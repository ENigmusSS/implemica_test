package ua.holovchenko;

import java.util.PriorityQueue;

/**
 * The Dijkstra class implements Dijkstra's algorithm for finding the shortest paths
 * from a source city to all other cities in a graph. It uses a priority queue
 * to efficiently select the next city with the smallest distance.
 */
public class Dijkstra {
    // Priority queue to process cities based on their distances
    PriorityQueue<City> queue;

    /**
     * Constructor initializes the priority queue.
     */
    public Dijkstra() {
        queue = new PriorityQueue<>();
    }

    /**
     * Computes the shortest paths from the source city to all other cities
     * using Dijkstra's algorithm.
     *
     * @param source The starting city.
     */
    public void compute(City source) {
        // Set the distance of the source city to 0
        source.setDistance(0);
        // Add the source city to the queue
        queue.add(source);

        // Process the queue until all reachable cities are visited
        while (!queue.isEmpty()) {
            // Retrieve and remove the city with the smallest distance from the queue
            City curr = queue.poll();

            // Iterate through the current city's neighbors
            for (Road e : curr.getNeighbours()) {
                City end = e.getEnd(); // Destination city of the road
                int w = e.getWeight(); // Weight of the road

                // Only process unvisited cities
                if (!end.isVisited()) {
                    // Check if a shorter path to the destination city exists
                    if (curr.getDistance() + w < end.getDistance()) {
                        // Update the distance to the destination city
                        end.setDistance(curr.getDistance() + w);
                        // Remove and re-add the destination city to update its position in the queue
                        queue.remove(end);
                        queue.add(end);
                    }
                }
            }
            // Mark the current city as visited
            curr.setVisited(true);
        }
    }

    /**
     * Resets the distance and visited status of a city.
     * This is useful for preparing the city for a new computation.
     *
     * @param city The city to reset.
     */
    public void cleanUp(City city) {
        city.setDistance(Integer.MAX_VALUE); // Reset distance to "infinity"
        city.setVisited(false);              // Mark the city as unvisited
    }
}
