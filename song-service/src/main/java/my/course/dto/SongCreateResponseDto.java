package my.course.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import my.course.entity.Song;

@NoArgsConstructor
@Data
public class SongCreateResponseDto {
    private Integer id;

    public SongCreateResponseDto(Song song) {
        this.id = song.getSongId();
    }
}
