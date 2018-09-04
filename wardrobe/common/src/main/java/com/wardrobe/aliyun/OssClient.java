package com.wardrobe.aliyun;

import com.aliyun.oss.OSSClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by cxs on 2018/9/3.
 */
public class OssClient {

    public static String OSS_IMG_PATH = "img";
    public static String OSS_IMG_DO_MAIN = "https://oss-admin.oss-cn-beijing.aliyuncs.com";


    private static String ACCESS_KEY_ID = "LTAIOO9pRRO51NzA";
    private static String ACCESS_KEY_SECRET = "KQ8Cu0lEdjeYE67Vdw2uNeQFBcSwWG";
    private static String BUCKET_NAME = "oss-admin";
    public static String END_POINT = "http://oss-cn-beijing.aliyuncs.com";

    public static void putInputStream(InputStream inputStream, String fileName){
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(END_POINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        // 上传网络流。
        ossClient.putObject(BUCKET_NAME, fileName, inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    public static String getImgPath(String path){
        return OSS_IMG_DO_MAIN + "/" + path;
    }

    /*public static void main(String[] args) throws Exception{
        putInputStream(new FileInputStream(new File("D:\\Penguins.jpg")), "img/12345.jpg");
    }*/

}
