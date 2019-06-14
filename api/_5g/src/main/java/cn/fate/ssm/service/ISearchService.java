package cn.fate.ssm.service;

import cn.fate.ssm.beans.Bill;

import java.util.List;

/**
 * 搜索业务层接口
 * @author always_on_the_way
 * @date 2019-06-14
 */
public interface ISearchService {

    /**
     * 通过关键字搜索相关的订单
     * @param keyword
     * @return
     */
    public List<Bill> searchBillListByKeyWord(String keyword) throws Exception;
}
