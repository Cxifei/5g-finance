package cn.fate.ssm.service.impl;

import cn.fate.ssm.beans.User;
import cn.fate.ssm.mapper.UserBackgroundMapper;
import cn.fate.ssm.service.IUserBackgroundService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 后台用户管理
 *
 * @author rimi
 * @DATE 2019-06-19 17:01
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserBackgroundServiceImpl implements IUserBackgroundService {

    private UserBackgroundMapper userBackgroundMapper;

    public UserBackgroundServiceImpl(UserBackgroundMapper userBackgroundMapper){
        this.userBackgroundMapper=userBackgroundMapper;
    }

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<User> bFindAll() {
        return userBackgroundMapper.bFindAll();
    }

    /**
     * 根据ID删除用户
     * @return
     */
    @Override
    public boolean bDelete(int id) {
        return userBackgroundMapper.bDelete(id)>0;
    }

    /**
     * 查询所有已通过验证的用户
     * @return
     */
    @Override
    public List<User> bFindVerification() {
        return userBackgroundMapper.bFindVerification();
    }

    /**
     * 查询所有未通过验证的用户
     * @return
     */
    @Override
    public List<User> bFindUnverified() {
        return userBackgroundMapper.bFindUnverified();
    }
}
