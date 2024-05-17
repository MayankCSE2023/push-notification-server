package com.firebase.pushnotification.pushnotification.service;

import java.time.Duration;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.firebase.pushnotification.pushnotification.model.NotificationRequest;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class FCMService {
	private Logger logger = LoggerFactory.getLogger(FCMService.class);

	public void sendMessageToToken(NotificationRequest request) throws InterruptedException, ExecutionException, FirebaseMessagingException {
		Message message = getPreconfiguredMessageToToken(request); // build the message
		Gson gson = new GsonBuilder().setPrettyPrinting().create();  //just for logging(optional) 
		String jsonOutput = gson.toJson(message);                    
		String response = sendAndGetResponse(message);               // send the message
		logger.info("Sent message to token. Device token: " + request.getToken() + ", " + response + " msg " + jsonOutput);
	}

	private String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException, FirebaseMessagingException {
		return FirebaseMessaging.getInstance().send(message);
	}

	private Message getPreconfiguredMessageToToken(NotificationRequest request) {
		if (request.getToken() == null || request.getToken().isEmpty()) {
			throw new IllegalArgumentException("Token must be specified");
		}
		return Message.builder()
				.setNotification(Notification.builder()
						.setTitle(request.getTitle())
						.setBody(request.getBody())
						.build())
				.setToken(request.getToken())
				.build();
	}
}