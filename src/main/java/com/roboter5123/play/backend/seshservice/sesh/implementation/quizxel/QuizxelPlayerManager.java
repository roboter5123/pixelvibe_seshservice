package com.roboter5123.play.backend.seshservice.sesh.implementation.quizxel;
import com.roboter5123.play.backend.seshservice.sesh.api.PlayerManager;
import com.roboter5123.play.backend.seshservice.sesh.exception.PlayerAlreadyJoinedException;
import com.roboter5123.play.backend.seshservice.sesh.implementation.quizxel.model.QuizxelPlayer;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizxelPlayerManager implements PlayerManager {

    private final Integer maxPlayers;
    @Getter
    @Setter
    private boolean isJoinable;
    private final Map<String, QuizxelPlayer> players;
    private String hostId;

    public QuizxelPlayerManager(final Integer maxPlayers) {

        this.maxPlayers = maxPlayers;
        this.isJoinable = false;
        this.players = new HashMap<>();
    }

    @Override
    public boolean joinAsHost(String socketId) {

        if (hasHostJoined()) {

            return false;
        }
        this.hostId = socketId;
        this.isJoinable = true;
        return true;
    }

    @Override
    public boolean joinAsPlayer(String playerName, String socketId) {

        if (hasPlayerAlreadyJoinedByName(playerName)) return false;

        QuizxelPlayer player = new QuizxelPlayer(playerName, socketId);
        this.players.put(player.getPlayerId(), player);
        this.isJoinable = !isSeshFull();

        return true;
    }

    private boolean hasPlayerAlreadyJoinedByName(String playerName) {

        boolean playerHasJoinedAlready = this.players.values().stream().anyMatch(player -> player.getPlayerName().equals(playerName));
        return playerHasJoinedAlready || (playerName.equals("Host") && (this.hostId != null));
    }

    @Override
    public boolean hasPlayerAlreadyJoinedByPlayerId(String playerId) {

        return this.players.containsKey(playerId);
    }

    @Override
    public boolean hasHostJoined() throws PlayerAlreadyJoinedException {

        return this.hostId != null;
    }

    @Override
    public boolean isSeshFull() {

        return this.players.size() >= maxPlayers;
    }

    @Override
    public List<QuizxelPlayer> getPlayers() {

        return new ArrayList<>(this.players.values());
    }

    @Override
    public boolean isVIP(String playerName) {

        return this.players.get(playerName).getVip();
    }

    @Override
    public boolean setVIP(String playerName) {

        if (this.players.values().stream().anyMatch(QuizxelPlayer::getVip)) return false;

        this.players.get(playerName).setVip(true);
        return true;
    }

    @Override
    public boolean hasVIP() {

        return this.players.values().stream().anyMatch(QuizxelPlayer::getVip);
    }

}
