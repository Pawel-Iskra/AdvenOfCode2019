import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day6_part2 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<String> left = new ArrayList<>();
        List<String> right = new ArrayList<>();

        String line;
        while ((line = br.readLine()) != null) {

            String[] input = line.split("[)]");
            left.add(input[0]);
            right.add(input[1]);
        }


        int indYou = right.indexOf("YOU");
        int indSan = right.indexOf("SAN");

        ///// PATH: YOU -> COM /////

        List<String> pathYou = new ArrayList<>();
        String planet = left.get(indYou);
        pathYou.add(planet);

        int size = right.size();
        while (!planet.equals("COM")) {

            int tempIndex = -1;
            for (int j = 0; j < size; j++) {
                if (right.get(j).equals(planet)) tempIndex = j;
            }

            planet = left.get(tempIndex);
            pathYou.add(planet);
        }


        ///// PATH: SAN -> COM /////
        List<String> pathSan = new ArrayList<>();
        planet = left.get(indSan);
        pathSan.add(planet);

        size = right.size();
        while (!planet.equals("COM")) {

            int tempIndex = -1;
            for (int j = 0; j < size; j++) {
                if (right.get(j).equals(planet)) tempIndex = j;
            }

            planet = left.get(tempIndex);
            pathSan.add(planet);
        }

        ///// FINDING FIRST COMMON PLANET /////
        int sizePathYou = pathYou.size();
        int sizePathSan = pathSan.size();

        int answer = -1;
        boolean flag = false;
        for (int i = 0; i < sizePathYou; i++) {

            if (flag) break;
            String elem = pathYou.get(i);

            for (int j = 0; j < sizePathSan; j++) {
                if (pathSan.get(j).equals(elem)) {
                    flag = true;
                    answer = j + i;
                    break;
                }
            }
        }

        
        System.out.println("Answer = " + answer);
    }
}
