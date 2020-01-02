import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Day10_part2 {

    static class Asteroid {
        int x;
        int y;
        int realX;
        int realY;

        public Asteroid(int x, int y, int realX, int realY) {
            this.x = x;
            this.y = y;
            this.realX = realX;
            this.realY = realY;
        }
    }

    static class Direction {
        double angle;
        int x;
        int y;
        List<Asteroid> asteroidList;

        public Direction(double angle, int x, int y, List<Asteroid> asteroidList) {
            this.angle = angle;
            this.x = x;
            this.y = y;
            this.asteroidList = asteroidList;
        }
    }

    static List<Asteroid> getSortedAsteroidsForStraight(List<char[]> map, char dir, int posX, int posY) {
        List<Asteroid> asteroids = new ArrayList<>();
        char anAsteroid = '#';

        switch (dir) {
            case 'N':
                for (int i = posY - 1; i >= 0; i--)
                    if (map.get(i)[posX] == anAsteroid) asteroids.add(new Asteroid(posX, i, posX, i));
                break;
            case 'S':
                for (int i = posY + 1; i < map.size(); i++)
                    if (map.get(i)[posX] == anAsteroid) asteroids.add(new Asteroid(posX, i, posX, i));
                break;
            case 'W':
                for (int i = posX - 1; i >= 0; i--)
                    if (map.get(posY)[i] == anAsteroid) asteroids.add(new Asteroid(i, posY, i, posY));
                break;
            case 'E':
                for (int i = posX + 1; i < map.get(0).length; i++)
                    if (map.get(posY)[i] == anAsteroid) asteroids.add(new Asteroid(i, posY, i, posY));
                break;
        }
        return asteroids;
    }

    static List<Asteroid> getSortedAsteroidsForDirection(List<char[]> map, int dirX, int dirY, int posX, int posY, int startX, int endX, int startY, int endY) {
        List<Asteroid> asteroids = new ArrayList<>();
        char anAsteroid = '#';

        int sizeX = map.get(0).length;

        for (int i = startX; i <= endX; i++) {       // i -> X
            for (int j = startY; j <= endY; j++) {   // j -> Y

                if (map.get(j)[i] == anAsteroid) {

                    int virtX = Math.abs(i - posX);
                    int virtY = Math.abs(j - posY);

                    if (virtY == dirY && virtX == dirX) asteroids.add(new Asteroid(virtX, virtY, i, j));
                    for (int z = 2; z < sizeX; z++) {
                        if (virtX == (z * dirX) && virtY == (z * dirY)) {
                            asteroids.add(new Asteroid(virtX, virtY, i, j));
                        }
                    }
                }
            }
        }

        /// BUBBLE SORT FOR ASTEROID LIST ///
        for (int i = 0; i < asteroids.size(); i++) {
            for (int j = 0; j < asteroids.size() - 1; j++) {

                Asteroid tempFirst = asteroids.get(j);
                Asteroid tempSecond = asteroids.get(j + 1);

                if (tempFirst.x > tempSecond.x) {
                    asteroids.set(j, tempSecond);
                    asteroids.set(j + 1, tempFirst);
                }
            }
        }

        return asteroids;
    }

    static List<Direction> getSortedDirectionList(List<char[]> map, int startX, int endX, int startY, int endY, int posX, int posY, int quarter) {
        List<Direction> directionList = new ArrayList<>();
        int sizeX = map.get(0).length;

        for (int i = startX; i <= endX; i++) {           // X
            for (int j = startY; j <= endY; j++) {       // Y

                int virtX = Math.abs(i - posX);
                int virtY = Math.abs(j - posY);

                boolean flag = false;
                for (int k = 2; k < sizeX; k++) if (virtX % k == 0 && virtY % k == 0) flag = true;
                if (!flag) {
                    double quotient = (double) virtX / virtY;
                    directionList.add(new Direction(Math.round(quotient * 1000.0) / 1000.0, virtX, virtY,
                            getSortedAsteroidsForDirection(map, virtX, virtY, posX, posY, startX, endX, startY, endY)));
                }
            }
        }

        // BUBLE SORT FOR DIRECTIONS //
        int listSize = directionList.size();
        for (int i = 0; i < listSize; i++) {
            for (int j = 0; j < listSize - 1; j++) {

                Direction first = directionList.get(j);
                Direction second = directionList.get(j + 1);

                if (quarter == 1 || quarter == 3) {
                    if (first.angle > second.angle) {
                        directionList.set(j, second);
                        directionList.set(j + 1, first);
                    }
                } else {
                    if (first.angle < second.angle) {
                        directionList.set(j, second);
                        directionList.set(j + 1, first);
                    }
                }
            }
        }

        return directionList;
    }

    static List<Direction> getSortedDirectionListForQuarter(List<char[]> map, int posX, int posY, int quarter) {
        int sizeX = map.get(0).length;
        int sizeY = map.size();
        int startX = 0, endX = 0, startY = 0, endY = 0;

        switch (quarter) {
            case 1:
                startX = posX + 1;
                endX = sizeX - 1;
                startY = 0;
                endY = posY - 1;
                break;
            case 2:
                startX = 0;
                endX = posX - 1;
                startY = 0;
                endY = posY - 1;
                break;
            case 3:
                startX = 0;
                endX = posX - 1;
                startY = posY + 1;
                endY = sizeY - 1;
                break;
            case 4:
                startX = posX + 1;
                endX = sizeX - 1;
                startY = posY + 1;
                endY = sizeY - 1;
                break;
        }

        List<Direction> directions = getSortedDirectionList(map, startX, endX, startY, endY, posX, posY, quarter);
        return directions;
    }

    static int vaporizeInStright(List<Asteroid> asteroids) {
        int numberOfVaporizedAsteroids = 0;
        if (asteroids.size() > 0) {

        }

        return numberOfVaporizedAsteroids;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int stationPositionX = 11;
        int stationPositionY = 19;

        List<char[]> map = new ArrayList<>();
        String input;
        while ((input = br.readLine()) != null) {
            char[] line = input.toCharArray();
            map.add(line);
        }

        List<Asteroid> north = getSortedAsteroidsForStraight(map, 'N', stationPositionX, stationPositionY);
        List<Asteroid> south = getSortedAsteroidsForStraight(map, 'S', stationPositionX, stationPositionY);
        List<Asteroid> west = getSortedAsteroidsForStraight(map, 'W', stationPositionX, stationPositionY);
        List<Asteroid> east = getSortedAsteroidsForStraight(map, 'E', stationPositionX, stationPositionY);

        List<Direction> firstQuarter = getSortedDirectionListForQuarter(map, stationPositionX, stationPositionY, 1);
        List<Direction> secondQuarter = getSortedDirectionListForQuarter(map, stationPositionX, stationPositionY, 2);
        List<Direction> thirdQuarter = getSortedDirectionListForQuarter(map, stationPositionX, stationPositionY, 3);
        List<Direction> fourthQuarter = getSortedDirectionListForQuarter(map, stationPositionX, stationPositionY, 4);

        /// cleaning empty directions in each Quarter ///
        for (int i = 0; i < firstQuarter.size(); i++)
            if (firstQuarter.get(i).asteroidList.size() == 0) firstQuarter.remove(i);
        for (int i = 0; i < secondQuarter.size(); i++)
            if (secondQuarter.get(i).asteroidList.size() == 0) secondQuarter.remove(i);
        for (int i = 0; i < thirdQuarter.size(); i++)
            if (thirdQuarter.get(i).asteroidList.size() == 0) thirdQuarter.remove(i);
        for (int i = 0; i < fourthQuarter.size(); i++)
            if (fourthQuarter.get(i).asteroidList.size() == 0) fourthQuarter.remove(i);

        /// VAPORIZING: LOOP: (N -> Q1 -> E -> Q4 -> S -> Q3 -> W -> Q2) ///
        int counter = 0;
        int numbOfVaporizedAsteroid = 200;
        while (true) {

            // DIRECTION N //
            if (north.size() > 0) {
                Asteroid temp = north.get(0);
                north.remove(0);
                counter++;
                if (counter == numbOfVaporizedAsteroid) {
                    System.out.println("Answer = " + (100 * temp.realX + temp.realY));
                    break;
                }
            }

            // QUARTER I //
            for (int i = 0; i < firstQuarter.size(); i++) {
                if (firstQuarter.get(i).asteroidList.size() > 0) {
                    Asteroid temp = firstQuarter.get(i).asteroidList.get(0);
                    firstQuarter.get(i).asteroidList.remove(0);
                    counter++;
                    if (counter == numbOfVaporizedAsteroid) {
                        System.out.println("Answer = " + (100 * temp.realX + temp.realY));
                        break;
                    }
                }
            }

            // DIRECTION E //
            if (east.size() > 0) {
                Asteroid temp = east.get(0);
                east.remove(0);
                counter++;
                if (counter == numbOfVaporizedAsteroid) {
                    System.out.println("Answer = " + (100 * temp.realX + temp.realY));
                    break;
                }
            }

            // QUARTER IV //
            for (int i = 0; i < fourthQuarter.size(); i++) {
                if (fourthQuarter.get(i).asteroidList.size() > 0) {
                    Asteroid temp = fourthQuarter.get(i).asteroidList.get(0);
                    fourthQuarter.get(i).asteroidList.remove(0);
                    counter++;
                    if (counter == numbOfVaporizedAsteroid) {
                        System.out.println("Answer = " + (100 * temp.realX + temp.realY));
                        break;
                    }
                }
            }

            // DIRECTION S //
            if (south.size() > 0) {
                Asteroid temp = south.get(0);
                south.remove(0);
                counter++;
                if (counter == numbOfVaporizedAsteroid) {
                    System.out.println("Answer = " + (100 * temp.realX + temp.realY));
                    break;
                }
            }

            // QUARTER III //
            for (int i = 0; i < thirdQuarter.size(); i++) {
                if (thirdQuarter.get(i).asteroidList.size() > 0) {
                    Asteroid temp = thirdQuarter.get(i).asteroidList.get(0);
                    thirdQuarter.get(i).asteroidList.remove(0);
                    counter++;
                    if (counter == numbOfVaporizedAsteroid) {
                        System.out.println("Answer = " + (100 * temp.realX + temp.realY));
                        break;
                    }
                }
            }

            // DIRECTION W //
            if (west.size() > 0) {
                Asteroid temp = west.get(0);
                west.remove(0);
                counter++;
                if (counter == numbOfVaporizedAsteroid) {
                    System.out.println("Answer = " + (100 * temp.realX + temp.realY));
                    break;
                }
            }

            // QUARTER II //
            for (int i = 0; i < secondQuarter.size(); i++) {
                if (secondQuarter.get(i).asteroidList.size() > 0) {
                    Asteroid temp = secondQuarter.get(i).asteroidList.get(0);
                    secondQuarter.get(i).asteroidList.remove(0);
                    counter++;
                    if (counter == numbOfVaporizedAsteroid) {
                        System.out.println("Answer = " + (100 * temp.realX + temp.realY));
                        break;
                    }
                }
            }

        } // end while


    }
}
