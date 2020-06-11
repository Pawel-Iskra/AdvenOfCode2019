public class Day12_part2 {

    static class Moon {
        int lastX;
        int lastY;
        int lastZ;
        int newX;
        int newY;
        int newZ;
        int velX;
        int velY;
        int velZ;
        int startPosX;
        int startPosY;
        int startPosZ;

        public Moon(int valueX, int valueY, int valueZ,
                    int velX, int velY, int velZ) {
            this.lastX = valueX;
            this.lastY = valueY;
            this.lastZ = valueZ;
            this.newX = valueX;
            this.newY = valueY;
            this.newZ = valueZ;
            this.velX = velX;
            this.velY = velY;
            this.velZ = velZ;
            this.startPosX = valueX;
            this.startPosY = valueY;
            this.startPosZ = valueZ;
        }
    }

    private static void valueCalculator(Moon moonToUpdate, Moon moon2, Moon moon3, Moon moon4) {
        int lastX = moonToUpdate.lastX;
        int lastY = moonToUpdate.lastY;
        int lastZ = moonToUpdate.lastZ;
        int diff;
        ///////// Counting X /////////
        diff = 0;
        if (lastX > moon2.lastX) diff--;
        if (lastX > moon3.lastX) diff--;
        if (lastX > moon4.lastX) diff--;

        if (lastX < moon2.lastX) diff++;
        if (lastX < moon3.lastX) diff++;
        if (lastX < moon4.lastX) diff++;
        moonToUpdate.velX = moonToUpdate.velX + diff;
        moonToUpdate.newX = lastX + moonToUpdate.velX;
        ///////// Counting Y /////////
        diff = 0;
        if (lastY > moon2.lastY) diff--;
        if (lastY > moon3.lastY) diff--;
        if (lastY > moon4.lastY) diff--;

        if (lastY < moon2.lastY) diff++;
        if (lastY < moon3.lastY) diff++;
        if (lastY < moon4.lastY) diff++;
        moonToUpdate.velY = moonToUpdate.velY + diff;
        moonToUpdate.newY = lastY + moonToUpdate.velY;
        ///////// Counting Z /////////
        diff = 0;
        if (lastZ > moon2.lastZ) diff--;
        if (lastZ > moon3.lastZ) diff--;
        if (lastZ > moon4.lastZ) diff--;

        if (lastZ < moon2.lastZ) diff++;
        if (lastZ < moon3.lastZ) diff++;
        if (lastZ < moon4.lastZ) diff++;
        moonToUpdate.velZ = moonToUpdate.velZ + diff;
        moonToUpdate.newZ = lastZ + moonToUpdate.velZ;
    }

    private static void updateLastValues(Moon moon) {
        moon.lastX = moon.newX;
        moon.lastY = moon.newY;
        moon.lastZ = moon.newZ;
    }

    private static boolean checkWithFirsts(Moon moon, char coord) {
        if (coord == 'x')
            if (moon.startPosX == moon.lastX) return true;
            else return false;
        if (coord == 'y')
            if (moon.startPosY == moon.lastY) return true;
            else return false;
        if (coord == 'z')
            if (moon.startPosZ == moon.lastZ) return true;
            else return false;
        else return false;
    }

    private static int findPeriod(Moon m1, Moon m2, Moon m3, Moon m4, char coord) {
        int period = -1;
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            // STEP 1 -> Gravity + Velocity: //
            valueCalculator(m1, m2, m3, m4);
            valueCalculator(m2, m1, m3, m4);
            valueCalculator(m3, m1, m2, m4);
            valueCalculator(m4, m1, m2, m3);
            // STEP 2 -> Updating values:
            updateLastValues(m1);
            updateLastValues(m2);
            updateLastValues(m3);
            updateLastValues(m4);
            // Checking with the firsts
            boolean flagIo = checkWithFirsts(m1, coord);
            boolean flagEurop = checkWithFirsts(m2, coord);
            boolean flagGany = checkWithFirsts(m3, coord);
            boolean flagCalli = checkWithFirsts(m4, coord);

            if (flagIo && flagEurop && flagGany && flagCalli) {
                period = i + 1;
                break;
            }
        }
        return period;
    }

    private static long getLeastCommonMultiple(long n1, long n2) {
        long small = 0;
        long big = 0;
        long reminder = 0;
        if (n1 > n2) {
            big = n1;
            small = n2;
        } else {
            big = n2;
            small = n1;
        }
        while ((reminder = big % small) != 0) {
            big = small;
            small = reminder;
        }
        return (n1 / small * n2);
    }

    public static void main(String[] args) {
        ///// MY INPUT /////
        int startX_1 = -7, startX_2 = -12, startX_3 = 6, startX_4 = 4;
        int startY_1 = -8, startY_2 = -3, startY_3 = -17, startY_4 = -10;
        int startZ_1 = 9, startZ_2 = -4, startZ_3 = -9, startZ_4 = -6;
        int startVel = 0;

        char[] coordinates = {'x', 'y', 'z'};
        int[] periods = new int[3];
        for (int i = 0; i < 3; i++) {
            Moon Io = new Moon(startX_1, startY_1, startZ_1, startVel, startVel, startVel);
            Moon Europ = new Moon(startX_2, startY_2, startZ_2,startVel, startVel, startVel);
            Moon Gany = new Moon(startX_3, startY_3, startZ_3, startVel, startVel, startVel);
            Moon Calli = new Moon(startX_4, startY_4, startZ_4, startVel, startVel, startVel);
            periods[i] = findPeriod(Io, Europ, Gany, Calli, coordinates[i]);
        }
        long commonPeriod = getLeastCommonMultiple(getLeastCommonMultiple(periods[0], periods[1]), periods[2]);
        System.out.println("Answer = " + commonPeriod);
    }
}
