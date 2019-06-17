package cn.fate.ssm.utils;


import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * base64工具
 *
 * @author fate
 * @date 2019-06-14 16:06
 */
public class Base64Util {
    /**
     * 将base64转化成 MultipartFile
     * @param base64 图片
     * @return MultipartFile
     */
    public static MultipartFile base64ToMultipart(String base64) {
        try {
            String[] baseStr = base64.split(",");

            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = new byte[0];
            b = decoder.decodeBuffer(baseStr[1]);

            for(int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }

            return new BASE64DecodedMultipartFile(b, baseStr[0]);
        } catch (IOException e) {
            return null;
        }
    }


    /**
     * 将本地图片转成base64
     * @param imageFile 文件的file对象
     * @return base64图片
     */
    public static String encodeImgageToBase64(File imageFile) {
// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
// 其进行Base64编码处理
        byte[] data = null;
// 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imageFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
// 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        return encoder.encode(data);
    }


}
