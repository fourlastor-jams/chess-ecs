package io.github.fourlastor.game.level;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import io.github.fourlastor.game.di.ScreenScoped;
import io.github.fourlastor.harlequin.component.ActorComponent;
import javax.inject.Inject;

/**
 * Factory to create various entities: player, buildings, enemies..
 */
@ScreenScoped
public class EntitiesFactory {

    private final TextureAtlas textureAtlas;
    private final Stage stage;

    @Inject
    public EntitiesFactory(TextureAtlas textureAtlas, Stage stage) {
        this.textureAtlas = textureAtlas;
        this.stage = stage;
    }

    public Entity board() {
        Entity entity = new Entity();
        Image image = new Image(textureAtlas.findRegion("chess/board"));
        image.setPosition(stage.getWidth() / 2f, stage.getHeight() / 2f, Align.center);
        entity.add(new ActorComponent(image, Layer.BOARD));
        return entity;
    }
}
