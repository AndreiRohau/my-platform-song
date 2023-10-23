package my.course.service;

import lombok.RequiredArgsConstructor;
import my.course.dto.SongCreateResponseDto;
import my.course.dto.SongDeleteResponseDto;
import my.course.dto.SongDto;
import my.course.dto.SongGetResponseDto;
import my.course.entity.Song;
import my.course.exception.SongServiceException;
import my.course.repository.SongRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SongServiceImpl implements SongService {

    private final SongServiceValidator validator;
    private final SongRepository songRepository;

    @Override
    public SongGetResponseDto getSongById(Integer id) {
        try {
            Optional<Song> optSong = songRepository.findById(id);

            validator.validateSongExistence(optSong);

            return new SongGetResponseDto(optSong.get());
        } catch (Exception e) {
            if (SongServiceException.isExceptionOfCode(e, HttpStatus.NOT_FOUND.value())) {
                throw e;
            } else {
                throw SongServiceException.init500();
            }
        }
    }

    @Override
    public SongCreateResponseDto saveSong(SongDto songDto) {
        try {
            Song song = new Song(songDto);
            songRepository.save(song);
            return new SongCreateResponseDto(song);
        } catch (Exception e) {
            throw SongServiceException.init500();
        }
    }

    @Override
    public SongDeleteResponseDto deleteSongs(String csvId) {
        try {
            validator.validateParamLength(csvId);
            final List<Integer> existingIds = Arrays.stream(csvId.split(","))
                    .map(Integer::parseInt)
                    .filter(songRepository::existsById)
                    .collect(Collectors.toList());

            songRepository.deleteAllById(existingIds);

            return new SongDeleteResponseDto(existingIds);
        } catch (Exception e) {
            throw SongServiceException.init500();
        }
    }
}
