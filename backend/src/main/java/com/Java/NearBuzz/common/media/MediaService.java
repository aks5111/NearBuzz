package com.Java.NearBuzz.common.media;

import org.springframework.web.multipart.MultipartFile;

public interface MediaService {
    /**
     * Uploads an image to Cloudinary under the given folder
     * (e.g. "travel", "fitness") and returns its public HTTPS URL.
     */
    String uploadImage(MultipartFile file, String folder);
}
