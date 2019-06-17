package cn.fate.ssm.utils;

import cn.fate.ssm.beans.User;
import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpHeaders;

/**
 * 通过token获取user
 *
 * @author fate
 * @date 2019-06-14 16:25
 */
public class GetUserByToken {
    /**
     * 通过token获取User，不存在时是null
     *
     * @param token header的
     * @return 返回用户数据
     */
    public static User getUserByToken(HttpHeaders headers){
        //获取token
        String token = headers.getFirst("token");
        if (token == null){
            return null;
        }
        String userJson = RedisUtli.getString(token);
        if (userJson == null ){
            return null;
        }
        return JSON.parseObject(userJson,User.class);
    }

}
