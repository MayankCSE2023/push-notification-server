package com.firebase.pushnotification.pushnotification.controller;


import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.firebase.pushnotification.pushnotification.model.NotificationRequest;
import com.firebase.pushnotification.pushnotification.model.NotificationResponse;
import com.firebase.pushnotification.pushnotification.service.FCMService;
import com.google.firebase.messaging.FirebaseMessagingException;

import java.util.concurrent.ExecutionException;

@RestController
public class FirebasePublisherController {
    
	@Autowired
    private FCMService fcmService;

    @PostMapping("/notification")
    public ResponseEntity sendNotification(@RequestBody NotificationRequest request) throws ExecutionException, InterruptedException, FirebaseMessagingException {
        fcmService.sendMessageToToken(request);
        return new ResponseEntity<>(new NotificationResponse(HttpStatus.OK.value(),
        		"Notification has been sent."), HttpStatus.OK);
    }

}