package cn.fate.ssm.mapper;

import cn.fate.ssm.beans.User;

import java.util.List;

/**
 * 后台用户管理
 *
 * @author rimi
 * @DATE 2019-06-19 17:12
 */
public interface UserBackgroundMapper {


    /**
     * 查询所有用户
     * @return
     */
    List<User> bFindAll();

    /**
     * 根据ID删除用户
     * @return
     */
    int bDelete(int id);

    /**
     * 查询所有已通过验证的用户
     * @return
     */
    List<User> bFindVerification();

    /**
     * 查询所有未通过验证的用户
     * @return
     */
    List<User> bFindUnverified();
}
