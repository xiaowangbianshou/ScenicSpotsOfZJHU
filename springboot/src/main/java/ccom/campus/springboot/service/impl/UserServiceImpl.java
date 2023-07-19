package ccom.campus.springboot.service.impl;

import ccom.campus.springboot.entity.User;
import ccom.campus.springboot.mapper.UserMapper;
import ccom.campus.springboot.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzq
 * @since 2023-04-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
