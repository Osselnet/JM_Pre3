package web;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import web.model.User;

import java.util.List;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        final String url = "http://91.241.64.178:7081/api/users";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        forEntity.getHeaders().get("Set-Cookie").stream().forEach(System.out::println);
        System.out.println(forEntity.getBody());

        HttpHeaders headers = new HttpHeaders();
        List<String> cookies = forEntity.getHeaders().get("Set-Cookie");
        headers.add("Cookie", cookies.get(cookies.size() - 1));

        User user = new User((long) 3, "James", "Brown", (byte) 13);

        HttpEntity<User> request1 = new HttpEntity<>(user, headers);
        ResponseEntity response = restTemplate.exchange(url, HttpMethod.POST, request1, String.class);
        System.out.println(response.getBody());

        user.setName("Thomas");
        user.setLastName("Shelby");
        HttpEntity<User> request2 = new HttpEntity<>(user, headers);
        response = restTemplate.exchange(url, HttpMethod.PUT, request2, String.class);
        System.out.println(response.getBody());

        response = restTemplate.exchange(url + "/3", HttpMethod.DELETE, new HttpEntity<>(headers), String.class);
        System.out.println(response.getBody());
    }
}