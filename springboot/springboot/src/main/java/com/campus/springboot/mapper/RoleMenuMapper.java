package com.campus.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campus.springboot.entity.RoleMenu;
import org.apache.ibatis.annotations.Delete;

public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    @Delete("delete from sys_role_menu where role_id = #{roleId}")
    void deleteByRoleID(Integer roleId);
}
