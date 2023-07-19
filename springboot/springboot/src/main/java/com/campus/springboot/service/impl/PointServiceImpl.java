package com.campus.springboot.service.impl;

import com.campus.springboot.entity.Point;
import com.campus.springboot.mapper.PointMapper;
import com.campus.springboot.service.PointService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzq
 * @since 2023-06-08 06:06:50
 */
@Service
public class PointServiceImpl extends ServiceImpl<PointMapper, Point> implements PointService {

}
