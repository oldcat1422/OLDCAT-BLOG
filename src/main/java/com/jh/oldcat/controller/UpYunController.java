package com.jh.oldcat.controller;

import com.jh.oldcat.utils.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/up")
@CrossOrigin
public class UpYunController {

    @Value("${upyun.bucket}")
    private String bucket;

    @Value("${upyun.operator}")
    private String operator;

    @Value("${upyun.password}")
    private String password;

    @Value("${upyun.domain}")
    private String domain;

    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error().message("请选择要上传的文件");
        }

        HttpURLConnection conn = null;
        try {
            String originalFilename = file.getOriginalFilename();
            String suffix = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + suffix;
            String datePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
            String savePath = "/" + datePath + "/" + newFileName;

            URL url = new URL("https://v0.api.upyun.com/" + bucket + savePath);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Authorization",
                    "Basic " + Base64.getEncoder().encodeToString(
                            (operator + ":" + password).getBytes("UTF-8")
                    ));
            conn.setRequestProperty("Content-Type", file.getContentType());
            conn.setDoOutput(true);
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(file.getBytes());
                os.flush();
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                String fileUrl = domain + savePath;
                return Result.ok().data("url", fileUrl);
            } else {
                return Result.error().message("上传失败，状态码: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("上传异常: " + e.getMessage());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
}
