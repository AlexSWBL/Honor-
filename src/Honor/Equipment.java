package honor;
public class Equipment {
    private String name;
    private String description;
    private int rating;

    public Equipment(String name, String description, int rating) {
        this.name = name;
        this.description = description;
        this.rating = rating;
    }

    public String getName() { return name; }
    public int getRating() { return rating; }
}