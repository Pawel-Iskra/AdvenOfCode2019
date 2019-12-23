import java.sql.SQLOutput;

public class Day12_part1 {

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

        public Moon(int lastX, int lastY, int lastZ,
                    int newX, int newY, int newZ,
                    int velX, int velY, int velZ) {

            this.lastX = lastX;
            this.lastY = lastY;
            this.lastZ = lastZ;
            this.newX = newX;
            this.newY = newY;
            this.newZ = newZ;
            this.velX = velX;
            this.velY = velY;
            this.velZ = velZ;
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


    private static int totalEnergyCalc(Moon moon) {

        int potEn = Math.abs(moon.newX) + Math.abs(moon.newY) + Math.abs(moon.newZ);
        int kinEn = Math.abs(moon.velX) + Math.abs(moon.velY) + Math.abs(moon.velZ);

        return potEn * kinEn;
    }

    public static void main(String[] args) {

        ///// MY INPUT /////
        int startX_1 = -7, startX_2 = -12, startX_3 = 6, startX_4 = 4;
        int startY_1 = -8, startY_2 = -3, startY_3 = -17, startY_4 = -10;
        int startZ_1 = 9, startZ_2 = -4, startZ_3 = -9, startZ_4 = -6;


        Moon Io = new Moon(startX_1, startY_1, startZ_1, startX_1, startY_1, startZ_1, 0, 0, 0);
        Moon Europ = new Moon(startX_2, startY_2, startZ_2, startX_2, startY_2, startZ_2, 0, 0, 0);
        Moon Gany = new Moon(startX_3, startY_3, startZ_3, startX_3, startY_3, startZ_3, 0, 0, 0);
        Moon Calli = new Moon(startX_4, startY_4, startZ_4, startX_4, startY_4, startZ_4, 0, 0, 0);

        for (int i = 1; i <= 1000; i++) {

            // STEP 1 -> Gravity + Velocity: //
            valueCalculator(Io, Europ, Gany, Calli);
            valueCalculator(Europ, Io, Gany, Calli);
            valueCalculator(Gany, Europ, Io, Calli);
            valueCalculator(Calli, Europ, Gany, Io);

            // STEP 2 -> Updating values:
            updateLastValues(Io);
            updateLastValues(Europ);
            updateLastValues(Gany);
            updateLastValues(Calli);
        }

        ///// ENERGY CALCULATION /////
        int totalEnIo = totalEnergyCalc(Io);
        int totalEnEurop = totalEnergyCalc(Europ);
        int totalEnGany = totalEnergyCalc(Gany);
        int totalEnCalli = totalEnergyCalc(Calli);

        int totalSystemEnergy = totalEnCalli + totalEnEurop + totalEnGany + totalEnIo;


        System.out.println("Answer = " + totalSystemEnergy);
    }
}
