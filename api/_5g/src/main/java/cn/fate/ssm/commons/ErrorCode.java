package cn.fate.ssm.commons;

/**
 * 错误状态
 *
 * @author fate
 * @date 2019-06-07 15:11
 */
public enum ErrorCode {

    SUCCESS(200,"操作成功"),
    FAIL(500,"操作失败"),
    REQUEST_NOT_FOUND(404,"无法响应"),
    REQUSET_NOT_METHOD(403,"请求类型异常"),
    FILE_ERROR(410,"文件异常"),
    LOGIN_ERROR(201,"用户名或密码错误"),
    CODE_ERROR(203,"输入验证码错误"),
    NOT_LOGIN_ERROR(204,"用户未登陆"),

    DELETE_ERROR(305,"取消订单失败"),
    TRANSACTION_SUCCESS(300,"交易成功"),
    TRANSACTION_ERROR(304,"交易失败"),
    ITRANSACTION_SUCCESS(301,"确认交易成功，等待接单人确认交易"),
    RTRANSACTION_SUCCESS(302,"确认交易成功，等待发单人确认交易")
    ;


    private int code;
    private String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
