package my.platform.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import my.platform.entity.Song;

@NoArgsConstructor
@Data
public class SongCreateResponseDto {
    private Integer id;

    public SongCreateResponseDto(Song song) {
        this.id = song.getSongId();
    }
}
