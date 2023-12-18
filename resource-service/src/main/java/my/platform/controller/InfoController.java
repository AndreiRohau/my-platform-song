package my.platform.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import my.platform.service.InfoService;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/info")
@RequiredArgsConstructor
@Log
public class InfoController {

    private final InfoService infoService;
    private final BuildProperties buildProperties;

    @GetMapping(value = "/v")
    public ResponseEntity<String> getArtifactVersion() {
        log.info("Endpoint-get-path=" + "/info/v");
        return ResponseEntity.ok(buildProperties.getVersion());
    }

    @GetMapping(value = "/status")
    public ResponseEntity<Map<String, String>> getStatus() {
        log.info("Endpoint-get-path=" + "/info/status");
        Map<String, String> map = infoService.getCurrentStatus();
        return ResponseEntity.ok(map);
    }

    @GetMapping(value = "/status/v2")
    public ResponseEntity<Map<String, String>> getStatusV2() {
        log.info("Endpoint-get-path=" + "/info/status/v2");
        Map<String, String> map = infoService.getCurrentStatus();
        return ResponseEntity.ok(map);
    }

    @GetMapping(value = "/status/{service-name}")
    public ResponseEntity<Map<String, String>> checkInterServiceConnection(@PathVariable("service-name") String serviceName) {
        log.info("Endpoint-get-path=" + "/status/{" + serviceName + "}");

        Map<String, String> map = infoService.checkInterServiceConnection(serviceName);

        return ResponseEntity.ok(map);
    }

    @GetMapping(value = "/ls")
    public ResponseEntity<Set<String>> listFiles(@RequestParam String dir) {
        log.info("Endpoint-get-path=" + "/ls?dir=" + dir + "}");
        Set<String> response = null;
        try (Stream<Path> stream = Files.list(Paths.get(dir))) {
            response = stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<String> createFile(@RequestParam String filename) {
        log.info("Endpoint-get-path=" + "/create?filename=" + filename + "}");
        String response = null;
        try {
            File myObj = new File(filename);
            if (myObj.createNewFile()) {
                response = "File created: " + myObj.getName();
                System.out.println(response);
            } else {
                response = "File already exists.";
                System.out.println(response);
            }
        } catch (IOException e) {
            response = "An error occurred.";
            System.out.println(response);
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<String> deleteFile(@RequestParam String filename) {
        log.info("Endpoint-get-path=" + "/delete?filename=" + filename + "}");
        String response = null;
        File myObj = new File(filename);
        if (myObj.delete()) {
            response = "Deleted the file: " + myObj.getName();
        } else {
            response = "Failed to delete the file.";
        }
        System.out.println(response);
        return ResponseEntity.ok(response);
    }
}
