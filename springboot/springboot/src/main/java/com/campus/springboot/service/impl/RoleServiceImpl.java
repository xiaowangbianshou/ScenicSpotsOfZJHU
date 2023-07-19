package com.campus.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.campus.springboot.entity.Role;
import com.campus.springboot.entity.RoleMenu;
import com.campus.springboot.mapper.RoleMapper;
import com.campus.springboot.mapper.RoleMenuMapper;
import com.campus.springboot.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzq
 * @since 2023-05-23 04:51:27
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Resource
    private RoleMenuMapper roleMenuMapper;
    @Transactional
    @Override
    public void setRoleMenu(Integer roleId, List<Integer> menuIds){
        roleMenuMapper.deleteByRoleID(roleId);

        for (Integer menuId:menuIds){
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenuID(menuId);
            roleMenu.setRoleID(roleId);
            roleMenuMapper.insert(roleMenu);
        }
    }
}
