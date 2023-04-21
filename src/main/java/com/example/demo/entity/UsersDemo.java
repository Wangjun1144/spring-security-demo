package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("usersdemo")
public class UsersDemo {
    @TableId(type = IdType.INPUT)
    private String username;

    private String password;
    private Boolean enabled;

    public UsersDemo(String username, String password, Boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }
}
