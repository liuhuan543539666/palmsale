package com.guoanfamily.palmsale.common.util.qiniu;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cuiss on 2017/3/21.
 */
public class CdnUtil {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(CdnUtil.class);

    //设置好账号的ACCESS_KEY和SECRET_KEY
    private static final String ACCESS_KEY = "HrfSUQLnauuNKiErBrP_lBGlPBTfWQlqZqhP76-a";
    private static final String SECRET_KEY = "nvmXyeEhKRCA2o7LAthnM37uy-vfdAmRsfCr7yLt";

    //要上传的空间
    private static final String bucketname = "guoanjia";
    //转码管道名称
    private static final String pipeline = "amr2mp3";
    /**
     * 文件上传到cdn上，当前用的是七牛的cdn加速
     * @param dirPath
     * @return
     */
    public static String cdnUploadFile(File dirPath){
        //第二种方式: 自动识别要上传的空间(bucket)的存储区域是华东、华北、华南。
        Zone z = Zone.autoZone();
        Configuration c = new Configuration(z);
        //创建上传对象
        UploadManager uploadManager = new UploadManager(c);
        //密钥配置
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        if ( null != dirPath ){
            String fileName = dirPath.getName();
            String token  = null;
            if (fileName.indexOf(".amr") != -1){
                fileName = fileName.replace(".amr",".mp3");
                String pipeName = bucketname + ":" + fileName;
                Map policies = new HashMap<String, Object>();
                // "|"竖线前是你要转换格式的命令；竖线后是转换完成后，文件的命名和存储的空间的名称！
                policies.put("persistentOps", "avthumb/mp3|saveas/"+Base64.encodeBase64String(pipeName.getBytes()));
                policies.put("persistentPipeline" ,pipeline );
                StringMap policy = new StringMap().putAll(policies);
                token  =  auth.uploadToken(bucketname, null, 3600, policy, true);
            }else{
                token  =  auth.uploadToken(bucketname);
            }
            try {
                Response res = uploadManager.put(dirPath, dirPath.getName(), token);
                if(null != res && res.statusCode==200){
                    return fileName;
                }else {
                    logger.error("qiniu:error 七牛上传文件出错！");
                    return null;
                }
            } catch (QiniuException e) {
                e.printStackTrace();
                return null;
            }
        }else{
            return null;
        }
    }
}
