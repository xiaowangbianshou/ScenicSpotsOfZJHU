package com.campus.springboot.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.springboot.common.Result;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.persistence.PostRemove;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.campus.springboot.service.RoleService;
import com.campus.springboot.entity.Role;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wzq
 * @since 2023-05-23 04:51:27
 */
@RestController
@RequestMapping("/role")
    public class RoleController {

@Resource
private RoleService roleService;

// 新增或者更新
@PostMapping
public Result save(@RequestBody Role role) {
        return Result.success(roleService.saveOrUpdate(role));
        }

@DeleteMapping("/{id}")
public Result delete(@PathVariable Integer id) {
        return Result.success(roleService.removeById(id));
        }

@PostMapping("/del/batch")
public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(roleService.removeByIds(ids));
        }

@GetMapping
public Result findAll() {
        return Result.success(roleService.list());
        }

@GetMapping("/{id}")
public Result findOne(@PathVariable Integer id) {
        return Result.success(roleService.getById(id));
        }

@PostMapping("/roleMenu/{roleId}")
public Result roleMenu(@PathVariable Integer roleId, @PathVariable List<Integer> menuIDs){
        roleService.setRoleMenu(roleId,menuIDs);
        return Result.success();

}

@GetMapping("/page")
public Result findPage(@RequestParam String name,
                       @RequestParam Integer pageNum,
                       @RequestParam Integer pageSize) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);
        queryWrapper.orderByDesc("id");
        return Result.success(roleService.page(new Page<>(pageNum, pageSize), queryWrapper));
        }

}
