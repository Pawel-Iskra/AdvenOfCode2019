import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Day2_part1 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(",");

        List<Integer> in = new ArrayList<>();

        int size = input.length;
        for (int i = 0; i < size; i++)
            in.add(Integer.parseInt(input[i]));


        in.set(1, 12);
        in.set(2, 2);

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

        System.out.println("Answer = " + in.get(0));

    }
}
