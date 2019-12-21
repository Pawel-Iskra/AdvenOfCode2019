import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Day5_part1 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(",");
        int x = input.length;
        int[] in = new int[x];

        for (int i = 0; i < x; i++)
            in[i] = Integer.parseInt(input[i]);


        int inputValue = 1;
        int answer = -100;


        int i = 0;
        while (in[i] != 99) {

            int opCode = in[i] % 10;

            switch (opCode) {

                case 4:

                    i++;
                    answer = in[in[i]];
                    i++;
                    break;

                case 3:

                    i++;
                    int y = inputValue;
                    in[in[i]] = y;
                    i++;
                    break;

                case 2:
                case 1:

                    int temp = in[i];
                    int[] mode = new int[3];
                    mode[1] = (temp / 1000) % 10;
                    mode[2] = (temp / 100) % 10;

                    int numb1 = 0, numb2 = 0;

                    i++;
                    if (mode[2] == 0) numb1 = in[in[i]];
                    if (mode[2] == 1) numb1 = in[i];

                    i++;
                    if (mode[1] == 0) numb2 = in[in[i]];
                    if (mode[1] == 1) numb2 = in[i];

                    i++;
                    if (opCode == 1) in[in[i]] = numb1 + numb2;
                    if (opCode == 2) in[in[i]] = numb1 * numb2;

                    i++;
                    break;
            } // end of switch
        } // end of while

        System.out.println("Answer = " + answer);

    }
}

