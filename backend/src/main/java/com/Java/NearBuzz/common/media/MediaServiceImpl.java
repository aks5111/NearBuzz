package com.Java.NearBuzz.common.media;

import com.Java.NearBuzz.common.exception.BadRequestException;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class MediaServiceImpl implements MediaService {

    private final Cloudinary cloudinary;

    public MediaServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadImage(MultipartFile file, String folder) {
        if (file == null || file.isEmpty()) {
            throw new BadRequestException("No file provided");
        }
        try {
            Map<?, ?> result = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap("folder", "nearbuzz/" + folder));
            return (String) result.get("secure_url");
        } catch (IOException ex) {
            throw new BadRequestException("Failed to upload image: " + ex.getMessage());
        }
    }
}
