import java.io.*;

public class Day1_part1 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int sum = 0;

        String line;
        while ((line = br.readLine()) != null)
            sum = sum + Integer.parseInt(line) / 3 - 2;


        System.out.println("sum = " + sum);

    }
}
