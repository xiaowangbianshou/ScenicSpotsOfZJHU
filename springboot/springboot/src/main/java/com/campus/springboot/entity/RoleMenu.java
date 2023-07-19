package com.campus.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("sys_role_menu")
public class RoleMenu {
    private Integer roleID;
    private Integer menuID;
}
