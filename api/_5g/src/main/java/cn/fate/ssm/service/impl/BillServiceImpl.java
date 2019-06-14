package cn.fate.ssm.service.impl;

import cn.fate.ssm.beans.Bill;
import cn.fate.ssm.mapper.BillMapper;
import cn.fate.ssm.service.IBillService;
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
    public BillServiceImpl(BillMapper billMapper){
        this.billMapper=billMapper;
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

    @Override
    public boolean IcancellationOfTransactions(int id) {
        return billMapper.IcancellationOfTransactions(id)>0;
    }

    @Override
    public boolean RcancellationOfTransactions(int id) {
        return billMapper.RcancellationOfTransactions(id)>0;
    }

}
