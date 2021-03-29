package entities;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.CollidableComponent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

public class BlockBuildingComponent extends Component {
    private double lastWall = 1000;

    @Override
    public void onUpdate(double tpf) {
        if (lastWall - entity.getX() < FXGL.getAppWidth()) {
            buildWalls();
        }
    }

    private Rectangle wallView(double width, double height) {
        Rectangle wall = new Rectangle(width, height, Color.GREEN);
        wall.setArcWidth(25);
        wall.setArcHeight(25);
        return wall;
    }

    private void buildWalls() {
        double height = FXGL.getAppHeight();
        double distance = height / 2;

        for (int i = 1; i <= 10; i++) {
            double topHeight = Math.random() * (height - distance);

            entityBuilder()
                    .at(lastWall + i * 500, FXGL.getAppHeight() - topHeight)
                    .type(EntityType.Block)
                    .viewWithBBox(wallView(50, topHeight))
                    .with(new CollidableComponent(true))
                    .buildAndAttach();
        }

        lastWall += 10 * 500;
    }
}
