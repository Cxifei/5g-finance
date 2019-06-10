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
    private String username;
    private String password;
    private String id;
    private String age;
    private String sex;
}
