package com.example.demo.controller;

import com.example.demo.entity.Users;
import com.example.demo.mapper.UsersMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.webjars.NotFoundException;

import javax.annotation.Resource;
import java.util.List;

@Tag(name = "UsersController", description = "利用HTTP状态码表示操作结果状态的控制器")
@RestController
@RequestMapping("/users")
public class UsersController {
    @Resource
    UsersMapper usersMapper;

    //@Operation用来对API添加说明
    @Operation(summary = "获取userslist", description = "返回值为全部的userslist")
    @GetMapping(path = "/list", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Users> listAll(){
        List<Users> users = usersMapper.findall();
        return users;
    }

    @Operation(summary = "根据id获取相应的users", description = "如果不存在，返回状态码为404")
    @PostMapping(path = "/id", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Users getById(Model model,Long id) {
        Users users = usersMapper.selectById(id);
        return users;
    }

    @Operation(summary = "根据name获取相应的users", description = "如果不存在，返回状态码为404")
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Users> getUserList(@RequestParam(name = "name", required = false)String name) {
        return usersMapper.getUserList(name);
    }

    @Operation(summary = "添加新的users", description = "创建成功，返回状态码201")
    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Users create(@RequestBody Users users) {
        usersMapper.addUsers(users);
        return users;
    }

    @Operation(summary = "根据id删除users")
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        usersMapper.deleteUserById(id);
    }

    @Operation(summary = "更新users", description = "如果不存在，则返回状态码404")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public Users update(@PathVariable Long id, @RequestBody Users users) {
        users.setId(id);
        usersMapper.updateUser(users);
        return users;
    }
}
