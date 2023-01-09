package com.laonworks.shop.api.controller.classes;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.laonworks.shop.api.framework.libs.FormFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class AwsService {

    @Autowired
    AmazonS3 amazonS3;

    @Autowired
    AwsConfig awsConfig;

    public String uploadFile(String key, FormFile avatarFile) {
        return "";
    }

    public List<String> uploadProductImageList(List<String> base64List) {

        List<String> urlList = new ArrayList<>();

        base64List.forEach(base64Image -> {

            // mime type, base64 split
            String[] base64ImageSplit = base64Image.split(",");
            String mimeType = base64ImageSplit[0].split(":")[1].split(";")[0];
            String base64 = base64ImageSplit[1];


            byte[] bytes = Base64.getDecoder().decode(base64);

            InputStream fis = new ByteArrayInputStream(bytes);

            String extName = ".jpg";
            if (mimeType.equals("image/png")) {
                extName = ".png";
            }

            final String key = "product/" + UUID.randomUUID() + extName;
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(mimeType);
            objectMetadata.setContentLength(bytes.length);

            PutObjectRequest request = new PutObjectRequest(awsConfig.bucket, key, fis, objectMetadata);
            amazonS3.putObject(request);

            urlList.add(awsConfig.endpoint + "/" + key);
        });

        return urlList;
    }
}
