package com.campus.springboot.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.springboot.common.Result;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.campus.springboot.service.PointsubmitService;
import com.campus.springboot.entity.Pointsubmit;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wzq
 * @since 2023-06-09 04:30:00
 */
@RestController
@RequestMapping("/pointsubmit")
    public class PointsubmitController {

@Resource
private PointsubmitService pointsubmitService;

// 新增或者更新
@PostMapping
public Result save(@RequestBody Pointsubmit pointsubmit) {
        return Result.success(pointsubmitService.saveOrUpdate(pointsubmit));
        }

@DeleteMapping("/{id}")
public Result delete(@PathVariable Integer id) {
        return Result.success(pointsubmitService.removeById(id));
        }

@PostMapping("/del/batch")
public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(pointsubmitService.removeByIds(ids));
        }

@GetMapping
public Result findAll() {
        return Result.success(pointsubmitService.list());
        }

@GetMapping("/{username}")
public  Result findinfo(@RequestParam String username){
        QueryWrapper<Pointsubmit> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return Result.success(pointsubmitService.list(queryWrapper));
}

@GetMapping("/page")
public Result findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize) {
        QueryWrapper<Pointsubmit> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(pointsubmitService.page(new Page<>(pageNum, pageSize), queryWrapper));
        }

        }

