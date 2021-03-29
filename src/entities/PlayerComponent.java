package entities;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;

public class PlayerComponent extends Component {
    public static final double BLOCK_MOVE_SPEED = 0.1;

    private final int blockSize = 20;
    // start on outer right minus block size
    private Vec2 latestPlayerPos = new Vec2(0,580);

    @Override
    public void onUpdate(double tpf) {
        // moves player forward
        latestPlayerPos.x += tpf * BLOCK_MOVE_SPEED;
        entity.translateX(latestPlayerPos.x);
    }
}
