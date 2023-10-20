package uqu;

public class MoviePackage  {
    private String name;
    private MovieType type;
    private double price;

    public MoviePackage(String name, MovieType type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public MovieType getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }
}


