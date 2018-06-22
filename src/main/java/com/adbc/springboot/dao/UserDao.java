package com.adbc.springboot.dao;

import com.adbc.springboot.enrty.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User findUserByName(String name){
        List<User> list = jdbcTemplate.query("select * from t_user where name = ?", new Object[]{name}, new BeanPropertyRowMapper(User.class));
        if(list!=null && list.size()>0){
            User user = list.get(0);
            return user;
        }else{
            return null;
        }
    }
}
