package tsp;

import java.util.ArrayList;
import java.util.Random;

public class SimulatedAnnealing {
    public static double TEMPERATURE = 100;
    public static double coolingRate = 0.9;
    double absoluteTemperature = 0.00001; //Moc nhiet do ket thuc thuat toan
    public double totalCost;

    public ArrayList<City> tempRoute;

    public ArrayList<City> currentRoute;
    public ArrayList<City> newRoute;

    public ArrayList<City> bestRoute;
    public double bestCost;

    public void simulatedAnnealing() {
        double temp = TEMPERATURE;
        startSequence();
        totalCost = calculateTotalCost(currentRoute);

        bestRoute = new ArrayList<>(currentRoute);
        bestCost = totalCost;

        int counter = 1;
        while (Math.round(TEMPERATURE * 100) != 0) {

            newRoute = new ArrayList<>(swapRoutes());

            double newCost = calculateTotalCost(newRoute);
            // depends on probability chosing new Route
            makeDecision(newCost);

            TEMPERATURE = TEMPERATURE * coolingRate;
            if (TEMPERATURE < absoluteTemperature) {
                break;
            }

            if (bestCost > totalCost) {
                bestCost = totalCost;
                bestRoute = new ArrayList<>(currentRoute);
            }

            for (int i = 0; i < currentRoute.size(); i++) {
                System.out.print((currentRoute.get(i).getName() + 1) + " ");
            }
            System.out.println("- TotalCost: " + totalCost);
            System.out.println();
            counter++;
        }

        System.out.println("__________________________");
        System.out.println("Initial Tempurature: " + temp);
        System.out.println("CoolingRate: " + coolingRate);
        System.out.println("Number of interations: " + counter);
        System.out.println("Best cost: " + bestCost);
    }

    // --------------------------------------------------------------------------------

    public ArrayList<City> swapRoutes() {
        tempRoute = new ArrayList<>(currentRoute);

        // Random 2 cross points p1 and p2
        int p1 = new Random().nextInt(this.currentRoute.size());
        int p2 = -1;
        do {
            p2 = new Random().nextInt(this.currentRoute.size());
        } while (p1 == p2);

        if (p1 > p2) {
            int tmp = p1;
            p1 = p2;
            p2 = tmp;
        }

        for (int i = p1; i < (p2 + p1 + 1) / 2; i++) {
            int j = p2 - (i - p1);
            City a = currentRoute.get(i);
            City b = currentRoute.get(j);
            tempRoute.set(i, b);
            tempRoute.set(j, a);
        }

        return tempRoute;
    }

    public double calculateTotalCost(ArrayList<City> route) {
        double totalCost = 0;
        // route = [0, 4, 2, ̀5, 1, 3, 0]
        for (int i = 0, j = i + 1; j < route.size(); i++, j++) {
            totalCost += route.get(i).getDistanceToCity(route.get(j));
        }
//		System.out.println(totalCost);
        return totalCost;
    }

    public boolean calculateProbability(double minusCost) {
        int pickRandom = new Random().nextInt(100);
        double prob = 100 / Math.pow(Math.E, (minusCost / TEMPERATURE));
        if (pickRandom > prob) {
            return false;
        } else {
            return true;
        }
    }

    public void makeDecision(double newCost) {
        if (newCost < totalCost) {
            totalCost = newCost; // set intermediately
            currentRoute = new ArrayList<>(newRoute);
        } else {
            double minusCost = newCost - totalCost;
            if (calculateProbability(minusCost)) {
                totalCost = newCost;
                currentRoute = new ArrayList<>(newRoute);
//			} else {
//				totalCost = totalCost;
            }
        }
    }

    public void startSequence() {
        ArrayList<City> tempSequence = new ArrayList<>();
        Random rd = new Random();
        int pointDB = rd.nextInt(Map.getListOfCities().size()); // start city
        // Set the starting point of the route
        tempSequence.add(Map.getListOfCities().get(pointDB));
        System.out.println(Map.getListOfCities().get(pointDB).getName());

        while (tempSequence.size() != Map.getListOfCities().size() + 1) {
            pointDB = new Random().nextInt(Map.getListOfCities().size());
            if (!tempSequence.contains(Map.getListOfCities().get(pointDB))) {
                tempSequence.add(Map.getListOfCities().get(pointDB));
            }
            if (tempSequence.size() == (Map.getListOfCities().size())) {
                pointDB = tempSequence.get(0).getName();
                tempSequence.add(Map.getListOfCities().get(pointDB));
            }
        }
        currentRoute = new ArrayList<>(tempSequence);
    }

}
