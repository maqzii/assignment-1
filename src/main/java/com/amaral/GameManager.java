package com.amaral;

import com.amaral.model.Game;
import java.util.List;

public interface GameManager {
    void add(Game game);

    void remove(Game game);

    void changeQuantity(Game game);

    List<Game> getGameList();
} // END OF GameManager
