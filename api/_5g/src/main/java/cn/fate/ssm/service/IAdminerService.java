package cn.fate.ssm.service;


import cn.fate.ssm.beans.Adminer;

/**
 * 管理员业务层接口
 * @author always_on_the_way
 * @date 2019-06-19
 */
public interface IAdminerService {

    /**
     * 通过管理员信息对象查询管理员数据
     * @param adminer 管理员信息对象
     */
    Adminer findAdminer(Adminer adminer);

}
