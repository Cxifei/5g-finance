package cn.fate.ssm.mapper;

import cn.fate.ssm.beans.Bill;

import java.util.List;

/**
 * 订单
 *
 * @author rimi
 * @DATE 2019-06-11 16:23
 */
public interface BillMapper {

    /**
     * 获取最新的20个订单
     * @return
     */
    List<Bill> findNewestBill();

    /**
     * 根据ID删除订单
     * @param id
     * @return
     */
    int cancelBill(int id);

    /**
     * 发单人确认交易
     * @param id
     * @return
     */
    int issuerConfirm(int id);

    /**
     * 接单人确认交易
     * @param id
     * @return
     */
    int receiverConfirm(int id);

    /**
     * 根据id查询订单
     * @param id
     * @return
     */
    Bill findById(int id);

    /**
     * 发单人取消交易
     * @param id
     * @return
     */
    int IcancellationOfTransactions(int id);

    /**
     * 接单人取消交易
     * @param id
     * @return
     */
    int RcancellationOfTransactions(int id);
}
