package com.example.demo.service;

import com.example.demo.bo.CustomUserDetails;
import com.example.demo.entity.Authorities;
import com.example.demo.entity.UsersDemo;
import com.example.demo.mapper.AuthoritiesMapper;
import com.example.demo.mapper.UsersDemoMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomUserService implements UserDetailsService {
    @Resource
    UsersDemoMapper usersDemoMapper;

    @Resource
    AuthoritiesMapper authoritiesMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //selectById是按主键搜索，用户表的主键就是username
        UsersDemo usersDemo = usersDemoMapper.selectById(username);
        if (usersDemo == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("username", usersDemo.getUsername());
        List<Authorities> authoritiesList = authoritiesMapper.selectByMap(map);

        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        for (Authorities authorities : authoritiesList) {
            auths.add(new SimpleGrantedAuthority(authorities.getAuthority()));
        }

        CustomUserDetails customUserDetails = new CustomUserDetails(usersDemo, auths);
        return customUserDetails;

    }
}
