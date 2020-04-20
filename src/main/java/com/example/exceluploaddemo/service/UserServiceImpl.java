package com.example.exceluploaddemo.service;

import com.example.exceluploaddemo.dao.UserMapper;
import com.example.exceluploaddemo.pojo.User;
import com.example.exceluploaddemo.pojo.UserExample;
import com.example.exceluploaddemo.util.PageData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    @Transactional
    public void importStudentExcel(List<PageData> listPd) {
        /*存入数据库操作======================================*/
        /**
         * var0 :username
         * var1 :name
         * var2 :email
         * var3 :phone
         */
        int row = 0;
        for (int i = 0; i < listPd.size(); i++) {
            row = i;
            if (listPd.get(i).getString("var0") == "") {
                break;
            }
            User user = new User();
            user.setUserId("xxxx"+new Random().nextInt(1000000));
            user.setUsername(listPd.get(i).getString("var0"));
            user.setName(listPd.get(i).getString("var1"));
            user.setEmail(listPd.get(i).getString("var2"));
            user.setPhone(listPd.get(i).getString("var3"));
            try {
                userMapper.insertSelective(user);
            } catch (Exception e) {
                throw new RuntimeException("录入第"+(row+3)+"行出现异常,操作已撤销！");
            }
        }
    }
}
