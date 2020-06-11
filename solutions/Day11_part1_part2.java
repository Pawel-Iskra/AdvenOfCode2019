import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Day11_part1_part2 {

    static class Panel {
        long x;
        long y;
        long colour;

        public Panel(long x, long y, long colour) {
            this.x = x;
            this.y = y;
            this.colour = colour;
        }
    }

    private static char getWalkDirection(char lastDirection, long currentTurn) {
        char out = lastDirection;
        if (lastDirection == 'E' && currentTurn == 0) out = 'N';
        if (lastDirection == 'E' && currentTurn == 1) out = 'S';
        if (lastDirection == 'W' && currentTurn == 0) out = 'S';
        if (lastDirection == 'W' && currentTurn == 1) out = 'N';
        if (lastDirection == 'N' && currentTurn == 0) out = 'W';
        if (lastDirection == 'N' && currentTurn == 1) out = 'E';
        if (lastDirection == 'S' && currentTurn == 0) out = 'E';
        if (lastDirection == 'S' && currentTurn == 1) out = 'W';
        return out;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(",");
        int x = input.length;
        for (int part = 1; part <= 2; part++) {
            List<Panel> panelList = new ArrayList<>();
            List<Character> directionList = new ArrayList<>();
            long inputCounter = 0;
            long outputCounter = 0;
            long lastx = 0;
            long lasty = 0;
            long paintedPanels = 0;
            Map<Long, Long> map = new HashMap<>();
            long k = 0;
            for (int i = 0; i < x; i++, k++)
                map.put(k, Long.parseLong(input[i]));
            long param1 = 0, param2 = 0, param3 = 0;
            long relBase = 0;
            long inputValue = -1;
            if (part == 1) inputValue = 0; // Part_1
            if (part == 2) inputValue = 1; // Part_2
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
                        long y = -1;
                        boolean flag = false;
                        if (inputCounter == 0) y = inputValue;
                        else {
                            int size = panelList.size();
                            for (int j = 0; j < size; j++) {
                                if (panelList.get(j).x == lastx) {
                                    if (panelList.get(j).y == lasty) {
                                        flag = true;
                                        y = panelList.get(j).colour;
                                        break;
                                    }
                                }
                            }
                        }
                        if (!flag && inputCounter > 0) y = 0;
                        if (map.containsKey(param1))
                            map.replace(param1, y);
                        else map.put(param1, y);
                        inputCounter++;
                        i++;
                        break;
                    case "4":   // output
                        i++;
                        if (modeParam1 == 0) param1 = map.get(i);
                        if (modeParam1 == 1) param1 = i;
                        if (modeParam1 == 2) param1 = map.get(i) + relBase;
                        if (!map.containsKey(param1)) map.put(param1, 0L);
                        long out = map.get(param1);
                        if (outputCounter % 2 == 0) {
                            boolean flag2 = false;
                            int size = panelList.size();
                            for (int j = 0; j < size; j++) {
                                if (panelList.get(j).x == lastx) {
                                    if (panelList.get(j).y == lasty) {
                                        panelList.get(j).colour = out;
                                        flag2 = true;
                                        break;
                                    }
                                }
                            }
                            if (!flag2) {
                                Panel panel = new Panel(lastx, lasty, out);
                                panelList.add(panel);
                                paintedPanels++;
                            }
                        }
                        if (outputCounter == 1) {
                            if (out == 0) {
                                lastx--;
                                directionList.add('W');
                            } else if (out == 1) {
                                lastx++;
                                directionList.add('E');
                            }
                        }
                        if (outputCounter % 4 == 3) {
                            char walkDirection = getWalkDirection(directionList.get(directionList.size() - 1), out);
                            if (walkDirection == 'N') {
                                directionList.add('N');
                                lasty++;
                            } else if (walkDirection == 'S') {
                                lasty--;
                                directionList.add('S');
                            }
                        }
                        if ((outputCounter > 1) && (outputCounter % 4 == 1)) {
                            char walkDirection = getWalkDirection(directionList.get(directionList.size() - 1), out);
                            if (walkDirection == 'E') {
                                directionList.add('E');
                                lastx++;
                            } else if (walkDirection == 'W') {
                                directionList.add('W');
                                lastx--;
                            }
                        }
                        outputCounter++;
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
            if (part == 1) System.out.println("Part_1 => Answer = " + paintedPanels);
            if (part == 2) {
                long maxX = panelList.get(0).x;
                long minX = panelList.get(0).x;
                long maxY = panelList.get(0).y;
                long minY = panelList.get(0).y;
                int size = panelList.size();
                for (int j = 0; j < size; j++) {
                    long tempX = panelList.get(j).x;
                    long tempY = panelList.get(j).y;
                    if (tempX > maxX) maxX = tempX;
                    if (tempX < minX) minX = tempX;
                    if (tempY > maxY) maxY = tempY;
                    if (tempY < minY) minY = tempY;
                }
                int hight = (int) (Math.abs(maxY - minY) + 1);
                int width = (int) (Math.abs(maxX - minX) + 1);
                char[][] registration = new char[hight][width];
                for (int g = 0; g < hight; g++) {
                    for (int h = 0; h < width; h++)
                        registration[g][h] = ' ';
                }
                for (int j = 0; j < size; j++) {
                    long tempX = panelList.get(j).x;
                    long tempY = panelList.get(j).y;
                    long tempC = panelList.get(j).colour;

                    if (tempC == 1) registration[(int) (tempY * (-1))][(int) tempX] = '#';
                }
                System.out.println("Part_2 => Answer:");
                for (int g = 0; g < hight; g++) {
                    for (int h = 0; h < width; h++) {
                        System.out.print(registration[g][h]);
                    }
                    System.out.println();
                }
            } // end if (part == 2)
        } // end main for

    }
}
