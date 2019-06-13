package cn.fate.ssm.service;

import cn.fate.ssm.beans.Bill;

import java.util.List;

/**
 * 订单业务层接口
 * @author rimi
 * @DATE 2019-06-11 17:24
 */
public interface IBillService {

    /**
     * 查询最新的20个订单
     * @return
     */
    List<Bill> findNewestBill();

    /**
     * 根据Id删除订单
     * @param id
     * @return
     */
    boolean cancelBill(int id);

    /**
     * 发单人确认交易
     * @param id
     * @return
     */
    boolean issuerConfirm(int id);

    /**
     * 接单人确认交易
     * @param id
     * @return
     */
    boolean receiverConfirm(int id);

    /**
     * 根据id查询订单
     * @param id
     * @return
     */
    Bill findById(int id);

}
