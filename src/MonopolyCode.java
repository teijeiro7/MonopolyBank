package src;

public abstract class MonopolyCode {
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
        String stringId = "Id: " + id;
        String stringDescription = "Description: " + description;
        return stringId + "\n" + stringDescription;
    }

    public int getId(int id) {
        return id;
    }

    public abstract void doOperation(Player player);

}