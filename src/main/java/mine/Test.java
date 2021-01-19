package mine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws IOException {
        File file = new File("dataset/ch130.tsp");
        ArrayList<City> cities;
        cities = new Map().readTest(file);
        Sa sa = new Sa();
        sa.sa(cities);
    }
}
