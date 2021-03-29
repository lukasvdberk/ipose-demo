package entities;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.TransformComponent;

public class Player extends Entity {
    // note that this component is injected automatically
    private TransformComponent position;
}
