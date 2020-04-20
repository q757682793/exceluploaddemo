package com.example.exceluploaddemo.controller;

import com.example.exceluploaddemo.service.UserService;
import com.example.exceluploaddemo.util.Const;
import com.example.exceluploaddemo.util.FileDownload;
import com.example.exceluploaddemo.util.ObjectExcelRead;
import com.example.exceluploaddemo.util.PageData;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class IndexController {
    @Resource
    UserService userService;

    @RequestMapping(value = "/downloadExcel")
    public void downloadStudentExcel(HttpServletResponse response) throws Exception {
        String url = ResourceUtils.getURL("classpath:").getPath();
        FileDownload.fileDownload(response, Const.getDownLoadPath()+ "excel.xls", "excel.xls");
    }

    /***
     * 批量导入
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/upload")
    public String uploadStudentExcel(
            @RequestParam(value="excel",required=false) MultipartFile file
    ) throws Exception{
        if (null != file && !file.isEmpty()) {
            String filePath = Const.FILEPATHFILE;                                //文件上传路径
            String fileName=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+".xls";
            File newFile=new File(filePath);
            if(!newFile.exists()){//目录不存在就创建
                newFile.mkdirs();
            }
            File saveFile = new File(filePath+fileName);
            file.transferTo(saveFile);//保存文件
            List<PageData> listPd = (List) ObjectExcelRead.readExcel(filePath, fileName, 2, 0, 0);        //执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
            try{
                userService.importStudentExcel(listPd);
                saveFile.delete();
                return "success";
            }catch (Exception e){
                return "fail";
            }
        }else{
            return "请选择文件！";
        }
    }
}
