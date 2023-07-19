package com.campus.springboot.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.springboot.common.Result;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.campus.springboot.service.PointService;
import com.campus.springboot.entity.Point;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wzq
 * @since 2023-06-08 06:06:50
 */
@RestController
@RequestMapping("/point")
    public class PointController {

@Resource
private PointService pointService;

// 新增或者更新
@PostMapping
public Result save(@RequestBody Point point) {
        return Result.success(pointService.saveOrUpdate(point));
        }

@DeleteMapping("/{id}")
public Result delete(@PathVariable Integer id) {
        return Result.success(pointService.removeById(id));
        }

@PostMapping("/del/batch")
public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(pointService.removeByIds(ids));
        }

@GetMapping
public Result findAll() {
        return Result.success(pointService.list());
        }

@PostMapping("/{id}")
public Result findOne(@PathVariable Integer id) {
        return Result.success(pointService.getById(id));
        }

@PostMapping("/getpoint")
public Result findByIds(@RequestBody List<Integer> ids){
      return Result.success(pointService.listByIds(ids));
}

@GetMapping("/page")
public Result findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize) {
        QueryWrapper<Point> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(pointService.page(new Page<>(pageNum, pageSize), queryWrapper));
        }

        }

