package my.course.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import my.course.entity.Song;

@NoArgsConstructor
@Data
public class SongGetResponseDto {
    private String name;
    private String artist;
    private String album;
    private String length;
    private String resourceId;
    private String year;

    public SongGetResponseDto(Song song) {
        this.name = song.getName();
        this.artist = song.getArtist();
        this.album = song.getAlbum();
        this.length = song.getLength();
        this.resourceId = song.getResourceId();
        this.year = song.getYear();
    }
}
