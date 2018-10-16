package com.wardrobe.aliyun;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.constant.IPlatformConstant;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

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

    @Desc("处理阿里云OSS上的图片--->下载->压缩->上传")
    public static void downOssYsUploadImgs(String imgs) throws Exception{
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = END_POINT;
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = ACCESS_KEY_ID;
        String accessKeySecret = ACCESS_KEY_SECRET;
        String bucketName = BUCKET_NAME;
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        String[] imgArr = imgs.split(",");
        for(String img : imgArr) {
            String objectName = img;

            // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
            OSSObject ossObject = ossClient.getObject(bucketName, objectName);

            // 读取图片内容。
            InputStream objectContent = ossObject.getObjectContent();
        /*FileUtils.copyInputStreamToFile(objectContent, new File("D://123.jpg"));*/

            int suffix = objectName.lastIndexOf(".");
            putInputStreamYS(objectContent, objectName.substring(0, suffix) + IPlatformConstant.YS_IMG_SUFFIX + objectName.substring(suffix));

            // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
            objectContent.close();
        }
        // 关闭OSSClient。
        ossClient.shutdown();

    }

/*    public static void main(String[] args) throws Exception{
        *//*StringBuilder imgs = new StringBuilder("img/f638355cbd01490491ae1a11414d7e35.jpg,img/4d5b901d92ef424fa078ca1d09dbfa46.jpg,img/d31764f6ee3140d1bc9c4d639a625578.jpg,img/ad95c65bf03744d1ba0fc9c0db7fae99.jpg,img/dd9510591a0f4be8aa122a855d1b0588.jpg,img/ed917fa5c86540559f709ef3d9069302.jpg,img/7ac21fc913474a9a86eda5a531ba7fbc.jpg,img/fa8daffb927a42c9a29a21defd98d059.jpg,img/e0042160e3cb46b7aab555abae23e678.jpg,img/303a6c76522e4477a16998e6e4002205.jpg,img/0274052ffc4f4063b85ea4f1be239433.jpg,img/3038d453e9b24f5fa94d6894271b9e10.jpg,img/b38e01d067a748a4ab91595fb326c570.jpg,img/38e814b2e6004930a4a1f24aeba3346a.jpg,img/7db45c6556104e5184b25a15ccff850b.jpg,img/21e60b5454d34bdba7712848ecd95e6d.jpg,img/2a4374d379f34d73bc926590c4a1851f.jpg,img/1ffaf84c47434b8a905351763313f536.jpg,img/94f3dad0a7dc4a2c8aa824308828a435.jpg,img/321d32277053436aa274d9d0d47b3026.jpg,img/a936678e9b0b40abae768e066263b11c.jpg,img/2afbf5f6da9b419bb2b58ef0bb529b0b.jpg,img/bd6d6bfdf9ae4f109be2be21683cb3f1.jpg,img/86b3f711c6584bd597ffed8f4b85d188.jpg,img/acfb9f2b299e4009a263ad3c645176f9.jpg,img/a1cde5ce6b6b4cceb6ec321ebf04fe45.jpg,img/a69a7ab2ec734522a9b7d6c1ddd269a9.jpg,img/a966c85efcc34a04901cfcb5b92fe9c8.jpg,img/88e4979a44884685bc7f6d1bc17853c2.jpg,img/9fb579691c3743c080f482bdf64800ae.jpg,img/662c99fcd0ac4a228e5b98798b68ec16.jpg,img/1ae593a31b8f42e4a9d1872ad970f418.jpg,img/f9cddbecd2f041af928cec67a1eeef01.jpg,img/650ae75159c2485c8923554eacfebb1c.jpg,img/2ce204647d8d4b8e8a5b243be6c9de62.jpg,img/bd6080e2677c4ababfba5ddbb3fd6947.jpg,img/3c3aae09dc964fa3a183cfa13de9fdad.jpg,img/5276310684eb4ce9a1f11c6ddaef6171.jpg,img/f9dda3275a6140a8b726fa469d8c72d6.jpg,img/263396811746457da3edc94805ddecb3.jpg,img/d0c68bc97a4e42f298ff2cb41a941f07.jpg,img/2da586c347004645b3f78c11b5a7386d.jpg,img/5682f5ac80ef43d69a29ec1bc2b9d8c9.jpg,img/73f62851977f4ac0bae80098ab03d3de.jpg,img/f7f4fe62966d46f694214cff7ab56c55.jpg,img/4fa322ecc04641b38335fac83c1c7d35.jpg,img/4d1fd47fbbc840d9a9b4a3e24fcba9e8.jpg,img/1d4e6fcff7b244908e8c5ed55b9b401c.jpg,img/b10bd5d4a45d4dedbd5171e4817aeec7.jpg,img/63111997f68d4e50a9116b0591e43fd7.jpg,img/4400fd3ff8704720be7caa713b5eec48.jpg,img/a93d762f7cdf4fea9a945636c03f3965.jpg,img/bf8e1567858644f98f7aeebeb40c537b.jpg,img/062ff118609440c78a3bdd6284d53b56.jpg,img/1f23ba1c601e43f69c34b1d80a745afa.jpg,img/1dc8a9c381574429868aca384938c428.jpg,img/70043597336d48ad96013fde3da6e324.jpg,img/dad27678478645e79f6602b6647de512.jpg,img/2ab5532f5ac34796aa4ad748539b1b56.jpg,img/1551330188c148f3b08514adb0bde4ee.jpg,img/0661a1048a894120b9c95cd47577db23.jpg,img/7f959647db1a48df85b03d993b93ee66.jpg,img/0fd3e7f9fd2c4570bdd3a107045fd742.jpg,img/97d2e83b46ad40be966a98d018091dac.jpg,img/a2002c0b5a9c4a32a9e2c290b39cc0da.jpg,img/dcca4861888c42028b815189be47a870.jpg,img/f2c026acdc7a4df4b653c9a7a6fe7d37.jpg,img/1e7a2aefe2b54fa1b354af1db1475b64.jpg,img/02190f4ea50b4b3b85269f5f2c9b6cef.jpg,img/2ad0b0b8d13341ed8de254a04d96fef5.jpg,img/3e848b887ae5497bbb33da968d9c18ce.jpg,img/679e78305f704516a54883e3aa89a1fc.jpg,img/b8d579751cbf4f718ac2d37e64720815.jpg,img/25471084342b41a3a1367be7698e28ef.jpg,img/cd29f2e5e18545bfb95dbc78a3777ffa.jpg,img/dc57723a2b3b4b80af982708481598dc.jpg,img/a100dc36af4845a3b702319c49ad96b9.jpg,img/b9d20c091145445a8e99c5d68e456f91.jpg,img/821cdd3ea99a4d2eb9f95a59611f0488.jpg,img/11088287dd17474a8996b5d29146aba3.jpg,img/b7f5804c6aa14cef80cf8152d8377ddd.jpg,img/7c06556f1bb64329a4c4d23d7cf3da9e.jpg,img/0f0ef65d3a894811b30294b88a05c43a.jpg,img/e52619bc72e74950a02ae7b5adf9cf0a.jpg,img/ecfa0253f43644849f1e6c70abbc238e.jpg,img/2fb73be15ff44dbebff3fe677d89a214.jpg,img/9464de1912b749868e57089574be1ff0.jpg,img/d1a8fb5925eb404a8d211b65b0c5f796.jpg,img/eef202f9144e4dcead47b479319446b2.jpg,img/7db1189098d0494f9a3cd6ddef5cf0d3.jpg,img/52b3c0c2ee7249328fc081e7c1406284.jpg,img/2dbb9db2a067404898a83bc70461b560.jpg,img/e0e87cb8082f40e3b743a8b02a7449a3.jpg,img/239ea366c1324670badf004b2d4380a4.jpg,img/8a24c57f328643a4bb5a15be6cdec6e0.jpg,img/3fe7bb6e1db74d9e8e134fb74a8c29bd.jpg,img/ab823a050c6f49749f63689d9ae94a4d.jpg,img/3a04f6c784fb4f0290d549bb882e591d.jpg,img/71c44c14f71d4060b95879200b2d1f61.jpg,img/bb87415ae89a4ed29b252bdbbe32363c.jpg,img/c248a477cc984d39803c0842d9bb0407.jpg,img/37dcdec1ca3047c182fa052c4bfb3dfe.jpg,img/b85262cfb8e44f6dbbbdb9c87ebe3966.jpg,img/b1bbfa2c30a94cbbb20ddba2a2a470d9.JPG,img/fae3f17625734bc688a705b4c90df554.JPG,img/0bdb7fae9de24433ba7691340546d808.JPG,img/4542a4ff6fb342a5b91c587eed54d4a0.jpg,img/ff9d71f238d74199ae11ffee0949b621.JPG,img/0e8750e9848d462f8113307e1b29e4d4.JPG,img/71b66cf92b024d64a78a860a62106de6.JPG,img/47ec138e0cc64e918ea9e12f67a50be5.JPG,img/6b43caa3612b448697a7a065f62b257b.JPG,img/5e7e904244074db6afa2ec8371281406.JPG,img/8aa00b0f01214efeae6e9d6596b27304.JPG,img/d06b1aa1b88c4359b05314f60cf002d3.JPG,img/c2505d4b16df4446b183a5bf71c97f89.jpg,img/fd6d91edc1c447a09e050dbf031757d0.jpg,img/a2c26f6d0ebb4f928a27a315aa15932e.JPG,img/706b87b988b64a35a11c37b145c53465.jpg,img/0bde80719837451ca9edaa75e02f2a75.JPG,img/7ee835e407894240b248973836205d35.jpg,img/ba0e87438da645e09e445315db0c1473.jpg,img/d14af31081dc4de3999b8d308888eff0.JPG,img/705cfc2702cb4403a51aa9492e34b89e.JPG,img/c532b4ba1c554e00931235a1e0e7d3b2.JPG,img/d5b0ff483b804f27baa7af8181035364.JPG,img/d7c249d52f9348a3923d9cf0fb05c78c.JPG,img/c7615e15d533485ca04bb7e9dfa4d3a5.JPG,img/0d18462111b349f2988fd66dd2ae63e9.jpg,img/50ef1a303d03477aa2ecb7b5fbb5e689.jpg,img/b282bcb467cd4cfdb4df811aca102308.jpg,img/2e6c140726f94500885f3f08b847d5e0.jpg,img/26b7826840144471afba00684653ec1e.jpg,img/cc089e6c69c34424aeb45f231c7e65a9.jpg,img/b05aaf74d4824894a489efc895d37061.jpg,img/1cdd220479914af480833a52dc214ac2.jpg,img/8eb2c76eb8a34b65939a4b0825a62f38.jpg,img/5f4f389892e144fb92a9de842f950931.JPG,img/be4776c06cb343bba04452320abf946e.JPG,img/7a3ff65e7a6c49649e38007d7b3228c3.JPG,img/66159a1d43ec4107b559106c1d75d5bf.JPG,img/1b737fcd5ae64eeb92c818b28ce1eaf5.JPG,img/ae21ca0f3b464ce1ac3841f63d8b6aaf.JPG,img/61fa3c612ae04ef4b38d05a7f28a920f.JPG,img/eb03c9dd0e454bc4a884bcd69fddea54.JPG,img/135abfc7f5414dd689a25d389cf15d5d.JPG,img/9ac302cbfed24f69a1364af0ddadc030.png,img/0908f2e0503b4bf9879b397ba74c40b3.JPG,img/15167f0c21ec4d1eb85d0bdecb9ec6fb.JPG,img/ec28190d80784f63870aa176b666190c.jpg,img/07975828b54442f3ad5a3ad620b0425f.JPG,img/64b1ae3f328d4c45b5574e85adbab5de.jpg,img/09ce29d9589047329f9feaf4a5b96203.JPG,img/55c9a93045624b25886122cf6f07227e.JPG,img/35338584768d4c0283c4780542f1d623.JPG,img/9d2b2f37edc64a3cafcdb71a69038e9d.JPG,img/ff1b35831214419a937f208143dedea8.JPG,img/0eec7ec2cb9446189813fc756c508226.JPG,img/5531073555014032a26989200fb66f01.JPG,img/81093855a93f433d8a29b4030824a2ff.JPG,img/c875582e70fc47e28642e37479d2694c.JPG,img/6d7d76766cf3474d83a7091375bf9892.JPG,img/fec01852d6f148d680467ccd94146717.JPG,img/ecf847c0d23142cbae3ccf5d24266f61.jpg,img/6ded97ed47d441efb6a2a75f527bb06f.JPG,img/c2340902bd3746c18cd7bb5c76905fbf.JPG,img/ef64814bc7b349ae9d9c4a62a9cbb3f5.JPG,img/fdd26f2967a84fec813eaa11786152ac.JPG,img/e3a3b224542140c8ae78621d444d82a0.JPG,img/888a276770e14053a8299abc501a13a0.JPG,img/f8adff467d494d798db25ead9068d1c2.JPG,img/618588ed699041adb491fb31085dfda2.JPG,img/ca65336463304fe2b05e4a5739be3c3d.jpg,img/ea228a93ac5043e0ac49845111dbba1e.jpg,img/8008358ff1c444eabb5190289f185040.jpg");
        downOssYsUploadImgs(imgs.toString());*//*

    }*/

}
