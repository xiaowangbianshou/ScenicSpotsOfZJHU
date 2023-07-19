package com.campus.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.campus.springboot.common.Constants;
import com.campus.springboot.controller.dto.UserDTO;
import com.campus.springboot.entity.User;
import com.campus.springboot.exception.ServiceException;
import com.campus.springboot.mapper.UserMapper;
import com.campus.springboot.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.campus.springboot.utils.TokenUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzq
 * @since 2023-04-15 05:19:49
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public UserDTO login(UserDTO userDTO) {
        User one = getUserLoginInfo(userDTO);
        if (one!=null){
            BeanUtil.copyProperties(one,userDTO,true);
             String token = TokenUtils.genToken("users"+one.getId().toString(),one.getPassword());
             userDTO.setToken(token);
            return userDTO;
        }else {
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }
    }

    @Override
    public User register(UserDTO userDTO) {
        User one = getUserRegisterInfo(userDTO);
        if(one == null){
            one = new User();
            BeanUtil.copyProperties(userDTO,one,true);
            save(one);
        }else {
            throw new ServiceException(Constants.CODE_600,"用户已存在");
        }
        return one;
    }

    private User getUserLoginInfo(UserDTO userDTO){
        QueryWrapper<User> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("username",userDTO.getUsername());
        queryWrapper.eq("password",userDTO.getPassword());
        User one;
        try {
            one = getOne(queryWrapper); //从数据库查询用户信息
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        return one;
    }

    private User getUserRegisterInfo(UserDTO userDTO){
        QueryWrapper<User> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("username",userDTO.getUsername());
        User one;
        try {
            one = getOne(queryWrapper); //从数据库查询用户信息
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        return one;
    }
}
