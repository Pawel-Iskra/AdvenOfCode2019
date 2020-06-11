import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Day7_part1 {
    private static int intComputer(int[] in, int phase, int input) {
        int out = 0;
        int inputIter = 0;
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
                    if (inputIter == 0) in[in[i]] = phase;
                    if (inputIter == 1) in[in[i]] = input;
                    inputIter++;
                    i++;
                    break;
                case 4:
                    i++;
                    int param = -100;
                    if (mode[2] == 0) param = in[i];
                    if (mode[2] == 1) param = i;
                    out = in[param];
                    i++;
                    break;
                case 5:
                case 6:
                    i++;
                    int firstParam = 0, secParam = 0;
                    if (mode[2] == 0) firstParam = in[in[i]];
                    if (mode[2] == 1) firstParam = in[i];
                    i++;
                    if (mode[1] == 0) secParam = in[in[i]];
                    if (mode[1] == 1) secParam = in[i];
                    if (opCode == 5) {
                        if (firstParam != 0) i = secParam;
                        else i++;
                    }
                    if (opCode == 6) {
                        if (firstParam == 0) i = secParam;
                        else i++;
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
            }
        }
        return out;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(",");
        int x = input.length;
        int[] in = new int[x];
        for (int i = 0; i < x; i++)
            in[i] = Integer.parseInt(input[i]);
        Random rand = new Random();
        List<Integer> outList = new ArrayList<>();
        int out;
        for (int i = 0; i < 10000; i++) {
            int ph1 = rand.nextInt(5);
            out = intComputer(in, ph1, 0);
            int ph2 = rand.nextInt(5);
            while (ph2 == ph1) ph2 = rand.nextInt(5);
            out = intComputer(in, ph2, out);
            int ph3 = rand.nextInt(5);
            while (ph3 == ph2 || ph3 == ph1) ph3 = rand.nextInt(5);
            out = intComputer(in, ph3, out);
            int ph4 = rand.nextInt(5);
            while (ph4 == ph3 || ph4 == ph2 || ph4 == ph1) ph4 = rand.nextInt(5);
            out = intComputer(in, ph4, out);
            int ph5 = rand.nextInt(5);
            while (ph5 == ph4 || ph5 == ph3 || ph5 == ph2 || ph5 == ph1) ph5 = rand.nextInt(5);
            out = intComputer(in, ph5, out);
            outList.add(out);
        }
        Collections.sort(outList);
        System.out.println("Answer = " + outList.get(outList.size() - 1));
    }
}
