import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Day19_part1 {

    private static long getParam(long pointer, long paramMode, long relativeBase, Map<Long, Long> map) {

        long value = -100;

        if (paramMode == 0) {
            if (!map.containsKey(map.get(pointer))) map.put(map.get(pointer), 0L);
            value = map.get(map.get(pointer));
        }
        if (paramMode == 1) value = map.get(pointer);
        if (paramMode == 2) {
            if (!map.containsKey(map.get(pointer) + relativeBase)) map.put(map.get(pointer) + relativeBase, 0L);
            value = map.get(map.get(pointer) + relativeBase);
        }

        return value;
    }

    private static long getPointer(long pointer, long paramMode, long relativeBase, Map<Long, Long> map) {

        long value = -100;

        if (paramMode == 0) value = map.get(pointer);
        if (paramMode == 1) value = pointer;
        if (paramMode == 2) value = map.get(pointer) + relativeBase;

        return value;
    }

    private static int intComputer(String[] input, int x, int y) {

        Map<Long, Long> map = new HashMap<>();
        int size = input.length;
        long k = 0;
        for (int i = 0; i < size; i++, k++)
            map.put(k, Long.parseLong(input[i]));

        long param1 = 0, param2 = 0, param3 = 0;
        long relBase = 0;
        int inputCounter = 0;
        int out = -1;


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
                case "2":
                    param1 = getParam((i + 1), modeParam1, relBase, map);
                    param2 = getParam((i + 2), modeParam2, relBase, map);
                    param3 = getPointer((i + 3), modeParam3, relBase, map);

                    if (oppCodee.equals("1")) {
                        if (map.containsKey(param3))
                            map.replace(param3, (param1 + param2));
                        else map.put(param3, (param1 + param2));
                    }

                    if (oppCodee.equals("2")) {
                        if (map.containsKey(param3))
                            map.replace(param3, (param1 * param2));
                        else map.put(param3, (param1 * param2));
                    }

                    i = i + 4;
                    break;

                case "3":   // input
                    param1 = getPointer((i + 1), modeParam1, relBase, map);

                    long inputValue = -100;
                    if (inputCounter == 0) inputValue = x;
                    if (inputCounter == 1) inputValue = y;

                    if (map.containsKey(param1))
                        map.replace(param1, inputValue);
                    else map.put(param1, inputValue);

                    inputCounter++;
                    i = i + 2;
                    break;

                case "4":   // output
                    param1 = getPointer((i + 1), modeParam1, relBase, map);
                    if (!map.containsKey(param1)) map.put(param1, 0L);

                    out = (Integer.parseInt(String.valueOf(map.get(param1))));

                    i = i + 2;
                    break;

                case "5":
                case "6":
                    param1 = getParam((i + 1), modeParam1, relBase, map);
                    param2 = getParam((i + 2), modeParam2, relBase, map);

                    if (oppCodee.equals("5")) {
                        if (param1 != 0) i = param2;
                        else i = i + 3;
                    }

                    if (oppCodee.equals("6")) {
                        if (param1 == 0) i = param2;
                        else i = i + 3;
                    }
                    break;

                case "7":
                case "8":
                    param1 = getParam((i + 1), modeParam1, relBase, map);
                    param2 = getParam((i + 2), modeParam2, relBase, map);
                    param3 = getPointer((i + 3), modeParam3, relBase, map);

                    if (oppCodee.equals("7")) {
                        if (param1 < param2) {
                            if (map.containsKey(param3))
                                map.replace(param3, 1L);
                            else map.put(param3, 1L);
                        } else {
                            if (map.containsKey(param3))
                                map.replace(param3, 0L);
                            else map.put(param3, 0L);
                        }
                    }

                    if (oppCodee.equals("8")) {
                        if (param1 == param2) {
                            if (map.containsKey(param3))
                                map.replace(param3, 1L);
                            else map.put(param3, 1L);
                        } else {
                            if (map.containsKey(param3))
                                map.replace(param3, 0L);
                            else map.put(param3, 0L);
                        }
                    }
                    i = i + 4;
                    break;

                case "9":
                    param1 = getParam((i + 1), modeParam1, relBase, map);
                    relBase = relBase + param1;

                    i = i + 2;
                    break;
            }
        }
        return out;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(",");

        int size = 50;
        int pulled = 1;

        int sum = 0;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {

                int out = intComputer(input, x, y);
                if (out == pulled) sum++;
            }
        }

        System.out.println("Answer = " + sum);
    }
}
