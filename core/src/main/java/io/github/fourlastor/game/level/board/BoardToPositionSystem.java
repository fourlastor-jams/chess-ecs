package io.github.fourlastor.game.level.board;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import io.github.fourlastor.game.level.component.BoardPositionComponent;
import io.github.fourlastor.harlequin.component.ActorComponent;
import javax.inject.Inject;

public class BoardToPositionSystem extends IteratingSystem {

    private static final Family BOARD_POSITION =
            Family.all(BoardPositionComponent.class, ActorComponent.class).get();

    private final Stage stage;
    private final BoardSettings boardSettings;
    private final ComponentMapper<ActorComponent> actors;
    private final ComponentMapper<BoardPositionComponent> boardPositions;

    @Inject
    public BoardToPositionSystem(
            Stage stage,
            BoardSettings boardSettings,
            ComponentMapper<ActorComponent> actors,
            ComponentMapper<BoardPositionComponent> boardPositions) {
        super(BOARD_POSITION);
        this.stage = stage;
        this.boardSettings = boardSettings;
        this.actors = actors;
        this.boardPositions = boardPositions;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        float width = stage.getWidth();
        float height = stage.getHeight();
        float left = width / 2 - boardSettings.boardSize * 4;
        float bottom = height / 2 - boardSettings.boardSize * 4;
        BoardPositionComponent boardPosition = boardPositions.get(entity);
        Actor actor = actors.get(entity).actor;
        actor.setPosition(
                left + boardPosition.position.x * boardSettings.boardSize + boardSettings.boardSize / 2,
                bottom + boardPosition.position.y * boardSettings.boardSize + boardSettings.boardSize / 2,
                Align.center);
    }
}
