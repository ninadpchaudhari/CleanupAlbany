package com.example.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.jasper.tagplugins.jstl.core.Url;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.exmaple.misc.*;


@Service
public class NotificationService {

	public static boolean send(String fcmtoken,String issueId) {
		try {
			   System.out.println("Got issueid in notification :" + issueId);
			   String androidFcmUrl= "https://fcm.googleapis.com/fcm/send";
			   try {
				   androidFcmUrl = URLEncoder.encode(androidFcmUrl,"UTF-8");
				} catch (UnsupportedEncodingException e) {
					System.out.println("/search POST Failed encode search string");
					e.printStackTrace();
				}
			   
			   

			   
			   
				MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
				headers.add("Authorization", "key=AAAA68-dUKE:APA91bGs9xgjmLRdod-m9EeopxYEKoNUD67zHEBOraLU5qpSgghUHQRVL52D5FXi0YVjxOYSiKlPqSsVC9NkQgcxGQAfO2eJ-Ft_0qaH19b2G_ydrUb9U-Qq5R-ecagxgnqLY1O6FRqf");
				headers.add("Content-Type", "application/json");

				RestTemplate restTemplate = new RestTemplate();
				List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
				interceptors.add(new LoggingRequestInterceptor());
				restTemplate.setInterceptors(interceptors);
				restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

				
/*
			   JSONObject msg = new JSONObject();
			   
			   JSONObject json = new JSONObject();

			   msg.put("title", "Issue");
			   msg.put("detail", issueId);
			  msg.put("notificationType", "Test");

			   json.put("data", msg);
			  // json.put("to", fcmtoken);
			   
			   System.out.println(json.toString());
			   HttpEntity<String> request = new HttpEntity<String>(headers,json);
			   //HttpEntity<String> httpEntity = new HttpEntity<String>(json.toString(),headers);
			   ResponseEntity<String> response = restTemplate.postForEntity(androidFcmUrl, request, String.class);
			   //String response = restTemplate.postForObject(androidFcmUrl,httpEntity,String.class);
			   System.out.println(response);*/
				URL obj = new URL(androidFcmUrl);
				HttpURLConnection  con = (HttpURLConnection)obj.openConnection();
				con.setRequestMethod("POST");
				//con.setRequestProperty("User-Agent", USER_AGENT);
				con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
				con.setRequestProperty("Authorization", "key=AAAA68-dUKE:APA91bGs9xgjmLRdod-m9EeopxYEKoNUD67zHEBOraLU5qpSgghUHQRVL52D5FXi0YVjxOYSiKlPqSsVC9NkQgcxGQAfO2eJ-Ft_0qaH19b2G_ydrUb9U-Qq5R-ecagxgnqLY1O6FRqf");
				
				String urlParameters = "{ \"data\":{ \"title\" : \"Issu Hi rgregergeTher eId\", \"detail\" : \" " + issueId+"\" }, \"to\": \" " + fcmtoken+ "\"}";
				
				// Send post request
				con.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				wr.writeBytes(urlParameters);
				wr.flush();
				wr.close();

				int responseCode = con.getResponseCode();
				System.out.println("\nSending 'POST' request to URL : " + androidFcmUrl);
				System.out.println("Post parameters : " + urlParameters);
				System.out.println("Response Code : " + responseCode);

				BufferedReader in = new BufferedReader(
				        new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				//print result
				System.out.println(response.toString());
		
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
