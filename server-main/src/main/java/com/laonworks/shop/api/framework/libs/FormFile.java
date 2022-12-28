package com.laonworks.shop.api.framework.libs;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Data
public class FormFile {

    private String name;

    private String originalFilename;

    private String path;

    private long size;

    private File file;

    public FormFile(MultipartFile multipartFile) {

        // random temp file path
        final String tempPath = System.getProperty("java.io.tmpdir") + "/" + UUID.randomUUID().toString();

        try {

            final File file = new File(tempPath);

            multipartFile.transferTo(file);

            this.name = multipartFile.getName();

            this.originalFilename = multipartFile.getOriginalFilename();

            this.path = tempPath;

            this.file = file;

            this.size = file.length();

        } catch (IOException e) {

            this.name = null;
            this.originalFilename = null;
            this.path = null;
            this.file = null;
            this.size = 0;

            e.printStackTrace();
        }
    }
}
