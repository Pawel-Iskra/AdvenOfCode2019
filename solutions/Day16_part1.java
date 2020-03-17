import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day16_part1 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] input = br.readLine().toCharArray();

        int inputSize = input.length;
        int[] in = new int[inputSize];
        for (int i = 0; i < inputSize; i++) in[i] = input[i] - 48;

        int numbOfPhases =  100;
        List<Integer> base = Arrays.asList(0, 1, 0, -1);


        List<Integer> factors;
        int[] out = new int[inputSize];

        for (int f = 0; f < numbOfPhases; f++) {

            factors = new ArrayList<>();
            factors.addAll(base);

            for (int i = 0; i < inputSize; i++) {

                int pointer = 1;
                int fctSize = factors.size();

                for (int j = 0; j < inputSize; j++) {
                    if (pointer == fctSize) pointer = 0;
                    out[i] = out[i] + in[j] * factors.get(pointer);
                    pointer++;
                } // single element

                out[i] = Math.abs(out[i]) % 10;

                /// updating factor list ///
                factors = new ArrayList<>();
                for (int k = 0; k < 4; k++) {
                    for (int m = 0; m < i + 2; m++) {
                        factors.add(base.get(k));
                    }
                }

            }

            /// updating: in on out, out on 0 ///
            for (int i = 0; i < inputSize; i++) in[i] = out[i];
            out = new int[inputSize];

        } // phases


        StringBuilder strb = new StringBuilder();

        for(int i = 0; i < 8; i++) strb.append(in[i]);

        System.out.println("Answer = " + strb.toString());

    }
}
