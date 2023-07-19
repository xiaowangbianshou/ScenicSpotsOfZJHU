package com.campus.springboot.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.springboot.common.Result;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.campus.springboot.service.MedalService;
import com.campus.springboot.entity.Medal;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wzq
 * @since 2023-06-02 08:26:11
 */
@RestController
@RequestMapping("/medal")
    public class MedalController {

@Resource
private MedalService medalService;

// 新增或者更新
@PostMapping
public Result save(@RequestBody Medal medal) {
        return Result.success(medalService.saveOrUpdate(medal));
        }

@DeleteMapping("/{id}")
public Result delete(@PathVariable Integer id) {
        return Result.success(medalService.removeById(id));
        }

@PostMapping("/del/batch")
public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(medalService.removeByIds(ids));
        }

@GetMapping
public Result findAll() {
        return Result.success(medalService.list());
        }

@GetMapping("/{id}")
public Result findOne(@PathVariable Integer id) {
        return Result.success(medalService.getById(id));
        }

@GetMapping("/page")
public Result findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize) {
        QueryWrapper<Medal> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(medalService.page(new Page<>(pageNum, pageSize), queryWrapper));
        }

        }

