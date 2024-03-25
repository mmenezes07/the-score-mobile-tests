package common;

/**
 * A helper class to create Stat objects for the team stats page of the app
 */
public class Stat {
    public String category;
    public String value;
    public String rank;

    public Stat(String category, String value, String rank) {
        this.category = category;
        this.value = value;
        this.rank = rank;
    }
}
