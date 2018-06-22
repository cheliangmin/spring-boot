package com.adbc.springboot.controller;

import com.adbc.springboot.dao.UserDao;
import com.adbc.springboot.enrty.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UserDao userDao;

    @PostMapping(value = "/user/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Map<String ,Object> map,
            HttpSession session) {
        User user = userDao.findUserByName(username);
        if(user == null){
            map.put("msg","用户不存在");
            return "login";

        }else if(!user.getPassword().equals(password)){
            map.put("msg","密码错误");
            return "login";
        } else{
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }
    }
    @GetMapping(value = "/user/logout")
    public String logout( Map<String ,Object> map,HttpSession session){
        map.put("msg","你已经退出");
        session.setAttribute("loginUser","");
        return "login";
    }
}
