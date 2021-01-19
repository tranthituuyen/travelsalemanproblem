package tsp;

import java.util.ArrayList;

public class City {
    // stt kc
    private int label;
    public ArrayList<Double> distances;

    public City(int name, ArrayList<Double> distances) {
        this.label = name;
        this.distances = distances;
    }

    public int getName() {
        return this.label;
    }

    public double getDistanceToCity(City city) {
        return distances.get(city.label);
    }

    public ArrayList<Double> getAllDistances() {
        return distances;
    }

    @Override
    public String toString() {
        return "" + distances;
    }

}
