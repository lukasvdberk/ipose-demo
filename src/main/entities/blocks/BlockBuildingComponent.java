package main.entities.blocks;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.CollidableComponent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.entities.EntityType;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.almasb.fxgl.dsl.FXGL.spawnWithScale;

public class BlockBuildingComponent extends Component {
    private final double wallHeight = 125;
    private double lastWall = FXGL.getAppWidth() / 5;

    @Override
    public void onUpdate(double tpf) {
        if (lastWall - entity.getX() < FXGL.getAppWidth()) {
            buildWalls();
        }
    }

    private void buildWalls() {
        for (int i = 1; i <= 10; i++) {
            double randomDistance = getRandomDistance();
            // builds new walls to jump over
            entityBuilder()
                    .at(lastWall + randomDistance, FXGL.getAppHeight() - wallHeight)
                    .type(EntityType.Block)
                    .viewWithBBox("spuit.jpeg")
                    .with(new CollidableComponent(true))
                    .buildAndAttach();

            lastWall += randomDistance;
        }
    }

    private double getRandomDistance() {
        return Math.random() * (201) + 400;
    }
}
