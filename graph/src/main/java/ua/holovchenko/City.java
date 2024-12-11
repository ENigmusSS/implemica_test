package ua.holovchenko;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class City implements Comparable<City> {
    private final String name;
    private boolean visited;
    private final List<Road> neighbours;
    private final HashMap<Integer, Integer> roads = new HashMap<>();
    int distance;


    public City(String name) {
        this.name = name;
        neighbours = new ArrayList<>();
        distance = Integer.MAX_VALUE;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void addNeighbor(Road road) {
        neighbours.add(road);
    }

    public String getName() {
        return name;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public List<Road> getNeighbours() {
        return neighbours;
    }

    public void addRoad(int cityIndex, int cost) {
        roads.put(cityIndex, cost);
    }

    public HashMap<Integer, Integer> getRoads() {
        return roads;
    }

    @Override
    public int compareTo(City o1) {
        return Integer.compare(this.getDistance(), o1.getDistance());
    }
}