package com.example.exceluploaddemo.util;

import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

public class Const {
    public static final String FILEPATHFILE  = "E://uploadFile/excel/";			//文件上传路径

    public static final String getDownLoadPath() throws FileNotFoundException {
        return ResourceUtils.getURL("classpath:").getPath()+ "templateExcels/";
    }
}
