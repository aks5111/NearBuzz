package com.Java.NearBuzz.common.media;

import com.Java.NearBuzz.common.exception.BadRequestException;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

@Service
public class MediaServiceImpl implements MediaService {

    private static final Logger log = LoggerFactory.getLogger(MediaServiceImpl.class);

    private static final Set<String> ALLOWED_CONTENT_TYPES =
            Set.of("image/png", "image/jpeg", "image/webp", "image/gif");
    private static final long MAX_IMAGE_BYTES = 8L * 1024 * 1024; // 8MB

    private final Cloudinary cloudinary;

    public MediaServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadImage(MultipartFile file, String folder) {
        if (file == null || file.isEmpty()) {
            throw new BadRequestException("No file provided");
        }
        if (file.getSize() > MAX_IMAGE_BYTES) {
            throw new BadRequestException("Image must be 8MB or smaller");
        }
        String contentType = file.getContentType();
        if (contentType == null || !ALLOWED_CONTENT_TYPES.contains(contentType.toLowerCase())) {
            throw new BadRequestException("Only PNG, JPEG, WEBP or GIF images are allowed");
        }

        try {
            Map<?, ?> result = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap(
                            "folder", "nearbuzz/" + sanitizeFolder(folder),
                            "resource_type", "image"));
            return (String) result.get("secure_url");
        } catch (IOException | RuntimeException ex) {
            log.error("Cloudinary upload failed", ex);
            throw new BadRequestException("Failed to upload image. Please try again.");
        }
    }

    private String sanitizeFolder(String folder) {
        String cleaned = folder == null ? "general" : folder.replaceAll("[^a-zA-Z0-9_-]", "");
        return cleaned.isBlank() ? "general" : cleaned;
    }
}
