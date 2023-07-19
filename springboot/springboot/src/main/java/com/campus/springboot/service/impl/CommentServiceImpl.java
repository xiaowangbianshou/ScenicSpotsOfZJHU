package com.campus.springboot.service.impl;

import com.campus.springboot.entity.Comment;
import com.campus.springboot.mapper.CommentMapper;
import com.campus.springboot.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzq
 * @since 2023-06-09 04:42:04
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
