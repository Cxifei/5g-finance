package cn.fate.ssm.utils;

import cn.fate.ssm.commons.BaiduOcr;
import cn.fate.ssm.commons.BaiduRequest;
import com.alibaba.fastjson.JSONObject;

/**
 * 身份证验证工具类
 *
 * @author fate
 * @date 2019-06-16 17:32
 */
public class RequestBaiduUtli {

    /**
     * 将转换为base64的图片发送给百度解析
     *
     * @param baseImg 图片
     * @return json的对象
     */
    public static JSONObject requestBaidu(String baseImg){
        baseImg = baseImg.replaceAll("\r\n","");
        baseImg = baseImg.replaceAll("\\+","%2B");


        //百度云的文字识别接口,后面参数为获取到的token
        String httpUrl=" https://aip.baidubce.com/rest/2.0/ocr/v1/idcard?access_token="+ BaiduOcr.getAuth();
        String httpArg = "detect_direction=true&id_card_side=front&image="+baseImg;
        String s = BaiduRequest.requestBaidu(httpUrl, httpArg);
        return JSONObject.parseObject(s);
    }


    /**
     * 通过json对象判断是否验证成功
     *
     * @param jsonObject 验证返回的json对象
     * @return 是否成功
     */
    public static boolean validationIsOk(JSONObject jsonObject){
        return "normal".equals(jsonObject.getString("image_status"));
    }
}
