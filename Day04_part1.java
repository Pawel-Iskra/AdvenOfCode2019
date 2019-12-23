import java.util.HashSet;
import java.util.Set;

public class Day4_part1 {
    public static void main(String[] args) {

        int start = 235741;
        int end = 706948;

        Set<Integer> setRand = new HashSet<>();

        for (int i = start; i <= end; i++) {

            boolean flag1 = false, flag2 = false;

            char[] lc = String.valueOf(i).toCharArray();

            if (lc[0] == lc[1] || lc[1] == lc[2] || lc[2] == lc[3] || lc[3] == lc[4] || lc[4] == lc[5]) flag1 = true;
            if (lc[0] <= lc[1] && lc[1] <= lc[2] && lc[2] <= lc[3] && lc[3] <= lc[4] && lc[4] <= lc[5]) flag2 = true;

            if (flag1 && flag2) setRand.add(i);
        }

        System.out.println("Answer = " + setRand.size());

    }
}
