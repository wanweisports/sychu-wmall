package com.wardrobe.wx.util;

import com.itextpdf.text.pdf.codec.Base64;
import net.sf.json.JSONObject;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;

public class WXAppletUserInfo {

    /**
     * 解密用户敏感数据获取用户信息
     * @param sessionKey 数据进行加密签名的密钥
     * @param encryptedData 包括敏感数据在内的完整用户信息的加密数据
     * @param iv 加密算法的初始向量
     * @return
     */
    public static JSONObject getUserInfo(String encryptedData,String sessionKey,String iv){
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionKey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);

        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSONObject.fromObject(result);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidParameterSpecException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getUserInfo("O4lTV4n2LKyI4DG0nkG819iC7fjL8kst7YFsWFMR855cN3RrXBg0I4KZjxWKChgOYXJNh8lFPAO8SiGmYhHN+qtx+wbN157IAnatpOASOFfVC5xWo/j+N4l52UD3JLVSfTGnCYCMOZJV0U7GrDf3NxYosqde4ThHS0goIoyLbJZQ5hMi6iBPJouCp55HcYNjwtlg9sgWSPVOXn62UtGg/UCbB53wtAiniJtfn7rIMqgoiN2si8lPhtElabV58laP9g5FFqheHMGB0CpFWrWZGn+LDJoCHeNXCO60GkSPUSojkRTKiEXKIwZIqN5oe/QWEZxPedBbBEWcqPzqp7vjNvfSxg396QiFgSlYBEfweiLVShyO4u7UuHeZnxdJ3i9FfksEQM0QQvhazSvZoGcuQRyA10sqS2ur4XMQmYFCCK/Dl1L1CaELkDDa4Qt2IUjMmZOmPnkpZyxH9znMlUrJWytN/bI/hKUYBNc+T5C0YlE=","bqiVGAnqlS/I9HC/+AxbTQ==", "uycq/rRLyD5ooaMsQPIhrA=="));
    }

}
