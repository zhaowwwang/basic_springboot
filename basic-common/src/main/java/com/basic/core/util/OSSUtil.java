package com.basic.core.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * @author: wangzw
 * @description: 优化代码
 * @version: 2.0
 * @date: 2017/11/29 14:09
 */
public class OSSUtil {

    public static String ENDPOINT = null;
    public static String ACCESS_KEY_ID = null;
    public static String ACCESS_KEY_SECRET = null;

    private static final Logger logger  = LoggerFactory.getLogger(OSSUtil.class);

    public static final OSSClient getOSSClient(String endpoint,String key,String secret) throws Exception {
        return new OSSClient(endpoint, key, secret);
    }

    /**
     * @author: wangzw
     * @description: 获取阿里云OSS客户端对象
     * @version: 1.0
     * @date: 2017/11/29 14:26
     */
    public static final OSSClient getOSSClient() throws Exception {
        return new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
    }

    /**
     * 新建Bucket  --Bucket权限:私有
     * @param bucketName bucket名称
     * @return true 新建Bucket成功
     * */
    public static final boolean createBucket(OSSClient client, String bucketName) throws Exception {
        Bucket bucket = client.createBucket(bucketName);
        return bucketName.equals(bucket.getName());
    }

    /**
     * 删除Bucket
     * @param bucketName bucket名称
     * */
    public static final void deleteBucket(OSSClient client, String bucketName) {
        client.deleteBucket(bucketName);
        logger.info("删除" + bucketName + "Bucket成功");
    }

    /**
     * 向阿里云的OSS存储中存储文件  --file也可以用InputStream替代
     * @param client OSS客户端
     * @param file 上传文件
     * @param bucketName bucket名称
     * @param diskName 上传文件的目录  --bucket下文件的路径
     * @return String 唯一MD5数字签名
     * */
    public static final String uploadObject2OSS(OSSClient client, File file, String fileName,String bucketName, String diskName) throws Exception{
        String resultStr = null;
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            Long fileSize = file.length();
            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(is.available());
            metadata.setCacheControl("no-cache");
            metadata.setHeader("Pragma", "no-cache");
            metadata.setContentEncoding("utf-8");
            metadata.setContentType(getContentType(fileName));
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            //上传文件
            PutObjectResult putResult = client.putObject(bucketName, diskName + fileName, is,metadata);
            //解析结果
            resultStr = putResult.getETag();
        } catch (Exception e) {
            logger.info("上传阿里云OSS服务器异常." + e.getMessage(), e);
            throw e;
        } finally {
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultStr;
    }

    /**
     * @author: wangzw
     * @description: 流需要在外层自行关闭
     * @version: 1.0
     * @date: 2017/11/29 14:44
     */
    public static final String uploadObject2OSS(OSSClient client, InputStream is, String fileName,String bucketName, String diskName) throws Exception{
        String resultStr = null;
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(is.available());
            metadata.setCacheControl("no-cache");
            metadata.setHeader("Pragma", "no-cache");
            metadata.setContentEncoding("utf-8");
            metadata.setContentType(getContentType(fileName));
            metadata.setContentDisposition("filename/filesize=" + fileName + "Byte.");
            //上传文件
            PutObjectResult putResult = client.putObject(bucketName, diskName + fileName, is,metadata);
            //解析结果
            resultStr = putResult.getETag();
        } catch (Exception e) {
            logger.info("上传阿里云OSS服务器异常." + e.getMessage(), e);
            throw e;
        }
        return resultStr;
    }

    /**
     * 根据key获取OSS服务器上的文件输入流
     * @param client OSS客户端
     * @param bucketName bucket名称
     * @param diskName 文件路径
     * @param key Bucket下的文件的路径名+文件名
     */
    public static final InputStream getOSS2InputStream(OSSClient client, String bucketName,String diskName, String key) throws Exception {
        OSSObject ossObj = client.getObject(bucketName, diskName + key);
        return ossObj.getObjectContent();
    }

    /**
     * 根据key删除OSS服务器上的文件
     * @param client OSS客户端
     * @param bucketName bucket名称
     * @param diskName 文件路径
     * @param key Bucket下的文件的路径名+文件名
     */
    public static void deleteFile(OSSClient client, String bucketName, String diskName,String key) throws Exception{
        client.deleteObject(bucketName, diskName + key);
        logger.info("删除" + bucketName + "下的文件" + diskName + key + "成功");
    }

    /**
     * 获得url链接
     * @param key (diskName + fileName)
     * @return
     */
    public static final String getUrl(OSSClient client, String bucketName, String key) throws Exception {
        // 设置URL过期时间为10年  3600l* 1000*24*365*10
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = client.generatePresignedUrl(bucketName, key, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }

    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     * @param fileName 文件名
     * @return 文件的contentType
     */
    public static final String getContentType(String fileName) {
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
        if ("bmp".equalsIgnoreCase(fileExtension)){
            return "image/bmp";
        }
        if ("gif".equalsIgnoreCase(fileExtension)){
            return "image/gif";
        }
        if ("jpeg".equalsIgnoreCase(fileExtension) || "jpg".equalsIgnoreCase(fileExtension)
                || "png".equalsIgnoreCase(fileExtension)){
            return "image/jpeg";
        }
        if ("html".equalsIgnoreCase(fileExtension)){
            return "text/html";
        }
        if ("txt".equalsIgnoreCase(fileExtension)){
            return "text/plain";
        }
        if ("vsd".equalsIgnoreCase(fileExtension)){
            return "application/vnd.visio";
        }
        if ("ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)){
            return "application/vnd.ms-powerpoint";
        }
        if ("doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)){
            return "application/msword";
        }
        if ("xml".equalsIgnoreCase(fileExtension)){
            return "text/xml";
        }
        if ("pdf".equalsIgnoreCase(fileExtension)){
            return "application/pdf";
        }
        return "text/html";
    }

}