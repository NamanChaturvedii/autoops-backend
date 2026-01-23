package com.autoops.autoops_backend.infrastructure.n8n;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Map;



//first we make this file simple and hardcoded

@Component
public class N8nClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private  final String webhookUrl;
    private  final String internalSecret;

    public N8nClient(
            @Value("${n8n.webhook.url}") String webhookUrl,
            @Value("${n8n.internal.secret}") String internalSecret
    ){
        this.webhookUrl = webhookUrl;
        this.internalSecret = internalSecret;
    }




//    private static  final String N8N_WEBHOOK_URL =
//            "http://localhost:5678/webhook/autoops/execute";


    public void triggerWorkflow(String workflowId,String executionId){
        Map<String,Object> payload = Map.of(
                "workflowId",workflowId,
                "executionId",executionId
        );

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-AUTOOPS-SECRET",internalSecret);

        HttpEntity<Map<String,Object>> request =
                new HttpEntity<>(payload,headers);

        restTemplate.postForEntity(
                webhookUrl,
                request,
                Void.class
        );
    }

}
