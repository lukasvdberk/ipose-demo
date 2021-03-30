package main.entities.player;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import main.entities.blocks.BlockBuildingComponent;
import main.entities.EntityType;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.almasb.fxgl.dsl.FXGL.getAppHeight;

public class PlayerFactory {
    public static Entity createPlayerEntity(PlayerComponent playerComponent) {
        return entityBuilder()
                .type(EntityType.Player)
                .with(playerComponent, new BlockBuildingComponent())
                .at(0,getAppHeight() - PlayerComponent.BLOCK_SIZE)
                .viewWithBBox("rutten.jpeg")
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }
}
