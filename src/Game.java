import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import entities.BlockBuildingComponent;
import entities.PlayerComponent;
import entities.EntityType;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

public class Game extends GameApplication {
    private PlayerComponent playerComponent;
    private Entity player;

    public static String PLAYER_MOVED = "pixelsMoved";

    @Override
    protected void initSettings(GameSettings settings) {
        // TODO refactor to variables
        settings.setWidth(1000);
        settings.setHeight(600);
        settings.setTitle("Dinosaur game");
        settings.setVersion("0.1");
    }

    @Override
    protected void initInput() {
        onKey(KeyCode.RIGHT, () -> {
            player.translateX(5); // move right 5 pixels
            inc(PLAYER_MOVED, +5);
        });

        onKey(KeyCode.LEFT, () -> {
            player.translateX(-5); // move left 5 pixels
            inc(PLAYER_MOVED, -5);
        });

        onKey(KeyCode.UP, () -> {
            player.translateY(-5); // move up 5 pixels
            inc(PLAYER_MOVED, +5);
        });

        onKey(KeyCode.DOWN, () -> {
            player.translateY(5); // move down 5 pixels
            inc(PLAYER_MOVED, +5);
        });
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put(PLAYER_MOVED, -1000);
    }

    @Override
    protected void initGame() {
        playerComponent = new PlayerComponent();

        player = entityBuilder()
                .type(EntityType.Player)
                .at(0,580)
                .view(new Rectangle(20, 20, Color.BLUE))
                .with(new PlayerComponent(), new BlockBuildingComponent())
                .buildAndAttach();
    }

    @Override
    protected void initUI() {
    }

    public static void main(String[] args) {
        launch(args);
    }
}