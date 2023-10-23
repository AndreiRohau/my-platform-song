package my.platform.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import my.platform.converter.MultipartFileToResourceCreateRequestDtoConverter;
import my.platform.dto.ResourceCreateRequestDto;
import my.platform.dto.ResourceCreateResponseDto;
import my.platform.dto.ResourceDeleteResponseDto;
import my.platform.dto.ResourceGetResponseDto;
import my.platform.exception.ResponseServiceException;
import my.platform.service.ResourceService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static my.platform.config.Constant.AUDIO_MPEG;

@RestController
@RequestMapping("/v1/api/resources")
@RequiredArgsConstructor
@Log
public class ResourceController {

    private final ResourceControllerValidator validator;
    private final ResourceService resourceService;
    private final MultipartFileToResourceCreateRequestDtoConverter multipartToDtoConverter;

    @GetMapping(value = "/{id}", produces = AUDIO_MPEG)
    public ResponseEntity<InputStreamResource> getResource(@PathVariable("id") Integer id) {
        log.info("Endpoint-get-path=" + "/v1/api/resources" + "/{" + id + "}");

        ResourceGetResponseDto resourceById = resourceService.getResourceById(id);

        try(InputStream is = new ByteArrayInputStream(resourceById.getResource())) {
            return ResponseEntity.ok().body(new InputStreamResource(is));
        } catch (Exception e) {
            throw ResponseServiceException.init500();
        }
    }

    @PostMapping
    public ResponseEntity<ResourceCreateResponseDto> saveResource(@RequestPart MultipartFile file) {
        log.info("Endpoint-post-path=" + "/v1/api/resources" + " | BODY: " + file);
        validator.validateSaveResourceRequest(file);

        ResourceCreateRequestDto resourceCreateRequestDto = multipartToDtoConverter.convert(file);

        return ResponseEntity.ok().body(resourceService.saveResource(resourceCreateRequestDto));
    }

    /**
     * Delete a resource(s). If there is no resource for id, do nothing
     * @param csvId CSV (Comma Separated Values) of resource IDs to remove
     * @return ResourceDeleteResponseDto
     */
    @DeleteMapping
    public ResponseEntity<ResourceDeleteResponseDto> deleteResource(@RequestParam("id") String csvId) {
        log.info("Endpoint-delete-path=" + "/v1/api/resources" + " | RequestParams: id=" + csvId);
        return ResponseEntity.ok().body(resourceService.deleteResources(csvId));
    }
}
