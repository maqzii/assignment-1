package com.amaral;

import com.amaral.interceptor.Logged;
import com.amaral.model.Game;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Model
@Named("gameBean") // @Named makes the CDI class available to the JSF
@RequestScoped // Destroys the bean lifetime before the page closes
public class GameBean {

// Initialization & Declarations
    private String name;
    private double price;
    private int quantity;
    private String publisher;

    private Game.Publisher defaultPublisher = Game.Publisher.BUNGIE;

    private String existingName;
    private int changeQuantity;

// Event Initialization
    // Events of type Game
    @Inject // Injects through the CDI to ensure the service is properly instantiated
    @AddEvent
    private Event<Game> gameAddEvent;

    @Inject
    @RemoveEvent
    private Event<Game> gameRemoveEvent;

    @Inject
    @ChangeQuantityEvent
    private Event<Game> gameChangeQuantityEvent;

    @Inject
    private GameManager gameService;

    public List<Game> getGames() {
        return gameService.getGameList();
    }

// Add Game
    @Logged // @Logged interceptor to log information when add method is called
    public void addGame(){
        quantity = 1;

        if(publisher.equals("IDSOFTWARE")) {
            defaultPublisher = Game.Publisher.IDSOFTWARE;
        }
        if(publisher.equals("NAMCO")) {
            defaultPublisher = Game.Publisher.NAMCO;
        }

        Game game = new Game(name, price, quantity, defaultPublisher);
        gameAddEvent.fire(game);
    } // END OF addGame

// Remove Game
    @Logged // @Logged interceptor to log information when remove method is called
    public void removeGame(Game game) {
        gameRemoveEvent.fire(game);
    } // END OF removeGame

// Update Quantity
    @Logged // @Logged interceptor to log information when changeQuantity method is called
    public void updateQuantity() {
        Game game = new Game(existingName, price, changeQuantity, defaultPublisher);
        gameChangeQuantityEvent.fire(game);
    } // END OF updateQuantity

// GETTERS AND SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getExistingName() {
        return existingName;
    }

    public void setExistingName(String existingName) {
        this.existingName = existingName;
    }

    public int getChangeQuantity() {
        return changeQuantity;
    }

    public void setChangeQuantity(int changeQuantity) {
        this.changeQuantity = changeQuantity;
    }
} // END OF GameBean
