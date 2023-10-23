package my.course.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import my.course.dto.SongCreateResponseDto;
import my.course.dto.SongDeleteResponseDto;
import my.course.dto.SongDto;
import my.course.dto.SongGetResponseDto;
import my.course.service.SongService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/songs")
@RequiredArgsConstructor
@Log
public class SongController {

    private final SongControllerValidator validator;
    private final SongService songService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<SongGetResponseDto> getSong(@PathVariable("id") Integer id) {
        log.info("Endpoint-get-path=" + "/v1/api/songs" + "/{" + id + "}");

        SongGetResponseDto songById = songService.getSongById(id);

        return ResponseEntity.ok().body(songById);
    }

    @PostMapping
    public ResponseEntity<SongCreateResponseDto> saveSong(@RequestBody SongDto songDto) {
        log.info("Endpoint-post-path=" + "/v1/api/songs" + " | BODY: " + songDto);
        validator.validateSaveSongRequest(songDto);

        SongCreateResponseDto songCreateResponseDto = songService.saveSong(songDto);

        return ResponseEntity.ok().body(songCreateResponseDto);
    }

    /**
     * Delete a song(s) metadata. If there is no song metadata for id, do nothing
     * @param csvId CSV of song metadata IDs to remove
     * @return SongDeleteResponseDto
     */
    @DeleteMapping
    public ResponseEntity<SongDeleteResponseDto> deleteSong(@RequestParam("id") String csvId) {
        log.info("Endpoint-delete-path=" + "/v1/api/songs" + " | RequestParams: id=" + csvId);
        return ResponseEntity.ok().body(songService.deleteSongs(csvId));
    }
}
