package my.course.service;

import my.course.dto.SongCreateResponseDto;
import my.course.dto.SongDeleteResponseDto;
import my.course.dto.SongDto;
import my.course.dto.SongGetResponseDto;

public interface SongService {
    SongGetResponseDto getSongById(Integer id);

    SongCreateResponseDto saveSong(SongDto songDto);

    SongDeleteResponseDto deleteSongs(String csvId);
}
