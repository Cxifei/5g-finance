package cn.fate.ssm.mapper;

import cn.fate.ssm.beans.User;

/**
 * 用户映射
 *
 * @author fate
 * @date 2019-06-07 15:28
 */
public interface UserMapper {
    /**
     * 登陆验证
     *
     * @param user 构造一个用户信息
     * @return User
     */
    User userQuery(User user);
}
