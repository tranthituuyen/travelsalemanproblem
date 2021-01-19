package mine;

import java.util.ArrayList;
import java.util.Random;

public class Sa {
    public static double TEMPERATURE = 10000000;
    public static double coolingRate = 0.99999;
    double absoluteTemperature = 0.00001; //Moc nhiet do ket thuc thuat toan

    public ArrayList<City> tempRoute;
    public ArrayList<City> currentRoute;
    public ArrayList<City> newRoute;
    public ArrayList<City> bestRoute;

    public double totalCost;
    public double bestCost;
    
    public void sa(ArrayList<City> cities) {
        double tempt = TEMPERATURE;

        startSequence(cities);
//        startSequence(new File(("dataset.properties/a280.tsp")));
        bestRoute = new ArrayList<>(currentRoute);

        totalCost = calculateTotalCost(currentRoute);
        bestCost = totalCost;

        int counter = 0;
        while (Math.round(TEMPERATURE * 100) != 0) {
            newRoute = new ArrayList<>(swapRoutes());
            double newCost = calculateTotalCost(newRoute);
            makeDecision(newCost);

            TEMPERATURE = TEMPERATURE * coolingRate;
            if (TEMPERATURE < absoluteTemperature) {
                break;
            } else {
                if (bestCost > totalCost) {
                    bestCost = totalCost;
                    bestRoute = new ArrayList<>(currentRoute);
                }

//                for (City city : currentRoute) {
//                    System.out.print((city.getLabel() + 1) + " ");
//                }


                System.out.println("- TotalCost: " + totalCost);
                System.out.println();
                counter++;
            }
        }

        System.out.println("__________________________");
        System.out.println("Initial Tempurature: " + tempt);
        System.out.println("CoolingRate: " + coolingRate);
        System.out.println("Number of interations: " + counter);
        System.out.println("Best cost: " + bestCost);
    }

    private void makeDecision(double newCost) {
        if (newCost < totalCost) {
            totalCost = newCost; // set intermediately
            currentRoute = new ArrayList<>(newRoute);
        } else {
            double minusCost = newCost - totalCost;
            if (calculateProbability(minusCost)) {
                totalCost = newCost;
                currentRoute = new ArrayList<>(newRoute);
            }
        }
    }

    private boolean calculateProbability(double minusCost) {
        int pickRandom = new Random().nextInt(100);
        double prob = 100 / Math.pow(Math.E, (minusCost / TEMPERATURE));
        return !(pickRandom > prob);
    }

    public ArrayList<City> swapRoutes() {
        tempRoute = new ArrayList<>(currentRoute);

        // Random 2 cross points p1 and p2
        int p1 = new Random().nextInt(this.currentRoute.size());
        int p2;
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

    private double calculateTotalCost(ArrayList<City> currentRoute) {
        double totalCost = 0;

        for (int i = 0, j = i + 1; j < currentRoute.size(); i++, j++) {
            totalCost += currentRoute.get(i).distance(currentRoute.get(j));
        }

        return totalCost;
    }

    public void startSequence(ArrayList<City> cities) {
        ArrayList<City> tempSequence = new ArrayList<>();

        Random random = new Random();
        int pointDB = random.nextInt(cities.size());

        tempSequence.add(cities.get(pointDB));
        System.out.println("start city: " + cities.get(pointDB).getLabel());

        while (tempSequence.size() != cities.size() + 1) {
            pointDB = new Random().nextInt(cities.size());
            if (!tempSequence.contains(cities.get(pointDB))) {
                tempSequence.add(cities.get(pointDB));
            }
            if (tempSequence.size() == cities.size()) {
                pointDB = tempSequence.get(0).getLabel();
                tempSequence.add(cities.get(pointDB - 1));
            }
        }
//
//        for (City c : tempSequence) {
//            System.out.print(c.getLabel() + " ");
//        }

        currentRoute = new ArrayList<>(tempSequence);
    }
}
