import java.io.*;

public class Day1_part2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int sum = 0;
        String line;
        while ((line = br.readLine()) != null) {
            int moduleFuel = Integer.parseInt(line) / 3 - 2;
            int fule4fuel = moduleFuel / 3 - 2;
            sum = sum + moduleFuel + fule4fuel;
            while ((fule4fuel = fule4fuel / 3 - 2) > 0)
                sum = sum + fule4fuel;
        }
        System.out.println("sum = " + sum);
    }
}
