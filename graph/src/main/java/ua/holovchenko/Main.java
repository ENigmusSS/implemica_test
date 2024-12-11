package ua.holovchenko;


import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Main class contains the entry point for the application, which reads input,
 * processes city and road data, and computes shortest paths using Dijkstra's algorithm.
 */
public class Main {
    public static void main(String[] args) {
        // Initialize Dijkstra's algorithm
        Dijkstra dijkstra = new Dijkstra();

        // Use try-with-resources to ensure Scanner is closed automatically
        try (Scanner scanner = new Scanner(System.in)) {
            // List to store all cities
            ArrayList<City> cities = new ArrayList<>();
            // Read the number of scenarios from input
            int numberOfScenarios = scanner.nextInt();

            // Process each scenario
            for (int s = 0; s < numberOfScenarios; s++) {
                int numberOfCities = scanner.nextInt();
                scanner.nextLine(); // Clear the buffer

                // Process city data
                for (int n = 0; n < numberOfCities; n++) {
                    String cityName = scanner.nextLine(); // Read city name
                    City city = new City(cityName); // Create a new City object
                    cities.add(city); // Add city to the list

                    int numberOfRoads = scanner.nextInt(); // Read the number of roads from this city
                    scanner.nextLine(); // Clear the buffer

                    // Process each road for the current city
                    for (int p = 0; p < numberOfRoads; p++) {
                        int neighborIndex = scanner.nextInt(); // Read index of the neighboring city
                        int cost = scanner.nextInt(); // Read the cost of the road
                        scanner.nextLine(); // Clear the buffer
                        city.addRoad(neighborIndex, cost); // Add the road to the city
                    }
                }

                // Set up neighbors for each city based on road data
                for (City city : cities) {
                    city.getRoads().forEach((index, cost) ->
                            city.addNeighbor(new Road(cost, city, cities.get(index - 1)))
                    );
                }

                // Process routes between cities
                int numberOfRoutes = scanner.nextInt(); // Read the number of routes to compute
                scanner.nextLine(); // Clear the buffer

                for (int r = 0; r < numberOfRoutes; r++) {
                    String startCityName = scanner.next(); // Read the name of the starting city
                    String endCityName = scanner.next(); // Read the name of the destination city
                    scanner.nextLine(); // Clear the buffer

                    // Find the starting city object by name
                    City startCity = cities.stream()
                            .filter(city -> city.getName().equals(startCityName))
                            .findFirst()
                            .orElse(null);

                    // Find the destination city object by name
                    City endCity = cities.stream()
                            .filter(city -> city.getName().equals(endCityName))
                            .findFirst()
                            .orElse(null);

                    // Handle case where one of the cities is not found
                    if (startCity == null || endCity == null) {
                        System.err.println("city is not present");
                        continue;
                    }

                    // Compute the shortest path from the starting city
                    dijkstra.compute(startCity);
                    // Print the distance to the destination city
                    System.out.println(endCity.getDistance());
                    // Reset city distances and visited statuses for the next computation
                    cities.forEach(dijkstra::cleanUp);
                }
            }
        } catch (Exception e) {
            // Handle any unexpected errors during execution
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}

