package cn.fate.ssm.beans;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户和验证码
 *
 * @author fate
 * @date 2019-06-12 14:10
 */
public class UserCode {
    private User user;
    private String code;
    public UserCode(User user, String code) {
        this.user = user;
        this.code = code;
    }
    public UserCode() {
    }

    public User getUser() {
        return user;
    }
    @Autowired
    public void setUser(User user) {
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
