package com.example.demo.delegate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MatchesDelegate {

    private final RestTemplate restTemplate = new RestTemplate();

    @HystrixCommand(fallbackMethod = "call_Fallback",
        commandProperties = {
            // set timeout to 1 seconds
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
            // set circuit breaker to 5 requests
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            // set circuit breaker to 50% error rate
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            // set circuit breaker to 30 seconds
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "30000")
        })
    public String callTeam(int id) {
        String result = restTemplate
                .getForObject("http://localhost:6060/teams/" + id, String.class);
        return result;
    }

    public String call_Fallback(String id) {
        System.out.println("NO RESPONSE!!! fallback route enabled...");
        return "CIRCUIT BREAKER ENABLED!!! No Response From Team-Service at this moment. " +
                " Service will be back shortly - " + id;
    }

}
