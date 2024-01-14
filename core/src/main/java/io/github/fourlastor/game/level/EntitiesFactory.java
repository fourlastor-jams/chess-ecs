package io.github.fourlastor.game.level;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import io.github.fourlastor.game.di.ScreenScoped;
import io.github.fourlastor.game.level.board.BoardSettings;
import io.github.fourlastor.game.level.component.BoardPositionComponent;
import io.github.fourlastor.harlequin.component.ActorComponent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;

/**
 * Factory to create various entities: player, buildings, enemies..
 */
@ScreenScoped
public class EntitiesFactory {

    private static final Color BLACK_COLOR = new Color(0x057137ff);
    private static final Color WHITE_COLOR = new Color(0xfcf4e1ff);

    private final TextureAtlas textureAtlas;
    private final BoardSettings boardSettings;
    private final Drawable blackBoard;
    private final Drawable whiteBoard;

    @Inject
    public EntitiesFactory(TextureAtlas textureAtlas, BoardSettings boardSettings) {
        this.textureAtlas = textureAtlas;
        this.boardSettings = boardSettings;
        TextureRegionDrawable whitePixel = new TextureRegionDrawable(textureAtlas.findRegion("whitePixel"));
        blackBoard = whitePixel.tint(BLACK_COLOR);
        whiteBoard = whitePixel.tint(WHITE_COLOR);
    }

    public List<Entity> board() {
        ArrayList<Entity> entities = new ArrayList<>();
        boolean black = true;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Entity entity = new Entity();
                Image image = new Image(black ? blackBoard : whiteBoard);
                image.setSize(boardSettings.boardSize, boardSettings.boardSize);
                entity.add(new ActorComponent(image, Layer.BOARD));
                entity.add(new BoardPositionComponent(x, y));
                entities.add(entity);
                black = !black;
            }
            black = !black;
        }
        return entities;
    }

    public List<Entity> pieces() {
        return Arrays.asList(
                piece(0, 0, "white_rook"),
                piece(1, 0, "white_knight"),
                piece(2, 0, "white_bishop"),
                piece(3, 0, "white_queen"),
                piece(4, 0, "white_king"),
                piece(5, 0, "white_bishop"),
                piece(6, 0, "white_knight"),
                piece(7, 0, "white_rook"),
                piece(0, 1, "white_pawn"),
                piece(1, 1, "white_pawn"),
                piece(2, 1, "white_pawn"),
                piece(3, 1, "white_pawn"),
                piece(4, 1, "white_pawn"),
                piece(5, 1, "white_pawn"),
                piece(6, 1, "white_pawn"),
                piece(7, 1, "white_pawn"),
                piece(0, 7, "black_rook"),
                piece(1, 7, "black_knight"),
                piece(2, 7, "black_bishop"),
                piece(3, 7, "black_queen"),
                piece(4, 7, "black_king"),
                piece(5, 7, "black_bishop"),
                piece(6, 7, "black_knight"),
                piece(7, 7, "black_rook"),
                piece(0, 6, "black_pawn"),
                piece(1, 6, "black_pawn"),
                piece(2, 6, "black_pawn"),
                piece(3, 6, "black_pawn"),
                piece(4, 6, "black_pawn"),
                piece(5, 6, "black_pawn"),
                piece(6, 6, "black_pawn"),
                piece(7, 6, "black_pawn"));
    }

    private Entity piece(int x, int y, String name) {
        Entity entity = new Entity();
        Image image = new Image(textureAtlas.findRegion("chess/" + name));
        entity.add(new ActorComponent(image, Layer.PIECES));
        entity.add(new BoardPositionComponent(x, y));
        return entity;
    }
}
