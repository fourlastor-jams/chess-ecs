package io.github.fourlastor.game.level.di;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import dagger.Module;
import dagger.Provides;
import io.github.fourlastor.game.di.ScreenScoped;
import io.github.fourlastor.game.level.Layer;
import io.github.fourlastor.game.level.system.ClearScreenSystem;
import io.github.fourlastor.harlequin.system.StageSystem;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier;

@Module
public class LevelModule {

    @Provides
    @ScreenScoped
    public Engine engine(StageSystem stageSystem, ClearScreenSystem clearScreenSystem) {
        Engine engine = new Engine();
        engine.addSystem(clearScreenSystem);
        engine.addSystem(stageSystem);
        return engine;
    }

    @Provides
    public StageSystem stageSystem(Stage stage, @Layers Class<? extends Enum<?>> layers) {
        return new StageSystem(stage, layers);
    }

    @Provides
    @Layers
    public Class<? extends Enum<?>> layersEnum() {
        return Layer.class;
    }

    @Provides
    @ScreenScoped
    public Viewport viewport() {
        return new FitViewport(256f, 256f);
    }

    @Provides
    @ScreenScoped
    public Stage stage(Viewport viewport) {
        return new Stage(viewport);
    }

    @Provides
    @ScreenScoped
    public Camera camera(Viewport viewport) {
        return viewport.getCamera();
    }

    @Provides
    @ScreenScoped
    public MessageDispatcher messageDispatcher() {
        return new MessageDispatcher();
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Layers {}
}
