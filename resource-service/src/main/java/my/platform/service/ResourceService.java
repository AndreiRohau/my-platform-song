package my.platform.service;

import my.platform.dto.ResourceCreateRequestDto;
import my.platform.dto.ResourceCreateResponseDto;
import my.platform.dto.ResourceDeleteResponseDto;
import my.platform.dto.ResourceGetResponseDto;

public interface ResourceService {

    ResourceGetResponseDto getResourceById(Integer id);

    ResourceCreateResponseDto saveResource(ResourceCreateRequestDto resourceCreateRequestDto);

    ResourceDeleteResponseDto deleteResources(String csvId);
}
