package com.evgeniypodprugin.rest_template_learning.restClient;

import com.evgeniypodprugin.rest_template_learning.model.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RestTemplateClient {

    private static final String URL = "http://91.241.64.178:7081/api/users";
    private static final String URL_WITH_ID = "http://91.241.64.178:7081/api/users/{id}";

    private static final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        RestTemplateClient client = new RestTemplateClient();
        client.process();

    }

    private void process(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        //GET
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity,String.class);
        List<String> cookie = response.getHeaders().get("Set-Cookie");
        System.out.println(response);

        //POST
        headers.set("cookie", String.join(";", cookie));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE); //*
        User james = new User(3L,"James","Brown",(byte)25);
        HttpEntity<User> requestBody = new HttpEntity<>(james, headers);
        ResponseEntity<String> postedJames = restTemplate.postForEntity(URL,requestBody,String.class);
        System.out.println(postedJames.getBody());

        //PUT
        headers.set("cookie", String.join(";", cookie));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        User thomas = new User(3L, "Thomas", "Shelby",(byte)40);
        HttpEntity<User> requestBody1 = new HttpEntity<>(thomas,headers);
        ResponseEntity<String> postedThomas = restTemplate.exchange(URL,HttpMethod.PUT,requestBody1,String.class);
        System.out.println(postedThomas.getBody());

        //Delete
        headers.set("cookie", String.join(";", cookie));
        Map<String, Integer> toDelete = new HashMap<>();
       toDelete.put("id",3);
        HttpEntity<User> httpDelete = new HttpEntity<>(thomas,headers);
        ResponseEntity<String> deletedThomas = restTemplate.exchange(URL_WITH_ID, HttpMethod.DELETE,
                httpDelete, String.class,toDelete);
        System.out.println(deletedThomas.getBody());

        //Result!
        System.out.println(postedJames.getBody() + postedThomas.getBody() + deletedThomas.getBody());

    }

}
