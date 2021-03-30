package entities;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;

public class PlayerComponent extends Component {
    public static final float BLOCK_MOVE_SPEED = 0.1F;
    public static final float FALL_SPEED = 5F;

    private int blockSize = 50;

    // start on outer right minus block size
    private Vec2 latestPlayerPos = new Vec2(0,550);
    private boolean isJumping = false;

    @Override
    public void onUpdate(double tpf) {
        // moves player forward
        latestPlayerPos.x += (tpf * BLOCK_MOVE_SPEED);

        if (latestPlayerPos.y >= FXGL.getAppHeight() - blockSize) {
            // so the player does not fall of the map
            latestPlayerPos.y = (float) (FXGL.getAppHeight() - blockSize);
            isJumping = false;
        }
        else {
            // to always pull the player downwards if it is not attached to the ground
            latestPlayerPos.y += (tpf * FALL_SPEED);
//            entity.translateY(tpf * FALL_SPEED);
        }

        entity.translateX(latestPlayerPos.x);
        entity.setY(latestPlayerPos.y);
    }

    public void jump() {
        if(!isJumping) {
            latestPlayerPos.addLocal(0, (blockSize * 2));
            isJumping = true;
        }
    }
}
