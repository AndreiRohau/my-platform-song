//package my.platform.config;
//
//import my.platform.exception.SongServiceException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//@Configuration
//public class RouteConfig {
//
//    @Autowired
//    private DiscoveryClient discoveryClient;
//
//    @Value("${microservices.structure.services}")
//    private List<String> services;
//
//    @Value("${microservices.structure.services.gateway.name}")
//    private String gatewayName;
//
//    @Bean
//    public RouteStateHolder routeSateHolder() {
//        return new RouteStateHolder(services);
//    }
//
//    public class RouteStateHolder {
//
//        private final List<String> services;
//        private final Map<String, List<ServiceInstance>> serviceToInstancesMap;
//        private ScheduledExecutorService executor;
//
//        private RouteStateHolder(List<String> services) {
//            this.services = services;
//            this.serviceToInstancesMap = new HashMap<>();
//            updateState(services);
//        }
//
//        private void updateState(final List<String> services) {
//            Runnable helloRunnable = () -> {
//                System.out.println("UPDATING ROUTES - START");
//                updateServiceToInstancesMap(services);
//                System.out.println("UPDATING ROUTES - END");
//            };
//
//            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
//            executor.scheduleAtFixedRate(helloRunnable, 0, 30, TimeUnit.SECONDS);
//            this.executor = executor;
//        }
//
//        private void updateServiceToInstancesMap(List<String> services) {
//            for (String service : services) {
//                List<ServiceInstance> instances = discoveryClient.getInstances(service);
//                serviceToInstancesMap.put(service, instances);
//            }
//            for (String e : services) {
//                Optional<ServiceInstance> instance = serviceToInstancesMap.get(e).stream().findAny();
//                String uri = null;
//                if (instance.isPresent()) {
//                    uri = instance.get().getUri().toString();
//                }
//                System.out.println(e + " = " + uri);
//            }
//        }
//
//        public String getGatewayUriString() {
//            return serviceToInstancesMap.get(gatewayName).stream()
//                    .findAny()
//                    .orElseThrow(SongServiceException::init504)
//                    .getUri()
//                    .toString();
//        }
//    }
//}
