package com.roboter5123.play.backend.seshservice.messaging.api;
public interface MessageBroadcaster {

    void broadcastSeshUpdate(String sessionCode, Object payload);
}
