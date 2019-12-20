import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Day4_part1 {
    public static void main(String[] args) {

        int st = 235741;
        int end = 706948;
        int rand;

        Random random = new Random();
        Set<Integer> setRand = new HashSet<>();


        for (int i = 0; i < 10000000; i++) {

            boolean flag1 = false, flag2 = false;

            rand = random.nextInt((end - st) + 1) + st;
            char[] lc = String.valueOf(rand).toCharArray();

            if (lc[0] == lc[1] || lc[1] == lc[2] || lc[2] == lc[3] || lc[3] == lc[4] || lc[4] == lc[5]) flag1 = true;
            if (lc[0] <= lc[1] && lc[1] <= lc[2] && lc[2] <= lc[3] && lc[3] <= lc[4] && lc[4] <= lc[5]) flag2 = true;

            if (flag1 && flag2) setRand.add(rand);
        }

        System.out.println("Answer = " + setRand.size());

    }
}
