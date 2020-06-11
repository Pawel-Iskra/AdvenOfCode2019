import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day2_part2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(",");
        int size = input.length;
        boolean flag = false;
        for (int noun = 0; noun < 100; noun++) {
            if (flag) break;
            for (int verb = 0; verb < 100; verb++) {
                int[] in = new int[size];
                for (int i = 0; i < size; i++)
                    in[i] = (Integer.parseInt(input[i]));
                in[1] = noun;
                in[2] = verb;
                int i = 0;
                while (in[i] != 99) {
                    switch (in[i]) {
                        case 1:
                            in[in[i + 3]] = in[in[i + 1]] + in[in[i + 2]];
                            break;
                        case 2:
                            in[in[i + 3]] = in[in[i + 1]] * in[in[i + 2]];
                            break;
                    }
                    i = i + 4;
                }
                if (in[0] == 19690720) {
                    System.out.println("Answer = " + (100 * noun + verb));
                    flag = true;
                    break;
                }
            }
        }

    }
}
