package cn.fate.ssm.service;

import cn.fate.ssm.beans.Bill;
import cn.fate.ssm.beans.User;

import java.util.List;

/**
 * @author: Brand
 * Date:     2019/6/19 17:30
 * Description: 后台订单管理业务层接口
 */
public interface BGBillService {

    boolean checkBill(int id, int status);
    boolean deleteBill(int id);
    List<Bill> getBillByStatus();
    User getUserById(int id);
}
