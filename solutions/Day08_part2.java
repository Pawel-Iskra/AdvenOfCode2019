import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day8_part2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] in = br.readLine().toCharArray();
        int amount = in.length;
        int width = 25;
        int hight = 6;
        int size = width * hight;
        int layers = amount / size;
        
        for (int i = 0; i < hight; i++) {
            for (int j = 0; j < width; j++) {
                for (int k = 0; k < layers; k++) {
                    
                    int index = k * size + i * width + j;
                    if (in[index] != '2') {
                        if (in[index] == '0') System.out.print(" ");
                        if (in[index] == '1') System.out.print("#");
                        break;
                    }
                }
            }
            System.out.println();
        }
    }
}
