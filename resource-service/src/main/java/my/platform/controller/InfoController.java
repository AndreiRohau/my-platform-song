package my.platform.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import my.platform.service.InfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/info")
@RequiredArgsConstructor
@Log
public class InfoController {

    private final InfoService infoService;

    @GetMapping(value = "/status")
    public ResponseEntity<Map<String, String>> getStatus() {
        log.info("Endpoint-get-path=" + "/info/status");
        Map<String, String> map = infoService.getCurrentStatus();
        return ResponseEntity.ok(map);
    }

    @GetMapping(value = "/status/{service-name}")
    public ResponseEntity<Map<String, String>> checkInterServiceConnection(@PathVariable("service-name") String serviceName) {
        log.info("Endpoint-get-path=" + "/status/{" + serviceName + "}");

        Map<String, String> map = infoService.checkInterServiceConnection(serviceName);

        return ResponseEntity.ok(map);
    }

    @GetMapping(value = "/create/{filename}")
    public ResponseEntity<String> createFile(@PathVariable String filename) {
        log.info("Endpoint-get-path=" + "/create/{" + filename + "}");
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

    @GetMapping(value = "/delete/{filename}")
    public ResponseEntity<String> deleteFile(@PathVariable String filename) {
        log.info("Endpoint-get-path=" + "/delete/{" + filename + "}");
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
