package main;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.input.virtual.VirtualButton;
import com.almasb.fxgl.physics.PhysicsComponent;
import main.entities.blocks.BlockBuildingComponent;
import main.entities.player.PlayerComponent;
import main.entities.EntityType;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import main.entities.player.PlayerFactory;

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

        player = PlayerFactory.createPlayerEntity(playerComponent);

        // binds player to not go outside of box
        getGameScene().getViewport().setBounds(0, 0, Integer.MAX_VALUE, getAppHeight());
        // updates viewport on player move. like a camera
        getGameScene().getViewport().bindToEntity(player, getAppWidth() / 6, getAppHeight());
    }
    @Override
    protected void initPhysics() {
        onCollisionBegin(EntityType.Player, EntityType.Block, (player, wall) -> {
            showMessage("Game over! Restart", new Runnable() {
                @Override
                public void run() {
                    playerComponent = new PlayerComponent();
                    getGameController().startNewGame();
                }
            });
        });
    }

    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("Jump") {
            @Override
            protected void onActionBegin() {
                playerComponent.jump();
            }
        }, KeyCode.SPACE);
    }

    @Override
    protected void initUI() {
        FXGL.getGameScene().setBackgroundRepeat("background.jpeg");
    }

    public static void main(String[] args) {
        launch(args);
    }
}