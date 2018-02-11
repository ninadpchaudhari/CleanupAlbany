package com.example.service;

import org.springframework.stereotype.Service;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.webapp.notifications.AndroidPushNotificationsService;
import com.example.webapp.notifications.FirebaseResponse;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class NotificationService {
	private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    AndroidPushNotificationsService androidPushNotificationsService;

    
    public ResponseEntity<String> send(String fcmtoken,String issueId) {


        JSONObject body = new JSONObject();
        // JsonArray registration_ids = new JsonArray();
        // body.put("registration_ids", registration_ids);
        body.put("to", fcmtoken);
        body.put("priority", "high");
        // body.put("dry_run", true);

        JSONObject notification = new JSONObject();
        notification.put("body", "Body");
        notification.put("title", "IssueId");
        // notification.put("icon", "myicon");

        JSONObject data = new JSONObject();
        data.put("issueId", issueId);
        //data.put("key2", "value2");

        body.put("notification", notification);
        body.put("data", data);

        HttpEntity<String> request = new HttpEntity<>(body.toString());

        CompletableFuture<FirebaseResponse> pushNotification = androidPushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try {
            FirebaseResponse firebaseResponse = pushNotification.get();
            if (firebaseResponse.getSuccess() == 1) {
                log.info("push notification sent ok!");
            } else {
                log.error("error sending push notifications: " + firebaseResponse.toString());
            }
            return new ResponseEntity<>(firebaseResponse.toString(), HttpStatus.OK);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("the push notification cannot be send.", HttpStatus.BAD_REQUEST);
    }
}
}
