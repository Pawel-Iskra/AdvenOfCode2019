import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day8_part1 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] in = br.readLine().toCharArray();
        int amount = in.length;

        int width = 25;
        int hight = 6;
        int size = width * hight;
        int layers = amount / size;

        int minZero = amount;
        int ones = 0;
        int twos = 0;

        for (int i = 0; i < layers; i++) {

            int countZero = 0;
            int countOne = 0;
            int countTwo = 0;

            for (int j = 0; j < size; j++) {
                if (in[i * size + j] == '0') countZero++;
                if (in[i * size + j] == '1') countOne++;
                if (in[i * size + j] == '2') countTwo++;
            }

            if (minZero > countZero) {
                minZero = countZero;
                ones = countOne;
                twos = countTwo;
            }
        }

        System.out.println("Answer = " + (twos * ones));
    }
}
