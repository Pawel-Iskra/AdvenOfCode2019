package Day07_part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Day7_part2 {

    private static Map<Long, Long> softCopy(String[] input) {
        Map<Long, Long> soft = new HashMap<>();
        int size = input.length;
        long k = 0;
        for (int i = 0; i < size; i++, k++)
            soft.put(k, Long.parseLong(input[i]));
        return soft;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(",");

        int[] phases = {5,6,7,8,9};
        Permutations permutations = new Permutations();
        permutations.permute(phases, 0);
        List<int[]> perms = permutations.getOutList();
        int size = perms.size();


        List<Long> outList = new ArrayList<>();
        for (int i = 0; i < size; i++) {

            int ph1 = perms.get(i)[0];
            int ph2 = perms.get(i)[1];
            int ph3 = perms.get(i)[2];
            int ph4 = perms.get(i)[3];
            int ph5 = perms.get(i)[4];

            IntComputer_for_Day7_2 cpu1 = new IntComputer_for_Day7_2(ph1, softCopy(input));
            IntComputer_for_Day7_2 cpu2 = new IntComputer_for_Day7_2(ph2, softCopy(input));
            IntComputer_for_Day7_2 cpu3 = new IntComputer_for_Day7_2(ph3, softCopy(input));
            IntComputer_for_Day7_2 cpu4 = new IntComputer_for_Day7_2(ph4, softCopy(input));
            IntComputer_for_Day7_2 cpu5 = new IntComputer_for_Day7_2(ph5, softCopy(input));

            /// INITIALIZATION ///
            long out = -100;
            cpu1.intComputer(0, 0);
            if (cpu1.isHasOut()) out = cpu1.getOut();

            cpu2.intComputer(out, 0);
            if (cpu2.isHasOut()) out = cpu2.getOut();

            cpu3.intComputer(out, 0);
            if (cpu3.isHasOut()) out = cpu3.getOut();

            cpu4.intComputer(out, 0);
            if (cpu4.isHasOut()) out = cpu4.getOut();

            cpu5.intComputer(out, 0);
            if (cpu5.isHasOut()) out = cpu5.getOut();

            /// FEEDBACK LOOP ///
            while (!cpu5.isEnd()) {
                cpu1.intComputer(out, cpu1.getI());
                if (cpu1.isHasOut()) out = cpu1.getOut();

                cpu2.intComputer(out, cpu2.getI());
                if (cpu2.isHasOut()) out = cpu2.getOut();

                cpu3.intComputer(out, cpu3.getI());
                if (cpu3.isHasOut()) out = cpu3.getOut();

                cpu4.intComputer(out, cpu4.getI());
                if (cpu4.isHasOut()) out = cpu4.getOut();

                cpu5.intComputer(out, cpu5.getI());
                if (cpu5.isHasOut()) out = cpu5.getOut();
            }
            outList.add(out);
        }

        Collections.sort(outList);
        System.out.println("Answer = " + outList.get(outList.size() - 1));
    }
}
