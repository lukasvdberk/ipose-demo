package game;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import entities.BlockBuildingComponent;
import entities.Player;
import entities.PlayerComponent;
import entities.EntityType;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

public class Game extends GameApplication {
    public static Entity player;
    private PlayerComponent playerComponent;

    @Override
    protected void initSettings(GameSettings settings) {
        // TODO refactor to variables
        settings.setWidth(1000);
        settings.setHeight(600);
        settings.setTitle("Dinosaur game");
        settings.setVersion("0.1");
    }

    @Override
    protected void initGame() {
        playerComponent = new PlayerComponent();

        player = entityBuilder()
                .type(EntityType.Player)
                .at(0,580)
                .view(new Rectangle(20, 20, Color.BLUE))
                .with(new PlayerComponent(), new BlockBuildingComponent())
                .build();

        getGameScene().getViewport().setBounds(0, 0, Integer.MAX_VALUE, getAppHeight());
        getGameScene().getViewport().bindToEntity(player, getAppWidth() / 5, Integer.MAX_VALUE);

        spawnWithScale(player, Duration.seconds(0.86), Interpolators.BOUNCE.EASE_OUT());
    }

    @Override
    protected void initUI() {
    }

    public static void main(String[] args) {
        launch(args);
    }
}