package mine;

import java.io.*;
import java.util.ArrayList;

public class Map {

    public Map() {
    }

    public ArrayList<City> readTest(File file) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String str;
        do {
            str = bf.readLine();
        } while (!str.equals("NODE_COORD_SECTION"));
        ArrayList<City> list = new ArrayList<>();
        while (true) {
            str = bf.readLine();
            if (str.equals("EOF"))
                break;
            String[] strArr = str.trim().split("\\s+");
            list.add(new City(Integer.parseInt(strArr[0]), Double.parseDouble(strArr[1]), Double.parseDouble(strArr[2])));
        }

//        for (City c : list) {
//            System.out.print(c.getLabel() + " ");
//        }
        return list;
    }

}
