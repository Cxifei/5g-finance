package cn.fate.ssm.service.impl;

import cn.fate.ssm.beans.Bill;
import cn.fate.ssm.mapper.BillMapper;
import cn.fate.ssm.service.IBillService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单业务层
 *
 * @author rimi
 * @DATE 2019-06-11 17:26
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class BillServiceImpl implements IBillService {
    private BillMapper billMapper;

    @Autowired
    public BillServiceImpl(BillMapper billMapper) {
        this.billMapper = billMapper;
    }

    /**
     * 根据贷款类型查询相关订单
     *
     * @param type
     * @return
     */
    @Override
    public List<Bill> findBillListByType(String type) {
        return billMapper.selectBillListByType(type);
    }

    /**
     * 查询最新的20个订单
     * @return
     */
    @Override
    public List<Bill> findNewestBill() {
        return billMapper.findNewestBill();
    }

    /**
     * 根据ID删除订单
     * @param id
     * @return
     */
    @Override
    public boolean cancelBill(int id) {
        return billMapper.cancelBill(id)>0;
    }


    /**
     * 发单人确认交易
     * @param id
     * @return
     */
    @Override
    public boolean issuerConfirm(int id) {
        return billMapper.issuerConfirm(id)>0;
    }

    /**
     * 接单人确认交易
     * @param id
     * @return
     */
    @Override
    public boolean receiverConfirm(int id) {
        return billMapper.receiverConfirm(id)>0;
    }


    /**
     * 根据订单id查询订单
     * @param id
     * @return
     */
    @Override
    public Bill findById(int id) {
        return billMapper.findById(id);
    }

    /**
     * 发单人取消订单交易
     * @param id
     * @return
     */
    public boolean IcancellationOfTransactions(int id) {
        return billMapper.IcancellationOfTransactions(id)>0;
    }

    /**
     * 接单人取消订单交易
     * @param id
     * @return
     */
    @Override
    public boolean RcancellationOfTransactions(int id) {
        return billMapper.RcancellationOfTransactions(id)>0;
    }

    /**
     * 用户发布的单子
     * @param id
     * @return
     */
    @Override
    public List<Bill> myInvoice(int id) {
        return billMapper.myInvoice(id);
    }

    /**
     * 用户接过的单子
     * @param id
     * @return
     */
    @Override
    public List<Bill> myReceipt(int id) {
        return billMapper.myReceipt(id);
    }

    @Override
    public boolean quxiao(int id) {
        return billMapper.quxiao(id)>0;
    }

    /**
     * 接单人确认交易
     * @param id
     * @return
     */
    @Override
    public boolean addBill(Bill bill) {
        return billMapper.addBill(bill);
    }

    /**
     * @param id  订单id
     * @param uid 用户id
     * @return 是否接单成功
     */

    @Override
    public boolean acceptBill(int id, int uid, String accepttime) {
        return billMapper.acceptBill(id,uid,accepttime);
    }

}
