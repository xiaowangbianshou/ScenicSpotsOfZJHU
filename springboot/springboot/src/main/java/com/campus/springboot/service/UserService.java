package com.campus.springboot.service;

import com.campus.springboot.controller.dto.UserDTO;
import com.campus.springboot.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wzq
 * @since 2023-04-15 05:19:49
 */
public interface UserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    User register(UserDTO userDTO);
}
