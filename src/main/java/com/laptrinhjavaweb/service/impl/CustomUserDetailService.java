package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.dto.MyUserDetail;
import com.laptrinhjavaweb.dto.RoleDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserDTO userDTO = userService.findOneByUserNameAndStatus(name, 1);
        if(userDTO == null){ // check account
            throw new UsernameNotFoundException("Username not found");
        }
        List<GrantedAuthority> authorities = new ArrayList<>(); // tạo 1 cái list quyền
        for(RoleDTO role: userDTO.getRoles()){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getCode())); // thằng account nay có bao nhiêu quyền thì cứ add vào thôi 
        }
        // UserDetails cần nhận 1 UserDetails nên ta tạo ra class MyUserDetail(nó extend USER rồi) này  là user hỗ trợ sẵn của User core SSecurity
        // Mặc định của USER chỉ có Username, Password 
        //tại vì ta có thêm fullname với id nửa nên ta cần tạo class extend cái User và thêm các field đó vào nên => MyUserDetail
        MyUserDetail myUserDetail = new MyUserDetail(name,userDTO.getPassword(),true,true,true,true,authorities);
        BeanUtils.copyProperties(userDTO, myUserDetail);
        return myUserDetail;
    }
}
