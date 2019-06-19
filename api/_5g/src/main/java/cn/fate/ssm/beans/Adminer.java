package cn.fate.ssm.beans;

import lombok.Data;

/**
 * 管理员类
 *
 * @author always_on_the_way
 * @date 2019-06-19
 */
@Data
public class Adminer {

    /**
     * 管理员编号
     */
    private Integer aid;
    /**
     * 管理员账号
     */
    private String account;
    /**
     * 管理员密码
     */
    private String psw;

}
