package cn.fate.ssm.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * @author: Brand
 * @Date: 2019/6/11 15:19
 * @Description: 订单实体类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor

public class Bill implements Serializable {

    /**
     * 贷款单据id
     */
    private int id;
    /**
     * 用户id
     */
    private int uid;
    /**
     * 贷款类型
     */
    private String type;
    /**
     * 贷款地址
     */
    private String address;
    /**
     * 贷款金额
     */
    private int amount;
    /**
     * 补充说明
     */
    private String instructions;
    /**
     * 估值/票面/房地产获得方式
     */
    private String valuation;
    /**
     * 利率
     */
    private String rate;
    /**
     *  公司负债率 或 房地产利润率
     */
    private String levorprofit;
    /**
     *  贷款周期（年，月，天）
     */
    private String cycle;
    /**
     * 借款用途
     */
    private String used;
    /**
     *  借款到期时间
     */
    private String editor;
    /**
     *  房屋面积
     */
    private double area;
    /**
     *  房屋容积率
     */
    private String plot;
    /**
     *  房屋楼面价
     */
    private double floorprice;
    /**
     *  房价
     */
    private double houseprice;
    /**
     *  征信/票据方式/房地产获得方式
     */
    private String creditplus;
    /**
     *  抵押物
     */
    private String mortqaqed;
    /**
     *  出款/类型（上市公司、票据、房地产 三个单据类型的字段为 类型）
     */
    private String prototype;
    /**
     *  风控/ 票据类型的单子 此字段表示利息
     */
    private String risk;
    /**
     *  佣金
     */
    private String commossion;
    /**
     *  接单人id
     */
    private int jid;
    /**
     *  发单人确认交易状态
     */
    private int uconfirm;
    /**
     *  接单人确认交易状态
     */
    private int jconfirm;
    /**
     *  单据创建时间
     */
    private String createtime;
    /**
     *  接单人接单时间
     */
    private String accepttime;
    /**
     *  单据状态(默认为未审核 0，审核未通过 1，审核通过 2)
     */
    private int status;

}
