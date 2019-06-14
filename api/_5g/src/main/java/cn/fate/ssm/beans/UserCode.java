package cn.fate.ssm.beans;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户和验证码
 *
 * @author fate
 * @date 2019-06-12 14:10
 */
public class UserCode {
    private String phone;
    private String code;


    public UserCode(String user, String code) {
        this.phone = phone;
        this.code = code;
    }
    public UserCode() {
    }

    @Override
    public String toString() {
        return "UserCode{" +
                "phone='" + phone + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
