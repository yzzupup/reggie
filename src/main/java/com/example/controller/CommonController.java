package com.example.controller;

import com.example.common.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * ClassName: CommonController
 * Package: com.example.controller
 * Description:
 *
 * @Author yzz
 * @Create 2023/12/5 11:22
 * @Version 1.0
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    @Value("${file.path}")
    private String basePath;
    @PostMapping("/upload")
    public R<String> uploadFile(MultipartFile file) throws IOException {

        String originalName = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalName.substring(originalName.indexOf("."));

        File dir = new File(basePath);
        if(!dir.exists())
            dir.mkdirs();

        file.transferTo(new File(basePath + fileName));

        return R.success(fileName);
    }

    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) throws IOException {

        FileInputStream inputStream = null;
        ServletOutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(basePath + name);

            outputStream = response.getOutputStream();

            byte[] bytes = new byte[1024];
            int len = 0;

            while ((len = inputStream.read(bytes)) != -1){
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }
        }
        catch (Exception ex){
            throw ex;
        }
        finally {
            if(inputStream != null)
                inputStream.close();
            if(outputStream != null)
                outputStream.close();
        }




        return;
    }
}
