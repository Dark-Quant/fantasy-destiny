package ru.fantasydestiny.fantasydestiny.server;

import ru.fantasydestiny.fantasydestiny.entity.player.Player;
import ru.fantasydestiny.fantasydestiny.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.List;

public class PlayerManagement {
    private static List<PlayerEntity> playersList = new ArrayList<>();

    public static List<PlayerEntity> getPlayersList() {
        return playersList;
    }

    public static PlayerEntity getPlayer(String nickname) {
        for (PlayerEntity player : playersList) {
            if (player.getNickname().equals(nickname)) {
                return player;
            }
        }
        return null;
    }
}
