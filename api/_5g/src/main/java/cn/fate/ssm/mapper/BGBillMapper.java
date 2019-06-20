package cn.fate.ssm.mapper;

import cn.fate.ssm.beans.Bill;
import cn.fate.ssm.beans.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: Brand
 * Date:     2019/6/19 17:28
 * Description: 后台管理系统 订单管理接口
 */
public interface BGBillMapper {

    /**
     * 后台审核订单的方法
     * @param id 订单id
     * @param status 审核后的订单状态值  1 为审核未通过， 2为审核通过
     * @return 是否审核订单成功
     */
    boolean checkBill(@Param("id") int id, @Param("status") int status);

    /**
     * 后台删除订单的方法
     * @param id 需要删除的订单id
     * @return 是否删除成功
     */
    boolean deleteBill(@Param("id") int id);

    /**
     * 获取所有未审核的单据
     * @return 所有未审核单据的集合
     */
    List<Bill> getBillByStatus();

    /**
     * 根据订单内的用户id获取用户信息
     * @param id 用户id
     * @return 与订单绑定的用户
     */
    User getUserById(@Param("id") int id);
}
