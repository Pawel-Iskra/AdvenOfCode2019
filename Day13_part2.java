import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day13_part2 {

    private static class Globals {
        static long empty = 0;
        static long wall = 1;
        static long block = 2;
        static long paddle = 3;
        static long ball = 4;
    }

    private static long getPointer(long pointer, long paramMode, long relativeBase, Map<Long, Long> map) {
        long value = -100;
        if (paramMode == 0) value = map.get(pointer);
        if (paramMode == 1) value = pointer;
        if (paramMode == 2) value = map.get(pointer) + relativeBase;
        return value;
    }

    private static long getParam(long pointer, long paramMode, long relativeBase, Map<Long, Long> map) {
        long value = -1000;
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

    private static long getMoveForPaddle(List<Long> list) {
        long lastXPaddle = 0, lastXBall = 0, move = 0;

        int size = list.size();
        for (int i = 2; i < size; i = i + 3) {
            long temp = list.get(i);
            if (temp != -1) {
                if (temp == Globals.ball) lastXBall = list.get(i - 2);
                if (temp == Globals.paddle) lastXPaddle = list.get(i - 2);
            }
        }
        if (lastXBall < lastXPaddle) move = -1;
        if (lastXBall > lastXPaddle) move = 1;
        if (lastXBall == lastXPaddle) move = 0;

        return move;
    }

    private static long getFinalScore(List<Long> outList) {
        long score = 0;
        int size = outList.size();
        for (int j = 0; j < size; j++) {
            if (outList.get(j) == -1 && outList.get(j + 1) == 0) score = outList.get(j + 2);
        }
        return score;
    }

    private static long intComputer(Map<Long, Long> map) {
        List<Long> outList = new ArrayList<>();
        long out = 0, param1 = 0, param2 = 0, param3 = 0, relBase = 0, value = 0;

        long i = 0;
        while (map.get(i) != 99) {

            long instr = map.get(i);
            long opCode = instr % 10;
            long modeParam1 = (instr / 100) % 10;
            long modeParam2 = (instr / 1000) % 10;
            long modeParam3 = (instr / 10000) % 10;

            String oppCodee = String.valueOf(opCode);
            switch (oppCodee) {
                case "1":
                case "2":
                    param1 = getParam(++i, modeParam1, relBase, map);
                    param2 = getParam(++i, modeParam2, relBase, map);
                    param3 = getPointer(++i, modeParam3, relBase, map);
                    if (oppCodee.equals("1")) value = param1 + param2;
                    if (oppCodee.equals("2")) value = param1 * param2;
                    if (map.containsKey(param3)) map.replace(param3, value);
                    else map.put(param3, value);
                    i++;
                    break;
                case "3":   // input
                    param1 = getPointer(++i, modeParam1, relBase, map);
                    long moveForPaddle = getMoveForPaddle(outList);
                    if (map.containsKey(param1)) map.replace(param1, moveForPaddle);
                    else map.put(param1, moveForPaddle);
                    i++;
                    break;
                case "4":   // output
                    param1 = getPointer(++i, modeParam1, relBase, map);
                    if (!map.containsKey(param1)) map.put(param1, 0L);
                    out = map.get(param1);
                    outList.add(out);
                    i++;
                    break;
                case "5":
                case "6":
                    param1 = getParam(++i, modeParam1, relBase, map);
                    param2 = getParam(++i, modeParam2, relBase, map);
                    if (oppCodee.equals("5"))
                        if (param1 != 0) i = param2;
                        else i++;
                    if (oppCodee.equals("6"))
                        if (param1 == 0) i = param2;
                        else i++;
                    break;
                case "7":
                case "8":
                    param1 = getParam(++i, modeParam1, relBase, map);
                    param2 = getParam(++i, modeParam2, relBase, map);
                    param3 = getPointer(++i, modeParam3, relBase, map);
                    if ((oppCodee.equals("7") && param1 < param2) || (oppCodee.equals("8") && param1 == param2)) {
                        if (map.containsKey(param3)) map.replace(param3, 1L);
                        else map.put(param3, 1L);
                    } else {
                        if (map.containsKey(param3)) map.replace(param3, 0L);
                        else map.put(param3, 0L);
                    }
                    i++;
                    break;
                case "9":
                    param1 = getParam(++i, modeParam1, relBase, map);
                    relBase = relBase + param1;
                    i++;
                    break;
            }
        }
        return getFinalScore(outList);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(",");
        int length = input.length;
        input[0] = "2";  // setting Play mode

        Map<Long, Long> soft = new HashMap<>();
        long k = 0;
        for (int i = 0; i < length; i++, k++)
            soft.put(k, Long.parseLong(input[i]));

        long score = intComputer(soft);
        System.out.println("Answer = " + score);
    }
}
