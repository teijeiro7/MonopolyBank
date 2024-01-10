package src;

import java.io.Serializable;

public class MonopolyCode implements Serializable {
    private String description;
    private int id;
    private Terminal terminal;
    private Player player;

    public MonopolyCode() {
    }

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

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    };

}