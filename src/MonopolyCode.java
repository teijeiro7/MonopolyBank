package src;

import utils.*;

public class MonopolyCode {
    private String description;
    private int id;
    private Terminal terminal;
    private Player player;

    public MonopolyCode(String description, int id, Terminal terminal) {
        this.description = description;
        this.id = id;
        this.terminal = terminal;
    }

    public String toString() {
        return id + ". " + description;
    }

    public int getId(int id) {
        return id;
    }

    public void doOperation(Player player) {

    };

}