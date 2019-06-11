package cn.fate.ssm.service;


import cn.fate.ssm.beans.User;

/**
 * 用户业务层
 *
 * @author fate
 * @date 2019-06-07 15:35
 */
public interface IUserService {
    /**
     * 登陆验证
     *
     * @param user 构造一个用户信息
     * @return User
     */
    User queryUser(User user);

    /**
     * 注册时验证账户是否重复
     *
     * @param user 形成用户
     * @return 是否重复
     */
    User queryUserByPhone(User user);

    /**
     * 注册用户
     * @param user 获取注册数据
     * @return 判断注册是否成功
     */
    boolean registerUser(User user);
}
