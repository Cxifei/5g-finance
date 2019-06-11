package cn.fate.ssm.commons;

/**
 * 统一返回类型
 *
 * @author fate
 * @date 2019-06-07 15:07
 */
public class ResultData {
    /**
     * 返回的状态
     */
    private int code;
    /**
     * 返回的消息
     */
    private String msg;
    /**
     * 返回的数据
     */
    private Object data;

    /**
     * 获取错误的状态码
     * @param code 获取相应的错误码
     * @return ResultData
     */
    public static ResultData of(ErrorCode code){
        ResultData resultData = new ResultData();
        resultData.code = code.getCode();
        resultData.msg = code.getMsg();
        return resultData;
    }

    public static ResultData of(Object object){
        ResultData resultData = of(ErrorCode.SUCCESS);
        resultData.data = object;
        return resultData;
    }

    public static ResultData error(){
        return of(ErrorCode.LOGIN_ERROR);
    }

    public static ResultData success(){
        return of(ErrorCode.SUCCESS);
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
