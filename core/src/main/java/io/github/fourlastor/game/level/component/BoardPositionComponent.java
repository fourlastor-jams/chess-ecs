package io.github.fourlastor.game.level.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.GridPoint2;

public class BoardPositionComponent implements Component {
    public final GridPoint2 position;

    public BoardPositionComponent(int x, int y) {
        this.position = new GridPoint2(x, y);
    }
}
