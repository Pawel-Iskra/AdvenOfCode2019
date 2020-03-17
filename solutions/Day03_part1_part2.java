import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day3_part1_part2 {

    private static class VerticalLines {
        int valueX;
        int startY;
        int endY;

        public VerticalLines(int valueX, int startY, int endY) {
            this.valueX = valueX;
            this.startY = startY;
            this.endY = endY;
        }
    }

    private static class HorizontalLines {
        int valueY;
        int startX;
        int endX;

        public HorizontalLines(int valueY, int startX, int endX) {
            this.valueY = valueY;
            this.startX = startX;
            this.endX = endX;
        }
    }

    private static class Intersection {
        int valueX;
        int valueY;
        int stepsOfFirstWire;
        int stepsOfSecondWire;

        public Intersection(int valueX, int valueY, int stepsOfFirstWire, int stepsOfSecondWire) {
            this.valueX = valueX;
            this.valueY = valueY;
            this.stepsOfFirstWire = stepsOfFirstWire;
            this.stepsOfSecondWire = stepsOfSecondWire;
        }
    }

    private static boolean checkIfIntersectionWhileHorizontalWalking(List<VerticalLines> vLines, List<HorizontalLines> hLines, int lastX, int lastY) {
        for (int i = 0; i < vLines.size(); i++)
            if (lastX == vLines.get(i).valueX)
                if (lastY >= vLines.get(i).startY && lastY <= vLines.get(i).endY) return true;

        for (int i = 0; i < hLines.size(); i++)
            if (lastX >= hLines.get(i).startX && lastX <= hLines.get(i).endX)
                if (lastY == hLines.get(i).valueY) return true;

        return false;
    }

    private static boolean checkIfIntersectionWhileVerticalWalking(List<VerticalLines> vLines, List<HorizontalLines> hLines, int lastX, int lastY) {
        for (int i = 0; i < hLines.size(); i++)
            if (lastY == hLines.get(i).valueY)
                if (lastX >= hLines.get(i).startX && lastX <= hLines.get(i).endX) return true;

        for (int i = 0; i < vLines.size(); i++)
            if (lastY >= vLines.get(i).startY && lastY <= vLines.get(i).endY)
                if (lastX == vLines.get(i).valueX) return true;

        return false;
    }

    private static VerticalLines getVerticalLine(char direction, int lastY, int valueX, int steps) {
        int lineStart = lastY;
        int lineEnd = lastY;
        if (direction == 'D') lastY = lastY - steps;
        if (direction == 'U') lastY = lastY + steps;
        if (lastY > lineEnd) lineEnd = lastY;
        else lineStart = lastY;
        return new VerticalLines(valueX, lineStart, lineEnd);
    }

    private static HorizontalLines getHorizontalLine(char direction, int valueY, int lastX, int steps) {
        int lineStart = lastX;
        int lineEnd = lastX;
        if (direction == 'L') lastX = lastX - steps;
        if (direction == 'R') lastX = lastX + steps;
        if (lastX > lineEnd) lineEnd = lastX;
        else lineStart = lastX;
        return new HorizontalLines(valueY, lineStart, lineEnd);
    }

    private static int getNumberOfStepsOfFirstWire(int valX, int valY, List<Character> directs, List<Integer> values) {
        int globalSteps = 0;
        int size = directs.size();
        int lastX = 0, lastY = 0;
        for (int i = 0; i < size; i++) {
            char direction = directs.get(i);
            int val = values.get(i);
            int steps = 0;
            switch (direction) {
                case 'U':
                case 'D':
                    while (steps < val) {
                        if (direction == 'U') lastY++;
                        if (direction == 'D') lastY--;
                        globalSteps++;
                        steps++;
                        if (valX == lastX && valY == lastY) return globalSteps;
                    }
                    break;
                case 'R':
                case 'L':
                    while (steps < val) {
                        if (direction == 'R') lastX++;
                        if (direction == 'L') lastX--;
                        globalSteps++;
                        steps++;
                        if (valX == lastX && valY == lastY) return globalSteps;
                    }
                    break;
            } // end switch
        }
        return globalSteps;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Character> firstWireDirections = new ArrayList<>();            // direction list wire 1
        List<Integer> firstWireSteps = new ArrayList<>();                   // number of steps for each direction - wire 1
        String[] firstWirePath = br.readLine().split(",");
        int size1 = firstWirePath.length;
        for (int i = 0; i < size1; i++) {
            firstWireDirections.add(firstWirePath[i].charAt(0));
            firstWireSteps.add(Integer.parseInt(firstWirePath[i].substring(1)));
        }

        List<Character> secondWireDirections = new ArrayList<>();        // direction list wire 2
        List<Integer> secondWireSteps = new ArrayList<>();               // number of steps for each direction - wire 2
        String[] secondWirePath = br.readLine().split(",");
        int size2 = secondWirePath.length;
        for (int i = 0; i < size2; i++) {
            secondWireDirections.add(secondWirePath[i].charAt(0));
            secondWireSteps.add(Integer.parseInt(secondWirePath[i].substring(1)));
        }

        ///// MAKING THE "BROKEN LINE" MADE BY FIRST WIRE AS LIST OF HORIZONTAL LINES AND LIST OF VERTICAL LINES/////
        List<VerticalLines> firstWireVerticalLines = new ArrayList<>();      // list of vertical "lines" made by first wire
        List<HorizontalLines> firstWireHorizontalLines = new ArrayList<>();  // list of horizontal "lines" made by first wire
        int firstWireLastY = 0, firstWireLastX = 0;
        int firstWireDirectionsSize = firstWireDirections.size();
        for (int i = 0; i < firstWireDirectionsSize; i++) {
            char direction = firstWireDirections.get(i);
            int stepsMadeWire1 = firstWireSteps.get(i);
            switch (direction) {
                case 'D':
                case 'U':
                    firstWireVerticalLines.add(getVerticalLine(direction, firstWireLastY, firstWireLastX, stepsMadeWire1));
                    if (direction == 'D') firstWireLastY = firstWireLastY - stepsMadeWire1;
                    if (direction == 'U') firstWireLastY = firstWireLastY + stepsMadeWire1;
                    break;
                case 'L':
                case 'R':
                    firstWireHorizontalLines.add(getHorizontalLine(direction, firstWireLastY, firstWireLastX, stepsMadeWire1));
                    if (direction == 'L') firstWireLastX = firstWireLastX - stepsMadeWire1;
                    if (direction == 'R') firstWireLastX = firstWireLastX + stepsMadeWire1;
                    break;
            }
        }

        ///// CHECKING - in every step of second wire - IF SECOND WIRE CROSSED THE FIRST ONE /////
        List<Intersection> intersections = new ArrayList<>();
        int secondWireLastX = 0, secondWireLastY = 0;
        int globalStepsOfsecondWire = 0;
        int secondWireDirectionsSize = secondWireDirections.size();
        for (int i = 0; i < secondWireDirectionsSize; i++) {
            int stepsMadeWire2 = secondWireSteps.get(i);
            int stepsCounter = 0;
            char direction = secondWireDirections.get(i);
            switch (direction) {
                case 'R':
                case 'L':
                    while (stepsCounter < stepsMadeWire2) {
                        if (direction == 'R') secondWireLastX++;
                        if (direction == 'L') secondWireLastX--;
                        stepsCounter++;
                        globalStepsOfsecondWire++;
                        if (checkIfIntersectionWhileHorizontalWalking(firstWireVerticalLines, firstWireHorizontalLines, secondWireLastX, secondWireLastY))
                            intersections.add(new Intersection(secondWireLastX, secondWireLastY,
                                    getNumberOfStepsOfFirstWire(secondWireLastX, secondWireLastY, firstWireDirections, firstWireSteps),
                                    globalStepsOfsecondWire));
                    }
                    break;
                case 'U':
                case 'D':
                    while (stepsCounter < stepsMadeWire2) {
                        if (direction == 'U') secondWireLastY++;
                        if (direction == 'D') secondWireLastY--;
                        stepsCounter++;
                        globalStepsOfsecondWire++;
                        if (checkIfIntersectionWhileVerticalWalking(firstWireVerticalLines, firstWireHorizontalLines, secondWireLastX, secondWireLastY))
                            intersections.add(new Intersection(secondWireLastX, secondWireLastY,
                                    getNumberOfStepsOfFirstWire(secondWireLastX, secondWireLastY, firstWireDirections, firstWireSteps),
                                    globalStepsOfsecondWire));
                    }
                    break;
            } // end switch
        } // end for

        /// ANSWER FOR PART 1 ///
        int distanceToClosestIntersection = Integer.MAX_VALUE;
        int intersectionsSize = intersections.size();
        for (int i = 0; i < intersectionsSize; i++) {
            int coordX = Math.abs(intersections.get(i).valueX);
            int coordY = Math.abs(intersections.get(i).valueY);
            if ((coordX + coordY) < distanceToClosestIntersection)
                distanceToClosestIntersection = coordX + coordY;
        }
        System.out.println("Answer part 1 = " + distanceToClosestIntersection);

        /// ANSWER FOR PART 2 ///
        int intersectionWithLowestSteps = Integer.MAX_VALUE;
        for (int i = 0; i < intersectionsSize; i++) {
            int stepsWire1 = intersections.get(i).stepsOfFirstWire;
            int stepsWire2 = intersections.get(i).stepsOfSecondWire;
            if ((stepsWire1 + stepsWire2) < intersectionWithLowestSteps)
                intersectionWithLowestSteps = stepsWire1 + stepsWire2;
        }
        System.out.println("Answer part 2 = " + intersectionWithLowestSteps);
    }
}
