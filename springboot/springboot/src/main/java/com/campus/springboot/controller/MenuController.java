package com.campus.springboot.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.springboot.common.Constants;
import com.campus.springboot.common.Result;
import com.campus.springboot.entity.Dict;
import com.campus.springboot.mapper.DictMapeper;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.campus.springboot.service.MenuService;
import com.campus.springboot.entity.Menu;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wzq
 * @since 2023-05-23 05:29:50
 */
@RestController
@RequestMapping("/menu")
    public class MenuController {

@Resource
private MenuService menuService;

@Resource
private DictMapeper dictMapeper;

// 新增或者更新
@PostMapping
public Result save(@RequestBody Menu menu) {
        return Result.success(menuService.saveOrUpdate(menu));
        }

@DeleteMapping("/{id}")
public Result delete(@PathVariable Integer id) {
        return Result.success(menuService.removeById(id));
        }

@PostMapping("/del/batch")
public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(menuService.removeByIds(ids));
        }

@GetMapping
public Result findAll() {
        List<Menu> list = menuService.list();
        List<Menu> parentNode = list.stream().filter(menu -> menu.getPid()==null).collect(Collectors.toList());
        for (Menu menu:parentNode){
               menu.setChildren(list.stream().filter(menu1 -> menu.getId().equals(menu1.getPid())).collect(Collectors.toList()));
        }
        return Result.success(parentNode);
        }

@GetMapping("/{id}")
public Result findOne(@PathVariable Integer id) {
        return Result.success(menuService.getById(id));
        }

@GetMapping("/page")
public Result findPage(@RequestParam Integer pageNum,
                       @RequestParam String name,
                       @RequestParam Integer pageSize) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);
        queryWrapper.orderByDesc("id");
        return Result.success(menuService.page(new Page<>(pageNum, pageSize), queryWrapper));
        }

@GetMapping("/icons")
public Result getIcons() {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", Constants.DICT_TYPE_ICON);
        return Result.success(dictMapeper.selectList(queryWrapper));
}

}


