package ua.holovchenko;

public class Road {
    private final City start;
    private final City end;
    private final int weight;

    public Road(int weight, City start, City end) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public City getStart() {
        return start;
    }

    public City getEnd() {
        return end;
    }

    public int getWeight() {
        return weight;
    }

}