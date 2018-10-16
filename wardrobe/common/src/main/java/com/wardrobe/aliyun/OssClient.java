package com.wardrobe.aliyun;

import com.aliyun.oss.OSSClient;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

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

    public static void putInputStreamYS(InputStream inputStream, String fileName) throws Exception{
        BufferedImage bufImg = ImageIO.read(inputStream);// 把图片读入到内存中

        // 压缩代码
        bufImg = Thumbnails.of(bufImg).width(750).keepAspectRatio(true).outputQuality(0.9).asBufferedImage();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();// 存储图片文件byte数组
        ImageIO.write(bufImg, "jpg", bos); // 图片写入到 ImageOutputStream
        ByteArrayInputStream input = new ByteArrayInputStream(bos.toByteArray());

        putInputStream(input, fileName); //上传到OSS
    }

    public static String getImgPath(String path){
        return OSS_IMG_DO_MAIN + "/" + path;
    }

    /*public static void main(String[] args) throws Exception{
        putInputStream(new FileInputStream(new File("D:\\Penguins.jpg")), "img/12345.jpg");
    }*/

}
