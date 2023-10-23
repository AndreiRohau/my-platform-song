package my.course.controller;

import my.course.dto.SongDto;
import my.course.exception.SongServiceException;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class SongControllerValidator {

    public void validateSaveSongRequest(SongDto songDto) {
        validateHasResourceId(songDto);
    }

    private void validateHasResourceId(SongDto songDto) {
        if (isNull(songDto.getResourceId()) || songDto.getResourceId().isBlank()) {
            throw SongServiceException.init400();
        }
    }
}
