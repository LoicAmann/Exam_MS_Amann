package com.example.demo.delegate;

import com.example.demo.model.TeamModel;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TeamDelegate {

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
    public String callPlayers(TeamModel teamModel) {
        String result = "";
        for (int i = 1; i < teamModel.getJoueurs().size() + 1; i++) {
            result += restTemplate.getForObject("http://localhost:7070/players/" + i, String.class);
        }
        return result;
    }

    public String call_Fallback(TeamModel teamModel) {
        System.out.println("call_Fallback Method invoked for team-service");

        return "CIRCUIT BREAKER ENABLED!!! No Response From Joueur-Service" +
                " Service will be back shortly - " ;
    }

}
