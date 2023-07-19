package com.campus.springboot.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.springboot.common.Result;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.campus.springboot.service.CommentService;
import com.campus.springboot.entity.Comment;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wzq
 * @since 2023-06-09 04:42:04
 */
@RestController
@RequestMapping("/comment")
    public class CommentController {

@Resource
private CommentService commentService;

// 新增或者更新
@PostMapping
public Result save(@RequestBody Comment comment) {
        return Result.success(commentService.saveOrUpdate(comment));
        }

@DeleteMapping("/{id}")
public Result delete(@PathVariable Integer id) {
        return Result.success(commentService.removeById(id));
        }

@PostMapping("/del/batch")
public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(commentService.removeByIds(ids));
        }

@GetMapping
public Result findAll() {
        return Result.success(commentService.list());
        }

@GetMapping("/{id}")
public Result findOne(@PathVariable Integer id) {
        return Result.success(commentService.getById(id));
        }

@GetMapping("/page")
public Result findPage(@RequestParam Integer pageNum,
                        @RequestParam Integer pageSize,
                        @RequestParam String username) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username",username);
        queryWrapper.orderByDesc("id");
        return Result.success(commentService.page(new Page<>(pageNum, pageSize), queryWrapper));
        }

        }

