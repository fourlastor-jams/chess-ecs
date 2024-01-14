package io.github.fourlastor.game.level.di;

import com.badlogic.ashley.core.ComponentMapper;
import dagger.Module;
import dagger.Provides;
import io.github.fourlastor.game.di.ScreenScoped;
import io.github.fourlastor.game.level.component.BoardPositionComponent;
import io.github.fourlastor.harlequin.component.ActorComponent;

@Module
public class EcsModule {

    @Provides
    @ScreenScoped
    public ComponentMapper<ActorComponent> actorComponent() {
        return ComponentMapper.getFor(ActorComponent.class);
    }

    @Provides
    @ScreenScoped
    public ComponentMapper<BoardPositionComponent> boardPositionComponent() {
        return ComponentMapper.getFor(BoardPositionComponent.class);
    }
}
