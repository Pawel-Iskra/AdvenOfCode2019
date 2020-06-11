import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day13_part1 {

    private static List<Integer> intComputer(String[] input) {
        Map<Long, Long> map = new HashMap<>();
        int size = input.length;
        long k = 0;
        for (int i = 0; i < size; i++, k++)
            map.put(k, Long.parseLong(input[i]));
        List<Integer> outList = new ArrayList<>();
        long param1 = 0;
        long param2 = 0;
        long param3 = 0;
        long relBase = 0;
        long i = 0;
        while (true) {
            long instr = map.get(i);
            if (instr == 99) break;
            long opCode = instr % 10;
            long modeParam1 = (instr / 100) % 10;
            long modeParam2 = (instr / 1000) % 10;
            long modeParam3 = (instr / 10000) % 10;
            String oppCodee = String.valueOf(opCode);
            switch (oppCodee) {
                case "1":
                    i++;
                    if (modeParam1 == 0) {
                        if (!map.containsKey(map.get(i))) map.put(map.get(i), 0L);
                        param1 = map.get(map.get(i));
                    }
                    if (modeParam1 == 1) param1 = map.get(i);
                    if (modeParam1 == 2) {
                        if (!map.containsKey(map.get(i) + relBase)) map.put(map.get(i) + relBase, 0L);
                        param1 = map.get(map.get(i) + relBase);
                    }
                    i++;
                    if (modeParam2 == 0) {
                        if (!map.containsKey(map.get(i))) map.put(map.get(i), 0L);
                        param2 = map.get(map.get(i));
                    }
                    if (modeParam2 == 1) param2 = map.get(i);
                    if (modeParam2 == 2) {
                        if (!map.containsKey(map.get(i) + relBase)) map.put(map.get(i) + relBase, 0L);
                        param2 = map.get(map.get(i) + relBase);
                    }
                    i++;
                    if (modeParam3 == 0) param3 = map.get(i);
                    if (modeParam3 == 2) param3 = map.get(i) + relBase;
                    if (map.containsKey(param3))
                        map.replace(param3, (param1 + param2));
                    else map.put(param3, (param1 + param2));
                    i++;
                    break;
                case "2":
                    i++;
                    if (modeParam1 == 0) {
                        if (!map.containsKey(map.get(i))) map.put(map.get(i), 0L);
                        param1 = map.get(map.get(i));
                    }
                    if (modeParam1 == 1) param1 = map.get(i);
                    if (modeParam1 == 2) {
                        if (!map.containsKey(map.get(i) + relBase)) map.put(map.get(i) + relBase, 0L);
                        param1 = map.get(map.get(i) + relBase);
                    }
                    i++;
                    if (modeParam2 == 0) {
                        if (!map.containsKey(map.get(i))) map.put(map.get(i), 0L);
                        param2 = map.get(map.get(i));
                    }
                    if (modeParam2 == 1) param2 = map.get(i);
                    if (modeParam2 == 2) {
                        if (!map.containsKey(map.get(i) + relBase)) map.put(map.get(i) + relBase, 0L);
                        param2 = map.get(map.get(i) + relBase);
                    }
                    i++;
                    if (modeParam3 == 0) param3 = map.get(i);
                    if (modeParam3 == 2) param3 = map.get(i) + relBase;
                    if (map.containsKey(param3))
                        map.replace(param3, (param1 * param2));
                    else map.put(param3, (param1 * param2));
                    i++;
                    break;
                case "3":   // input
                    i++;
                    if (modeParam1 == 0) param1 = map.get(i);
                    if (modeParam1 == 2) param1 = map.get(i) + relBase;
                    i++;
                    break;
                case "4":   // output
                    i++;
                    if (modeParam1 == 0) param1 = map.get(i);
                    if (modeParam1 == 1) param1 = i;
                    if (modeParam1 == 2) param1 = map.get(i) + relBase;
                    if (!map.containsKey(param1)) map.put(param1, 0L);
                    outList.add(Integer.parseInt(String.valueOf(map.get(param1))));
                    i++;
                    break;
                case "5":
                    i++;
                    if (modeParam1 == 0) {
                        if (!map.containsKey(map.get(i))) map.put(map.get(i), 0L);
                        param1 = map.get(map.get(i));
                    }
                    if (modeParam1 == 1) param1 = map.get(i);
                    if (modeParam1 == 2) {
                        if (!map.containsKey(map.get(i) + relBase)) map.put(map.get(i) + relBase, 0L);
                        param1 = map.get(map.get(i) + relBase);
                    }
                    i++;
                    if (modeParam2 == 0) {
                        if (!map.containsKey(map.get(i))) map.put(map.get(i), 0L);
                        param2 = map.get(map.get(i));
                    }
                    if (modeParam2 == 1) param2 = map.get(i);
                    if (modeParam2 == 2) {
                        if (!map.containsKey(map.get(i) + relBase)) map.put(map.get(i) + relBase, 0L);
                        param2 = map.get(map.get(i) + relBase);
                    }
                    if (param1 != 0) i = param2;
                    else i++;
                    break;
                case "6":
                    i++;
                    if (modeParam1 == 0) {
                        if (!map.containsKey(map.get(i))) map.put(map.get(i), 0L);
                        param1 = map.get(map.get(i));
                    }
                    if (modeParam1 == 1) param1 = map.get(i);
                    if (modeParam1 == 2) {
                        if (!map.containsKey(map.get(i) + relBase)) map.put(map.get(i) + relBase, 0L);
                        param1 = map.get(map.get(i) + relBase);
                    }
                    i++;
                    if (modeParam2 == 0) {
                        if (!map.containsKey(map.get(i))) map.put(map.get(i), 0L);
                        param2 = map.get(map.get(i));
                    }
                    if (modeParam2 == 1) param2 = map.get(i);
                    if (modeParam2 == 2) {
                        if (!map.containsKey(map.get(i) + relBase)) map.put(map.get(i) + relBase, 0L);
                        param2 = map.get(map.get(i) + relBase);
                    }
                    if (param1 == 0) i = param2;
                    else i++;
                    break;
                case "7":
                    i++;
                    if (modeParam1 == 0) {
                        if (!map.containsKey(map.get(i))) map.put(map.get(i), 0L);
                        param1 = map.get(map.get(i));
                    }
                    if (modeParam1 == 1) param1 = map.get(i);
                    if (modeParam1 == 2) {
                        if (!map.containsKey(map.get(i) + relBase)) map.put(map.get(i) + relBase, 0L);
                        param1 = map.get(map.get(i) + relBase);
                    }
                    i++;
                    if (modeParam2 == 0) {
                        if (!map.containsKey(map.get(i))) map.put(map.get(i), 0L);
                        param2 = map.get(map.get(i));
                    }
                    if (modeParam2 == 1) param2 = map.get(i);
                    if (modeParam2 == 2) {
                        if (!map.containsKey(map.get(i) + relBase)) map.put(map.get(i) + relBase, 0L);
                        param2 = map.get(map.get(i) + relBase);
                    }
                    i++;
                    if (modeParam3 == 0) param3 = map.get(i);
                    if (modeParam3 == 1) param3 = i;
                    if (modeParam3 == 2) param3 = map.get(i) + relBase;
                    if (param1 < param2) {
                        if (map.containsKey(param3))
                            map.replace(param3, 1L);
                        else map.put(param3, 1L);
                    } else {
                        if (map.containsKey(param3))
                            map.replace(param3, 0L);
                        else map.put(param3, 0L);
                    }
                    i++;
                    break;
                case "8":
                    i++;
                    if (modeParam1 == 0) {
                        if (!map.containsKey(map.get(i))) map.put(map.get(i), 0L);
                        param1 = map.get(map.get(i));
                    }
                    if (modeParam1 == 1) param1 = map.get(i);
                    if (modeParam1 == 2) {
                        if (!map.containsKey(map.get(i) + relBase)) map.put(map.get(i) + relBase, 0L);
                        param1 = map.get(map.get(i) + relBase);
                    }
                    i++;
                    if (modeParam2 == 0) {
                        if (!map.containsKey(map.get(i))) map.put(map.get(i), 0L);
                        param2 = map.get(map.get(i));
                    }
                    if (modeParam2 == 1) param2 = map.get(i);
                    if (modeParam2 == 2) {
                        if (!map.containsKey(map.get(i) + relBase)) map.put(map.get(i) + relBase, 0L);
                        param2 = map.get(map.get(i) + relBase);
                    }
                    i++;
                    if (modeParam3 == 0) param3 = map.get(i);
                    if (modeParam3 == 1) param3 = i;
                    if (modeParam3 == 2) param3 = map.get(i) + relBase;

                    if (param1 == param2) {
                        if (map.containsKey(param3))
                            map.replace(param3, 1L);
                        else map.put(param3, 1L);
                    } else {
                        if (map.containsKey(param3))
                            map.replace(param3, 0L);
                        else map.put(param3, 0L);
                    }
                    i++;
                    break;
                case "9":
                    i++;
                    if (modeParam1 == 0) {
                        if (!map.containsKey(map.get(i))) map.put(map.get(i), 0L);
                        param1 = map.get(map.get(i));
                    }
                    if (modeParam1 == 1) param1 = map.get(i);
                    if (modeParam1 == 2) {
                        if (!map.containsKey(map.get(i) + relBase)) map.put(map.get(i) + relBase, 0L);
                        param1 = map.get(map.get(i) + relBase);
                    }
                    relBase = relBase + param1;
                    i++;
                    break;
            } // end switch
        } // end while
        return outList;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(",");
        List<Integer> outList = intComputer(input);
        int size = outList.size();
        int countBlocks = 0;
        for (int i = 2; i < size; i = i + 3)
            if (outList.get(i) == 2) countBlocks++;
        System.out.println("Answer = " + countBlocks);
    }
}
