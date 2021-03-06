package com.amaral;

import com.amaral.model.Game;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GameManagementService implements GameManager {
    private List<Game> gameList = new ArrayList<>();

    @Override
    public void add(@Observes @AddEvent Game game) { // @Observes will identify the event parameter of each method
        int newQuantity = 0;

        // Loops through the Game list and updates values of current game if it already exists
        for(Game games : gameList) {
            if(games.getName().equals(game.getName())) {
                newQuantity = games.getQuantity();
            }
        }
        // removeIf statement works as its own if condition and will remove Game list if condition is met
        gameList.removeIf(g -> g.getName().equals(game.getName()));
        gameList.add(new Game(game.getName(), game.getPrice(), newQuantity + 1, game.getPublisher()));  // Adds new Game with updated values
    } // END OF add()

    @Override
    public void remove(@Observes @RemoveEvent Game game) {
        gameList.remove(game);
    } // END OF remove()

    @Override
    public void changeQuantity(@Observes @ChangeQuantityEvent Game game) {
        int updatedQuantity = 0;
        double price = 0;
        Game.Publisher publisher = null; // Otherwise publisher could not be initialized properly

        for(Game games : gameList) {
            if(games.getName().equals(game.getName())) {
                updatedQuantity = games.getQuantity() + game.getQuantity();
                price = games.getPrice();
                publisher = games.getPublisher();
            }
        }

        gameList.removeIf(g -> g.getName().equals(game.getName()));
        gameList.add(new Game(game.getName(), price, updatedQuantity, publisher));
    } // END OF changeQuantity()

    @Override
    public List<Game> getGameList() {
        return gameList;
    } // END OF getGameList()
} // END OF GameManagementService