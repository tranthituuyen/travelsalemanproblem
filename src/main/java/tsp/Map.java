package tsp;

import java.io.*;
import java.util.ArrayList;

public class Map {
    private static double[][] map;
    private static ArrayList<City> cities = new ArrayList<>();

    public Map(int size) {
        map = new double[size][size];
    }

    public Map() {
    }

    public void setPath(int a, int b, double path) {
        map[a][b] = path;
        map[b][a] = path;
    }

    public double getPath(int a, int b) {
        return map[a][b];
    }

    public int size() {
        return map.length;
    }

    public void readTest(File file) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String str;
        do {
            str = bf.readLine();
        } while (!str.equals("NODE_COORD_SECTION"));
        ArrayList<Point> list = new ArrayList<Point>();
        while (true) {
            str = bf.readLine();
            if (str.equals("EOF"))
                break;
            String[] strArr = str.trim().split("\\s+");
            list.add(new Point(Double.parseDouble(strArr[1]), Double.parseDouble(strArr[2])));
        }
        ArrayList<Double> distances = new ArrayList<Double>();
        map = new double[list.size()][list.size()];
        int label = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                map[i][j] = list.get(i).distance(list.get(j));
                map[j][i] = map[i][j];
                distances.add(map[i][j]);
            }
            cities.add(new City(label, distances));
            label++;
        }
    }

    public static ArrayList<City> getListOfCities() {
        return cities;
    }

}
