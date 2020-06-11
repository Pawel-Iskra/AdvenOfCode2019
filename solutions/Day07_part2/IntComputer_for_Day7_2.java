package Day07_part2;

import java.util.Map;

public class IntComputer_for_Day7_2 {
    long phase;
    Map<Long, Long> map;
    public IntComputer_for_Day7_2(int phase, Map<Long, Long> map) {
        this.phase = phase;
        this.map = map;
    }
    public boolean isHasOut() {
        return hasOut;
    }
    public boolean isEnd() {
        return end;
    }
    public long getOut() {
        return out;
    }
    public long getI() {
        return i;
    }
    private long getParam(long pointer, long paramMode, long relativeBase) {
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
    private long getPointer(long pointer, long paramMode, long relativeBase) {
        long value = -100;
        if (paramMode == 0) value = map.get(pointer);
        if (paramMode == 1) value = pointer;
        if (paramMode == 2) value = map.get(pointer) + relativeBase;
        return value;
    }
    private boolean hasOut = false;
    private boolean end = false;
    private long out = -10;
    private long i = 0;
    private int inputCounter = 0;
    void intComputer(long input, long pointer) {
        long param1 = 0, param2 = 0, param3 = 0, relBase = 0, in = -10;
        boolean flag = false;
        i = pointer;
        while (true) {
            if (map.get(i) == 99 || flag) {
                if (map.get(i) == 99) end = true;
                hasOut = true;
                break;
            }
            hasOut = false;
            long instr = map.get(i);
            long opCode = instr % 10;
            long modeParam1 = (instr / 100) % 10;
            long modeParam2 = (instr / 1000) % 10;
            long modeParam3 = (instr / 10000) % 10;
            String oppCodee = String.valueOf(opCode);
            switch (oppCodee) {
                case "1":
                case "2":
                    param1 = getParam(++i, modeParam1, relBase);
                    param2 = getParam(++i, modeParam2, relBase);
                    param3 = getPointer(++i, modeParam3, relBase);
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
                    param1 = getPointer(++i, modeParam1, relBase);
                    if (inputCounter == 0) in = phase;
                    if (inputCounter > 0) in = input;
                    if (map.containsKey(param1))
                        map.replace(param1, in);
                    else map.put(param1, in);
                    inputCounter++;
                    i++;
                    break;
                case "4":   // output
                    param1 = getPointer(++i, modeParam1, relBase);
                    if (!map.containsKey(param1)) map.put(param1, 0L);
                    out = map.get(param1);
                    flag = true;
                    i++;
                    break;
                case "5":
                case "6":
                    param1 = getParam(++i, modeParam1, relBase);
                    param2 = getParam(++i, modeParam2, relBase);
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
                    param1 = getParam(++i, modeParam1, relBase);
                    param2 = getParam(++i, modeParam2, relBase);
                    param3 = getPointer(++i, modeParam3, relBase);
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
                    param1 = getParam(++i, modeParam1, relBase);
                    relBase = relBase + param1;
                    i++;
                    break;
            }
        }
    } /// intComp
}
