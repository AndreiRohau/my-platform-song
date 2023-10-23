package my.platform.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import my.platform.exception.ResponseServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
@Log
public class InfoServiceImpl implements InfoService {

    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${server.port}")
    private String serverPort;

    private final RestTemplateBuilder restTemplateBuilder;
    private RestTemplate restTemplate;

    @PostConstruct
    private void postConstruct() {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Map<String, String> getCurrentStatus() {
        return Map.of(applicationName, getLocalHost() + ":" + serverPort);
    }

    @Override
    public Map<String, String> checkInterServiceConnection(String serviceName) {
        //todo SET URL
        final String url = "" + "/info/status/" + serviceName;
        log.info("checkInterServiceConnection(); url=" + url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Map<String, String> result = new HashMap<>();
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            result = response.getBody();
        }
        result.put("[REQUESTER]" + applicationName, getLocalHost() + ":" + serverPort);
        log.info("checkInterServiceConnection(); Status from [" + serviceName + "] result=" + result);
        return result;
    }

    private String getLocalHost() {
        try {
            return InetAddress.getLocalHost().toString();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            log.info("Exception during running: InetAddress.getLocalHost().toString()");
            throw ResponseServiceException.init500();
        }
    }
}
