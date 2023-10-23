package my.platform.service;

import java.util.Map;

public interface InfoService {
    Map<String, String> getCurrentStatus();

    Map<String, String> checkInterServiceConnection(String serviceName);
}
