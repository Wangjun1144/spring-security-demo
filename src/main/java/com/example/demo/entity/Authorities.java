package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("authorities")
public class Authorities {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String authority;
}
