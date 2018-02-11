package com.example.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.exmaple.misc.*;


@Service
public class NotificationService {

	public static boolean send(String fcmtoken,String issueId) {
		try {
			   System.out.println("Got issueid in notification :" + issueId);
			   String androidFcmUrl="https://fcm.googleapis.com/fcm/send";

			   RestTemplate restTemplate = new RestTemplate();
			   List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
				interceptors.add(new LoggingRequestInterceptor());
				restTemplate.setInterceptors(interceptors);
			   HttpHeaders httpHeaders = new HttpHeaders();
			   httpHeaders.set("Authorization", "key=" + "AAAA68-dUKE:APA91bGs9xgjmLRdod-m9EeopxYEKoNUD67zHEBOraLU5qpSgghUHQRVL52D5FXi0YVjxOYSiKlPqSsVC9NkQgcxGQAfO2eJ-Ft_0qaH19b2G_ydrUb9U-Qq5R-ecagxgnqLY1O6FRqf");
			   httpHeaders.set("Content-Type", "application/json");
			   JSONObject msg = new JSONObject();
			   HashMap<String,String> myMap = new HashMap<String,String>();
			   myMap.put("to", fcmtoken);
			   JSONObject json = new JSONObject(myMap);

			   msg.put("title", "Issue");
			   msg.put("detail", issueId);
			   //msg.put("notificationType", "Test");

			   json.put("data", msg);
			  // json.put("to", fcmtoken);
			   System.out.println(json.toString());
			   HttpEntity<String> httpEntity = new HttpEntity<String>(json.toString(),httpHeaders);
			   String response = restTemplate.postForObject(androidFcmUrl,httpEntity,String.class);
			   System.out.println(response);
			} catch (JSONException e) {
			   e.printStackTrace();
			   return false;
			}
		return true;
	}
}
