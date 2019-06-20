package cn.fate.ssm.service.impl;

import cn.fate.ssm.beans.Bill;
import cn.fate.ssm.beans.User;
import cn.fate.ssm.mapper.BGBillMapper;
import cn.fate.ssm.mapper.BillMapper;
import cn.fate.ssm.service.BGBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Brand
 * @Date: 2019/6/19 17:31
 * @Description: 后台订单管理业务层接口实现类
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class BGBillServiceImpl implements BGBillService {

    private BGBillMapper BGBillMapper;

    @Autowired
    public BGBillServiceImpl(BGBillMapper BGBillMapper) {
        this.BGBillMapper = BGBillMapper;
    }


    /**
     *
     * @param id 订单id
     * @return 是否审核成功
     */
    @Override
    public boolean checkBill(int id, int status) {
        return BGBillMapper.checkBill(id, status);
    }

    /**
     *
     * @param id 订单id
     * @return  是否删除成功
     */
    @Override
    public boolean deleteBill(int id) {
        return BGBillMapper.deleteBill(id);
    }

    @Override
    public List<Bill> getBillByStatus() {
        return BGBillMapper.getBillByStatus();
    }

    @Override
    public User getUserById(int id) {
        return BGBillMapper.getUserById(id);
    }
}
