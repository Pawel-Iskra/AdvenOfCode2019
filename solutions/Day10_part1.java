import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day10_part1 {

    static class Direction {
        int x;
        int y;

        public Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Point {
        int x;
        int y;
        boolean isAsteroid;

        public Point(int x, int y, boolean isAsteroid) {
            this.x = x;
            this.y = y;
            this.isAsteroid = isAsteroid;
        }
    }

    static class Position {
        int coordX;
        int coordY;
        int detected;

        public Position(int coordX, int coordY, int detected) {
            this.coordX = coordX;
            this.coordY = coordY;
            this.detected = detected;
        }
    }

    private static int countAsteroidsInHorizontalLine(int posX, int posY, List<char[]> map) {
        int counter = 0;
        char anAsteroid = '#';
        int sizeX = map.get(0).length;

        for (int i = 0; i < posX; i++) {
            if (map.get(posY)[i] == anAsteroid) {
                counter++;
                break;
            }
        }

        for (int i = (posX + 1); i < sizeX; i++) {
            if (map.get(posY)[i] == anAsteroid) {
                counter++;
                break;
            }
        }

        return counter;
    }

    private static int countAsteroidsInVerticalLine(int posX, int posY, List<char[]> map) {
        int counter = 0;
        char anAsteroid = '#';
        int sizeY = map.size();

        for (int i = 0; i < posY; i++) {
            if (map.get(i)[posX] == anAsteroid) {
                counter++;
                break;
            }
        }

        for (int i = (posY + 1); i < sizeY; i++) {
            if (map.get(i)[posX] == anAsteroid) {
                counter++;
                break;
            }
        }

        return counter;
    }

    private static int countAsteroidsInAQuarter(int posX, int posY, List<char[]> map, int quarterNumb) {
        char anAsteroid = '#';
        int counter = 0;

        int sizeX = map.get(0).length;
        int sizeY = map.size();
        int max = 0, startX = 0, startY = 0, endX = 0, endY = 0;

        switch (quarterNumb) {
            case 1:
                if (posX > posY) max = posX;
                else max = posY;
                startX = posX - 1;
                endX = 0;
                startY = posY - 1;
                endY = 0;
                break;
            case 2:
                if (posX > posY) max = sizeX - posX;
                else max = posY;
                startX = sizeX - 1;
                endX = posX + 1;
                startY = posY - 1;
                endY = 0;
                break;
            case 3:
                if (posX > posY) max = posX;
                else max = sizeY - posY;
                startX = posX - 1;
                endX = 0;
                startY = sizeY - 1;
                endY = posY + 1;
                break;
            case 4:
                if (posX > posY) max = sizeX - posX;
                else max = sizeY - posY;
                startX = sizeX - 1;
                endX = posX + 1;
                startY = sizeY - 1;
                endY = posY + 1;
                break;
        }

        /// creating list of all points in a quarter as "my virtual Matrix"=>  coordinate system that: posX=0 i posY=0 ///
        List<Point> points = new ArrayList<>();
        for (int i = startX; i >= endX; i--) {        // i -> X
            for (int j = startY; j >= endY; j--) {    // j -> Y

                boolean isThereAnAsteroid = false;
                if (map.get(j)[i] == anAsteroid) isThereAnAsteroid = true;
                points.add(new Point(Math.abs(i - posX), Math.abs(j - posY), isThereAnAsteroid));
            }
        }

        /// creating list of all directions in a quarter as "my virtual Matrix"=>  coordinate system that: posX=0 i posY=0 ///
        List<Direction> directions = new ArrayList<>();
        for (int i = startX; i >= endX; i--) {        // i -> X
            for (int j = startY; j >= endY; j--) {    // j -> Y

                boolean flag = false;
                int virtualX = Math.abs(i - posX);    // virtual X
                int virtualY = Math.abs(j - posY);    // virtual Y

                for (int k = 2; k <= max; k++) if ((virtualX % k == 0) && (virtualY % k == 0)) flag = true;

                if (!flag) directions.add(new Direction(virtualX, virtualY));
            }
        }

        // for each direction : 1. check if there is an point with an asteroid with the same coords
        //                      2. if not check points with multiples of coordinates
        int dirSize = directions.size();
        for (int i = 0; i < dirSize; i++) {

            int dirX = directions.get(i).x;
            int dirY = directions.get(i).y;

            boolean flag = false;
            int pointsSize = points.size();
            for (int j = 0; j < pointsSize; j++) {

                if (flag) break;
                if (points.get(j).x == dirX && points.get(j).y == dirY) {

                    if (points.get(j).isAsteroid) {
                        counter++;
                        break;

                    } else {
                        int k = 2;
                        int dirXinWhile = dirX * k;
                        int dirYinWhile = dirY * k;

                        while (k <= max) {
                            if (flag) break;

                            for (int g = 0; g < pointsSize; g++) {

                                if (points.get(g).x == dirXinWhile && points.get(g).y == dirYinWhile) {

                                    if (points.get(g).isAsteroid) {
                                        counter++;
                                        flag = true;
                                        break;
                                    }
                                }
                            } // end for - searching in pointsList - in while
                            k++;
                            dirXinWhile = dirX * k;
                            dirYinWhile = dirY * k;
                        } // end while
                    } // end if-else
                } // end if x == dirX && y == dirY
            } // end for - searching in pointsList
        } // end for - for each direction

        return counter;
    }

    private static int getNumberOfDetectedAsteroids(int posX, int posY, List<char[]> map) {
        int counter = 0;
        counter = counter + countAsteroidsInVerticalLine(posX, posY, map);
        counter = counter + countAsteroidsInHorizontalLine(posX, posY, map);
        counter = counter + countAsteroidsInAQuarter(posX, posY, map, 1);
        counter = counter + countAsteroidsInAQuarter(posX, posY, map, 2);
        counter = counter + countAsteroidsInAQuarter(posX, posY, map, 3);
        counter = counter + countAsteroidsInAQuarter(posX, posY, map, 4);

        return counter;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<char[]> map = new ArrayList<>();
        char anAsteroid = '#';

        String input;
        while ((input = br.readLine()) != null) {
            char[] line = input.toCharArray();
            map.add(line);
        }
        int sizeX = map.get(0).length;
        int sizeY = map.size();

        /// counting detected asteroids for each position from map ///
        List<Position> positions = new ArrayList<>();
        for (int i = 0; i < sizeY; i++) {      // lines
            for (int j = 0; j < sizeX; j++) {  // element in a line

                if (map.get(i)[j] == anAsteroid) {
                    int asteroidsDetected = getNumberOfDetectedAsteroids(j, i, map);
                    positions.add(new Position(j, i, asteroidsDetected));
                }
            }
        }

        int max = 0;
        int posistionXforStation = -1;
        int posistionYforStation = -1;
        int positionsSize = positions.size();
        for(int i = 0; i < positionsSize; i++) {
            Position temp = positions.get(i);
            if(temp.detected > max){
                max = temp.detected;
                posistionXforStation = temp.coordX;
                posistionYforStation = temp.coordY;
            }
        }

        System.out.println("Answer = " + max);
        System.out.println("StationPosition X = " + posistionXforStation);
        System.out.println("StationPosition Y = " + posistionYforStation);
    }
}
