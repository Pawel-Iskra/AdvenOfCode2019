import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day6_part1 {
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


        int sumAll = 0;
        int orbPlanet = 0;
        int size = right.size();

        for (int i = 0; i < size; i++) {

            orbPlanet = 0;
            String planet = left.get(i);
            orbPlanet++;

            while (!planet.equals("COM")) {

                int tempIndex = -1;
                for (int j = 0; j < size; j++)
                    if (right.get(j).equals(planet)) tempIndex = j;

                orbPlanet++;
                planet = left.get(tempIndex);
            }

            sumAll = sumAll + orbPlanet;
        }

        System.out.println("Answer = " + sumAll);
    }
}
