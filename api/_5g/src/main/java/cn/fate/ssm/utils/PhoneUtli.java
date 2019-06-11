package cn.fate.ssm.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;


/**
 * 短信验证码发送
 *
 * @author fate
 * @date 2019-06-11 09:52
 */
public class PhoneUtli {
    private static final String ACCSESS_KEY_ID = "LTAIedaCo8dEoz6z";
    private static final String ACCESS_SECRET="WizfIJSVxV1DEO71hEz0NerLshsOOP";

    public static String phoneCode(String phone) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCSESS_KEY_ID, ACCESS_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);
        String phoneCode = phoneCode();
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "个人网站fateys");
        request.putQueryParameter("TemplateCode", "SMS_167180389");
        request.putQueryParameter("TemplateParam", "{code:"+phoneCode+"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            return phoneCode;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String phoneCode(){
        int newNum = (int)((Math.random()*9+1)*100000);
        return String.valueOf(newNum);

    }
}
