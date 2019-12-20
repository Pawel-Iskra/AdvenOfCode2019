import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Day2_part2 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(",");
        int size = input.length;


        while (true) {

            List<Integer> in = new ArrayList<>();
            for (int i = 0; i < size; i++)
                in.add(Integer.parseInt(input[i]));


            Random rand = new Random();
            int noun = rand.nextInt(100);
            int verb = rand.nextInt(100);

            in.set(1, noun);
            in.set(2, verb);

            int i = 0;
            while (in.get(i) != 99) {

                switch (in.get(i)) {

                    case 1:
                        in.set(in.get(i + 3), in.get(in.get(i + 1)) + in.get(in.get(i + 2)));
                        break;
                    case 2:
                        in.set(in.get(i + 3), in.get(in.get(i + 1)) * in.get(in.get(i + 2)));
                        break;
                }
                i = i + 4;
            }

            if (in.get(0) == 19690720) {
                System.out.println("Answer = " + (100 * noun + verb));
                break;
            }

        }


    }
}
