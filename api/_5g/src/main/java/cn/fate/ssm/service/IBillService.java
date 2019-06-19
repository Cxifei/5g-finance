package cn.fate.ssm.service;

import cn.fate.ssm.beans.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单业务层接口
 * @author rimi
 * @DATE 2019-06-11 17:24
 */
public interface IBillService {


    /**
     * 根据贷款类型查询相关订单
     * @param type
     * @return
     */
    List<Bill> findBillListByType(String type);

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

    /**
     * 发单人取消交易
     * @param id
     * @return
     */
    boolean IcancellationOfTransactions(int id);

    /**
     *
     * @param bill
     * @return 是否添加订单成功
     */
    boolean addBill(Bill bill);

    /**
     *
     * @param id 订单id
     * @param uid 用户id
     * @return 是否接单成功
     */
    boolean acceptBill(@Param("id") int id,@Param("uid") int uid);

    /**
     * @param id 订单id
     * @param status 审核后的订单状态 1表示审核未通过，2表示审核通过
     * @return 订单是否审核成功（通过，未通过）
     */

    boolean checkBill(@Param("id") int id, @Param("status") int status);

    /**
     * 接单人取消交易
     * @param id
     * @return
     */
    boolean RcancellationOfTransactions(int id);

    /**
     * 用户的发单
     * @param id
     * @return
     */
    List<Bill> myInvoice(int id);

    /**
     * 用户的接单
     * @param id
     * @return
     */
    List<Bill> myReceipt(int id);

    boolean quxiao(int id);


}
