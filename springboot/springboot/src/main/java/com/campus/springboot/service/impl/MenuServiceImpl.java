package com.campus.springboot.service.impl;

import com.campus.springboot.entity.Menu;
import com.campus.springboot.mapper.MenuMapper;
import com.campus.springboot.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzq
 * @since 2023-05-23 05:29:50
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}
