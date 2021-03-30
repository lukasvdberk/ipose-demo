package entities;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.CollidableComponent;
import game.Game;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.almasb.fxgl.dsl.FXGL.spawnWithScale;
import static com.almasb.fxgl.dsl.FXGLForKtKt.runOnce;

public class BlockBuildingComponent extends Component {
    private final double wallHeight = 50;
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
                    .viewWithBBox(new Rectangle(25, wallHeight, Color.GREEN))
                    .collidable()
                    .buildAndAttach();

            lastWall += randomDistance;
        }
    }

    private double getRandomDistance() {
        return Math.random() * (201) + 400;
    }
}
