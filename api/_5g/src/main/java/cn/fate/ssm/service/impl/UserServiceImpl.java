package cn.fate.ssm.service.impl;

import cn.fate.ssm.beans.User;
import cn.fate.ssm.mapper.UserMapper;
import cn.fate.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author fate
 * @date 2019-06-07 15:37
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements IUserService {
    private UserMapper mapper;

    @Autowired
    public UserServiceImpl(UserMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 登陆验证
     *
     * @param user 构造一个用户信息
     * @return User
     */
    @Override
    public User queryUser(User user) {
        return mapper.userQuery(user);
    }
}
