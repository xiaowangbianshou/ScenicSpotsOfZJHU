package com.campus.springboot.service;

import com.campus.springboot.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wzq
 * @since 2023-05-23 04:51:27
 */
public interface RoleService extends IService<Role> {

    void setRoleMenu(Integer roleId, List<Integer> menuIDs);
}
