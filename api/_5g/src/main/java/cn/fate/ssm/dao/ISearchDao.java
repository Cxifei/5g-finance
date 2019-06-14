package cn.fate.ssm.dao;

import cn.fate.ssm.beans.Bill;

import java.util.List;

/**
 * 搜索数据层接口
 *
 * @author always_on_the_way
 * @date 2019-06-14
 */

public interface ISearchDao {


    /**
     * 通过关键字搜索相关的订单
     * @param keyword
     * @return
     */
    List<Bill> selectBillListByKeyWord(String keyword) throws Exception;
}
