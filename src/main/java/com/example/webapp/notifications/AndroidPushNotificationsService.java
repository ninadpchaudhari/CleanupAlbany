package com.example.webapp.notifications;


import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Service
public class AndroidPushNotificationsService {

    private static final String FIREBASE_SERVER_KEY = "AAAA68-dUKE:APA91bGs9xgjmLRdod-m9EeopxYEKoNUD67zHEBOraLU5qpSgghUHQRVL52D5FXi0YVjxOYSiKlPqSsVC9NkQgcxGQAfO2eJ-Ft_0qaH19b2G_ydrUb9U-Qq5R-ecagxgnqLY1O6FRqf";

    @Async
    public CompletableFuture<FirebaseResponse> send(HttpEntity<String> entity) {

        RestTemplate restTemplate = new RestTemplate();

        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
        restTemplate.setInterceptors(interceptors);

        FirebaseResponse firebaseResponse = restTemplate.postForObject("https://fcm.googleapis.com/fcm/send", entity, FirebaseResponse.class);

        return CompletableFuture.completedFuture(firebaseResponse);
    }

}