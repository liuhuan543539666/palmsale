package com.guoanfamily.palmsale.controller;

import com.guoanfamily.palmsale.common.AjaxJson;
import com.guoanfamily.palmsale.common.util.qiniu.CdnUtil;
import com.guoanfamily.palmsale.config.UrlSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
public class UploadController {

    @Autowired
    private UrlSettings urlSettings;

    @Autowired
    private AjaxJson ajaxJson;

    @PostMapping("/api/file/uploadFiles") // new annotation since 4.3
    @ResponseBody
    public AjaxJson singleFileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("file");
        List<Map<String, String>> fileUploadList = new ArrayList <>();
        try {
            for (MultipartFile file: files) {
                // Get the file and save it somewhere
                byte[] bytes = file.getBytes();
                String newFileName = UUID.randomUUID().toString()+getFileType(file.getOriginalFilename());
                Path path = Paths.get(urlSettings.getUploadPath() + newFileName);
                Files.write(path, bytes);
                Map<String, String> fileUpload = new HashMap <String , String>();
                fileUpload.put("fileName",file.getOriginalFilename());
                fileUpload.put("newFileName",newFileName);
                fileUploadList.add(fileUpload);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return ajaxJson.setSuccess(true).setStatus(200).setData(fileUploadList);
    }

    @PostMapping("/api/file/uploadsCdn")  //new annotation since 4.3
    @ResponseBody
    public AjaxJson singleQiniuFileUpload(HttpServletRequest request) {

        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("file");
        List<Map<String, String>> fileUploadList = new ArrayList <>();
        for (MultipartFile file: files) {
            String fileName = file.getOriginalFilename();
            String newFileName = UUID.randomUUID().toString()+getFileType(fileName);
            File dirPath = new File(urlSettings.getUploadPath(),newFileName);

            try {
                file.transferTo(dirPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String qiniuFileName = CdnUtil.cdnUploadFile(dirPath);
            Map<String, String> fileUpload = new HashMap <String , String>();
            fileUpload.put("path",urlSettings.getQiniuUrl());
            fileUpload.put("fileName",fileName);
            fileUpload.put("newFileName",qiniuFileName);
            fileUploadList.add(fileUpload);

        }
        return ajaxJson.setSuccess(true).setStatus(200).setData(fileUploadList);
    }

    /**
     * 获取文件后缀名
     * @param fileName
     * @return
     */
    private String getFileType(String fileName) {
        if(fileName!=null && fileName.indexOf(".")>=0) {
            return fileName.substring(fileName.lastIndexOf("."), fileName.length());
        }
        return "";
    }
}