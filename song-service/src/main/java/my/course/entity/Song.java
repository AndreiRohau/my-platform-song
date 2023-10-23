package my.course.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.course.dto.SongDto;

import javax.persistence.*;

@Entity
@Table(name = "songs")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id")
    private Integer songId;
    @Column(name = "name")
    private String name;
    @Column(name = "artist")
    private String artist;
    @Column(name = "album")
    private String album;
    @Column(name = "length")
    private String length;
    @Column(name = "resource_id")
    private String resourceId;
    @Column(name = "year")
    private String year;

    public Song(SongDto songDto) {
        this.name = songDto.getName();
        this.artist = songDto.getArtist();
        this.album = songDto.getAlbum();
        this.length = songDto.getLength();
        this.resourceId = songDto.getResourceId();
        this.year = songDto.getYear();
    }
}
