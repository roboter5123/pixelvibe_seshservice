package com.roboter5123.play.backend.webinterface.service.api;
import com.roboter5123.play.backend.game.api.Game;
import com.roboter5123.play.backend.game.api.GameMode;
import com.roboter5123.play.backend.messaging.model.CommandStompMessage;
import com.roboter5123.play.backend.messaging.model.StompMessage;
import com.roboter5123.play.backend.webinterface.service.exception.NoSuchSessionException;
import com.roboter5123.play.backend.webinterface.service.exception.TooManySessionsException;

import java.util.Map;
import java.util.Optional;

public interface GameService {

    Optional<Game> createSession(GameMode gameMode) throws TooManySessionsException;

    Map<String, Object> joinGame(String sessionCode, String playerName) throws NoSuchSessionException;

    Optional<Game> getGame(String sessionCode);

    StompMessage sendCommandToGame(CommandStompMessage message, String sessionCode) throws NoSuchSessionException;
}
