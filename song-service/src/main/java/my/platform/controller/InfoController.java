package my.platform.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import my.platform.service.InfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
