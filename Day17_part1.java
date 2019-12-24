import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day17_part1 {

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
                case "2":
                    i++;
                    param1 = getParam(i, modeParam1, relBase, map);

                    i++;
                    param2 = getParam(i, modeParam2, relBase, map);

                    i++;
                    param3 = getPointer(i, modeParam3, relBase, map);

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

                    i++;
                    break;

                case "3":   // input
                    i++;
                    param1 = getPointer(i, modeParam1, relBase, map);

                    i++;
                    break;

                case "4":   // output

                    i++;
                    param1 = getPointer(i, modeParam1, relBase, map);
                    if (!map.containsKey(param1)) map.put(param1, 0L);

                    outList.add(Integer.parseInt(String.valueOf(map.get(param1))));

                    i++;
                    break;

                case "5":
                case "6":

                    i++;
                    param1 = getParam(i, modeParam1, relBase, map);

                    i++;
                    param2 = getParam(i, modeParam2, relBase, map);

                    if (oppCodee.equals("5")) {
                        if (param1 != 0) i = param2;
                        else i++;
                    }

                    if (oppCodee.equals("6")) {
                        if (param1 == 0) i = param2;
                        else i++;
                    }

                    break;

                case "7":
                case "8":

                    i++;
                    param1 = getParam(i, modeParam1, relBase, map);
                    i++;
                    param2 = getParam(i, modeParam2, relBase, map);
                    i++;
                    param3 = getPointer(i, modeParam3, relBase, map);

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

                    i++;
                    break;

                case "9":

                    i++;
                    param1 = getParam(i, modeParam1, relBase, map);

                    relBase = relBase + param1;

                    i++;
                    break;
            }
        }
        return outList;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(",");
        int scaffoldChar = 35;
        int newLineChar = 10;


        List<Integer> outList = intComputer(input);
        int size = outList.size();


        int lineLength = 0;
        for (int i = 0; i < size; i++) {
            if (outList.get(i) == newLineChar) {
                lineLength = i;
                break;
            }
        }

        /// removing newLine chars ///
        for (int i = 0; i < outList.size(); i++)
            if (outList.get(i) == newLineChar) outList.remove(i);


        /// searching for scaffold intersections && calculating parameters ///
        int sum = 0;
        size = outList.size() - lineLength;

        for (int i = lineLength; i < size; i++) {  // skipping first and last horizontal line

            if (i % lineLength == 0 || i % lineLength == (lineLength - 1)) continue;  // skipping first and last vertical line
            else if (outList.get(i) == scaffoldChar) {
                if (outList.get(i - 1) == scaffoldChar && outList.get(i + 1) == scaffoldChar &&
                        outList.get(i + lineLength) == scaffoldChar && outList.get(i - lineLength) == scaffoldChar) {
                    sum = sum + (i % lineLength) * (i / lineLength);
                }
            }
        }

        System.out.println("Answer = " + sum);

    }
}
