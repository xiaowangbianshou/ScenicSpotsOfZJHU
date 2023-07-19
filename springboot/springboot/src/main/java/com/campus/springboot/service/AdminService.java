package com.campus.springboot.service;

import com.campus.springboot.controller.dto.AdminDTO;
import com.campus.springboot.controller.dto.UserDTO;
import com.campus.springboot.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.springboot.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wzq
 * @since 2023-06-06 01:24:14
 */
public interface AdminService extends IService<Admin> {

    AdminDTO login(AdminDTO adminDTO);

}
