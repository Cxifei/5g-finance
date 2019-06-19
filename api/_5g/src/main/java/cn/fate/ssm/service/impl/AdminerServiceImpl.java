package cn.fate.ssm.service.impl;

import cn.fate.ssm.beans.Adminer;
import cn.fate.ssm.mapper.AdminerMapper;
import cn.fate.ssm.service.IAdminerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 管理员业务层实现类
 * @author always_on_the_way
 * @date 2019-06-19
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminerServiceImpl implements IAdminerService {

    private AdminerMapper adminerMapper;

    @Autowired
    public AdminerServiceImpl(AdminerMapper adminerMapper) {
        this.adminerMapper = adminerMapper;
    }

    /**
     * 通过管理员信息对象查询管理员数据
     *
     * @param adminer 管理员信息对象
     */
    @Override
    public Adminer findAdminer(Adminer adminer) {
        return adminerMapper.selectAdminer(adminer);
    }
}
