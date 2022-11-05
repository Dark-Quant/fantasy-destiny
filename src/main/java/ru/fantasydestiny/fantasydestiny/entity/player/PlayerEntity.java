package ru.fantasydestiny.fantasydestiny.entity.player;

import ru.fantasydestiny.fantasydestiny.entity.Attacking;
import ru.fantasydestiny.fantasydestiny.entity.Entity;
import ru.fantasydestiny.fantasydestiny.entity.InteractionResult;
import ru.fantasydestiny.fantasydestiny.entity.LivingEntity;
import ru.fantasydestiny.fantasydestiny.world.Location;

public class PlayerEntity extends LivingEntity implements Attacking {
    private final String nickname;

    public PlayerEntity(String nickname, Location location) {
        super(location);
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public boolean attack(Entity target) {
        if (target instanceof LivingEntity) {
            return true;
        }
        return false;
    }

    public InteractionResult interact(Entity target) {
        return InteractionResult.PASS;
    }
}
