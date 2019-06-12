package cn.fate.ssm.service.impl;

import cn.fate.ssm.beans.User;
import cn.fate.ssm.mapper.UserMapper;
import cn.fate.ssm.service.IUserService;
import cn.fate.ssm.utils.MD5Utli;
import cn.fate.ssm.utils.StringUtli;
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





    /**
     * 注册时验证账户是否重复
     *
     * @param user 形成用户
     * @return 是否重复
     */
    @Override
    public User queryUserByPhone(User user) {
        return mapper.userQueryByPhone(user);
    }

    /**
     * 注册用户
     *
     * @param user 获取注册数据
     * @return 判断注册是否成功
     */
    @Override
    public boolean registerUser(User user) {
        //检查输入是否非法
        if (StringUtli.isNull(user.getName())||StringUtli.canshu(user.getName())
        ||StringUtli.isNull(user.getPhone()+"")||StringUtli.canshu(user.getPhone()+"")
        ||StringUtli.isNull(user.getSex())||StringUtli.canshu(user.getSex())
        ||StringUtli.isNull(user.getPassword())
        ){
            return false;
        }
        user.setPassword(MD5Utli.md52(user.getPassword()));
        System.out.println("开始注册"+user);
        return mapper.registerUser(user)>0;
    }

    /**
     * 修改用户信息
     *
     * @param user 输入要修改的用户信息
     * @return 受影响 的行数
     */
    @Override
    public boolean changeUser(User user) {
        return mapper.changeUser(user)>0;
    }


}
