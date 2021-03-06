package cn.fate.ssm.mapper;

import cn.fate.ssm.beans.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单映射
 *
 * @author rimi
 * @DATE 2019-06-11 16:23
 */
public interface BillMapper {



    /**
     * 根据贷款类型查询相关订单
     *
     * @param type
     * @return
     */
    List<Bill> selectBillListByType(String type);

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

    /**
     * 用户发布的单子
     * @param id
     * @return
     */
    List<Bill> myInvoice(int id);


    /**
     * 用户接取的单子
     * @param id
     * @return
     */
    List<Bill> myReceipt(int id);

    /**
     *
     * @param bill 订单
     * @return 是否添加成功
     */
    boolean addBill(Bill bill);

    /**
     *
     * @param id 订单id
     * @param uid 接单用户id
     * @return 是否接单成功
     */
    boolean acceptBill(@Param("id") int id, @Param("uid") int uid,@Param("accepttime") String accepttime);

    int quxiao(int id);
}
