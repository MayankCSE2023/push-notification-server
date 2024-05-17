# Firebase Push Notification Service

This project demonstrates a Spring Boot application that sends push notifications using Firebase Cloud Messaging (FCM). Below is a brief summary of the main components and their functionalities:

## Components

### 1. `PushNotificationApplication`
- **Main class**: Bootstraps the Spring Boot application.

### 2. `FCMInitializer`
- **Service class**: Initializes Firebase with credentials specified in the configuration file.
- **Configuration**: Reads the Firebase configuration file path from `application.properties`.
- **Initialization**: Sets Firebase credentials and initializes the Firebase app if it is not already initialized.

### 3. `FCMService`
- **Service class**: Handles the creation and sending of push notifications.
- **Methods**:
  - `sendMessageToToken(NotificationRequest request)`: Sends a notification to a specific device token.
  - `sendAndGetResponse(Message message)`: Sends the message and returns the response.
  - `getPreconfiguredMessageToToken(NotificationRequest request)`: Constructs the message object based on the request.

### 4. `NotificationRequest`
- **Model class**: Represents the notification request payload.
- **Fields**:
  - `title`: The title of the notification.
  - `body`: The body of the notification.
  - `token`: The device token to send the notification to.

### 5. `NotificationResponse`
- **Model class**: Represents the response after sending a notification.
- **Fields**:
  - `status`: HTTP status code of the response.
  - `message`: Response message.

### 6. `FirebasePublisherController`
- **Controller class**: Exposes an endpoint to send notifications.
- **Endpoint**:
  - `POST /notification`: Accepts a `NotificationRequest` body and triggers the sending of a notification via `FCMService`.

## Workflow

1. The application initializes and configures Firebase using `FCMInitializer`.
2. A client sends a `POST` request to `/notification` with a `NotificationRequest` payload.
3. `FirebasePublisherController` handles the request and invokes `FCMService` to send the notification.
4. `FCMService` constructs the message and sends it to the specified device token using Firebase.
5. The controller returns a `NotificationResponse` indicating the status of the operation.

This setup allows the application to send push notifications to devices using Firebase Cloud Messaging.
