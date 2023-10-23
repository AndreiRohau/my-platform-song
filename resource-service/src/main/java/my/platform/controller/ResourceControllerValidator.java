package my.platform.controller;

import my.platform.exception.ResourceServiceException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import static my.platform.config.Constant.AUDIO_MPEG;
import static my.platform.config.Constant.MP3;


@Component
public class ResourceControllerValidator {

    public void validateSaveResourceRequest(MultipartFile file) {
        validateRequestContentType(file);
        validateRequestResourceType(file);
    }

    private void validateRequestContentType(MultipartFile file) {
        if (!AUDIO_MPEG.equals(file.getContentType())) {
            throw ResourceServiceException.init400();
        }
    }

    private void validateRequestResourceType(MultipartFile file) {
        String[] splitFilename = file.getOriginalFilename().split("\\.");
        String ext = splitFilename[splitFilename.length - 1];
        if (!ext.equals(MP3)) {
            throw ResourceServiceException.init400();
        }
    }
}
