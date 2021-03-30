package entities;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;

public class PlayerComponent extends Component {
    public static final float BLOCK_MOVE_SPEED = 200F;
    public static final float FALL_SPEED = 100F;

    private int blockSize = 50;

    // start on outer right minus block size
    private Vec2 latestPlayerPos = new Vec2(0,550);
    private boolean isJumping = false;
    private float amountToJump = 0F;

    @Override
    public void onUpdate(double tpf) {
        // moves player forward
        latestPlayerPos.x += (tpf * BLOCK_MOVE_SPEED);

        if(isJumping) {
            // jump the player
            float amountJumpedInframe = (amountToJump * (float) tpf) * 2;
            amountToJump -= amountJumpedInframe;
            latestPlayerPos.y -= amountJumpedInframe;
            if(amountToJump <= 4) {
                isJumping = false;
                latestPlayerPos.y = (float) (FXGL.getAppHeight() - blockSize);
            }
        }

        if (latestPlayerPos.y >= FXGL.getAppHeight() - blockSize) {
            // so the player does not fall of the map
            latestPlayerPos.y = (float) (FXGL.getAppHeight() - blockSize);
        }

        else {
            // to always pull the player downwards if it is not attached to the ground
            latestPlayerPos.y += (tpf * FALL_SPEED);
        }

        entity.setX(latestPlayerPos.x);
        // translate Y will increase it exponentially do it won't work
        entity.setY(latestPlayerPos.y);
    }

    public void jump() {
        if(!isJumping) {
            amountToJump = blockSize * 4;
            isJumping = true;
        }
    }
}
