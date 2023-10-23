package my.platform.service;

import my.platform.dto.SongCreateResponseDto;
import my.platform.dto.SongDeleteResponseDto;
import my.platform.dto.SongDto;
import my.platform.dto.SongGetResponseDto;

public interface SongService {
    SongGetResponseDto getSongById(Integer id);

    SongCreateResponseDto saveSong(SongDto songDto);

    SongDeleteResponseDto deleteSongs(String csvId);
}
