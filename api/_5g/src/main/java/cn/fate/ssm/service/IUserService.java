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
}
