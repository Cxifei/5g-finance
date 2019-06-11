package cn.fate.ssm.beans;

import lombok.Data;

/**
 * 登陆用户
 *
 * @author fate
 * @date 2019-06-07 15:04
 */
@Data
public class User {
    /**
     * 密码
     */
    private String password;
    /**
     * 账户id
     */
    private Long id;
    /**
     * 性别
     */
    private String sex;
    /**
     * 电话
     */
    private Long phone;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 公司名
     */
    private String company;
    /**
     * 用户名
     */
    private String name;
    /**
     * 用户简介
     */
    private String msg;
    /**
     * 用户地址
     */
    private String address;

}
