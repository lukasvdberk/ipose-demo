package entities;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import javafx.util.Duration;

public class PlayerComponent extends Component {
    public static float BLOCK_MOVE_SPEED = 200F;
    public static float FALL_SPEED = 100F;

    private final int BLOCK_SIZE = 50;

    // start on outer right minus block size
    private Vec2 latestPlayerPos = new Vec2(0,550);
    private boolean isJumping = false;
    private float amountToJump = 0F;

    public PlayerComponent() {
        // move the player every 10 seconds faster to increase speed
        FXGL.getGameTimer().runAtInterval(() -> {
            BLOCK_MOVE_SPEED *= 1.2;
            FALL_SPEED *= 1.2;
            FXGL.showMessage("Increased speed!");
        }, Duration.seconds(10));
    }

    @Override
    public void onUpdate(double tpf) {
        movePlayerToRight(tpf);
        updateY(tpf);

        entity.setX(latestPlayerPos.x);
        // translate Y will increase it exponentially do it won't work
        entity.setY(latestPlayerPos.y);
    }

    private void movePlayerToRight(double tpf) {
        latestPlayerPos.x += (tpf * BLOCK_MOVE_SPEED);
    }

    private void updateY(double tpf) {
        if(isJumping) {
            jumpPlayerForGivenFrame(tpf);
        }

        if (touchesGround()) {
            setPlayerToGround();
        }

        else {
            pullPlayerDownWords(tpf);
        }
    }

    private void jumpPlayerForGivenFrame(double tpf) {
        // jump the player
        float amountJumpedInframe = (amountToJump * (float) tpf) * (FALL_SPEED * (float) tpf);
        amountToJump -= amountJumpedInframe;
        latestPlayerPos.y -= amountJumpedInframe;

        if(amountToJump <= 10) {
            isJumping = false;
            latestPlayerPos.y = (float) (FXGL.getAppHeight() - BLOCK_SIZE);
        }
    }


    private boolean touchesGround() {
        return latestPlayerPos.y >= FXGL.getAppHeight() - BLOCK_SIZE;
    }

    private void setPlayerToGround() {
        latestPlayerPos.y = (float) (FXGL.getAppHeight() - BLOCK_SIZE);
    }

    private void pullPlayerDownWords(double tpf) {
        latestPlayerPos.y += (tpf * FALL_SPEED);
    }

    public void jump() {
        if(!isJumping) {
            amountToJump = BLOCK_SIZE * 4;
            isJumping = true;
        }
    }
}
