import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day5_part2 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(",");
        int x = input.length;
        int[] in = new int[x];

        for (int i = 0; i < x; i++)
            in[i] = Integer.parseInt(input[i]);


        int inputValue = 5;
        int answer = -100;


        int i = 0;
        while (in[i] != 99) {


            int temp = in[i];
            int[] mode = new int[3];

            mode[0] = temp / 10000;
            mode[1] = (temp / 1000) % 10;
            mode[2] = (temp / 100) % 10;

            int opCode = temp % 10;

            switch (opCode) {

                case 1:
                case 2:
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

                case 3:

                    i++;
                    in[in[i]] = inputValue;
                    i++;
                    break;

                case 4:

                    i++;
                    int param = -100;
                    if (mode[2] == 0) param = in[i];
                    if (mode[2] == 1) param = i;
                    answer = in[param];
                    i++;
                    break;

                case 5:
                case 6:

                    i++;
                    int firstParam = 0, secParam = 0;
                    if (mode[2] == 0) firstParam = in[in[i]];
                    if (mode[2] == 1) firstParam = in[i];

                    if (opCode == 5) {
                        if (firstParam != 0) {
                            i++;
                            if (mode[1] == 0) secParam = in[in[i]];
                            if (mode[1] == 1) secParam = in[i];
                            i = secParam;
                        } else i = i + 2;
                    }

                    if (opCode == 6) {
                        if (firstParam == 0) {
                            i++;
                            if (mode[1] == 0) secParam = in[in[i]];
                            if (mode[1] == 1) secParam = in[i];
                            i = secParam;
                        } else i = i + 2;
                    }
                    break;

                case 7:
                case 8:

                    int param1 = 0, param2 = 0;

                    i++;
                    if (mode[2] == 0) param1 = in[in[i]];
                    if (mode[2] == 1) param1 = in[i];

                    i++;
                    if (mode[1] == 0) param2 = in[in[i]];
                    if (mode[1] == 1) param2 = in[i];

                    i++;
                    if (opCode == 7) {
                        if (param1 < param2) in[in[i]] = 1;
                        else in[in[i]] = 0;
                    }
                    if (opCode == 8) {
                        if (param1 == param2) in[in[i]] = 1;
                        else in[in[i]] = 0;
                    }

                    i++;
                    break;

            } // end of switch
        } // end of while

        System.out.println("Answer = " + answer);

    }
}

