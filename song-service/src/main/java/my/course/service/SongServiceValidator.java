package my.course.service;

import my.course.entity.Song;
import my.course.exception.SongServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SongServiceValidator {

    @Value("${validator.parameter.length}")
    private Integer parameterLength;

    public void validateSongExistence(Optional<Song> optSong) {
        if (optSong.isEmpty()) {
            throw SongServiceException.init404();
        }
    }

    /**
     * Valid CSV length < 200 characters
     * @param csvId, e.g. "1,2"
     */
    public void validateParamLength(String csvId) {
        if (csvId.length() >= parameterLength) {
            throw SongServiceException.init500();
        };
    }
}
