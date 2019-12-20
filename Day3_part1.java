import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day3_part1 {

    private static class VerticLine {
        int x;
        int ymin;
        int ymax;

        public VerticLine(int x, int ymin, int ymax) {
            this.x = x;
            this.ymin = ymin;
            this.ymax = ymax;
        }
    }

    private static class HorizLine {
        int y;
        int xmin;
        int xmax;

        public HorizLine(int y, int xmin, int xmax) {
            this.y = y;
            this.xmin = xmin;
            this.xmax = xmax;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        List<Character> dirlw1 = new ArrayList<>();       // direction list wire 1
        List<Integer> vallw1 = new ArrayList<>();         // value list wire 1
        List<VerticLine> verLines = new ArrayList<>();   // list of vertical lines made by first wire
        List<HorizLine> horlines = new ArrayList<>();    // list of horizontal lines made by first wire

        String[] input1 = br.readLine().split(",");
        int size1 = input1.length;
        for (int i = 0; i < size1; i++) {
            dirlw1.add(input1[i].charAt(0));
            vallw1.add(Integer.parseInt(input1[i].substring(1)));
        }


        List<Character> dirlw2 = new ArrayList<>();       // direction list wire 2
        List<Integer> vallw2 = new ArrayList<>();         // value list wire 2

        String[] input2 = br.readLine().split(",");
        int size2 = input2.length;
        for (int i = 0; i < size2; i++) {
            dirlw2.add(input2[i].charAt(0));
            vallw2.add(Integer.parseInt(input2[i].substring(1)));
        }


        ///////////////////////////////////////////////////////
        ///// MAKING THE "BROKEN LINE" MADE BY FIRST WIRE /////
        int lasty = 0, lastx = 0;
        int max, min;
        int size3 = dirlw1.size();

        for (int i = 0; i < size3; i++) {

            switch (dirlw1.get(i)) {

                case 'D':
                case 'U':
                    max = lasty;
                    min = lasty;

                    if (dirlw1.get(i) == 'D') lasty = lasty - vallw1.get(i);
                    else lasty = lasty + vallw1.get(i);

                    if (lasty > max) max = lasty;
                    else min = lasty;

                    VerticLine verticalLine = new VerticLine(lastx, min, max);
                    verLines.add(verticalLine);
                    break;

                case 'L':
                case 'R':
                    max = lastx;
                    min = lastx;

                    if (dirlw1.get(i) == 'L') lastx = lastx - vallw1.get(i);
                    else lastx = lastx + vallw1.get(i);

                    if (lastx > max) max = lastx;
                    else min = lastx;

                    HorizLine horizontalline = new HorizLine(lasty, min, max);
                    horlines.add(horizontalline);
                    break;
            }
        }


        ///// CHECKING - in every step of second wire - IF SECOND WIRE CROSSED THE FIRST ONE /////
        int odlMin = 1000000;
        int lastx2 = 0, lasty2 = 0;
        int size4 = dirlw2.size();
        for (int i = 0; i < size4; i++) {

            char c = dirlw2.get(i);
            int w = vallw2.get(i);
            int temp = 0;
            int tempx2 = 0, tempy2 = 0;

            if (c == 'R') {

                while (temp < w) {
                    lastx2++;
                    temp++;

                    for (int j = 0; j < verLines.size(); j++)
                        if (lastx2 == verLines.get(j).x)
                            if (lasty2 <= verLines.get(j).ymax && lasty2 >= verLines.get(j).ymin) {
                                tempx2 = Math.abs(lastx2);
                                tempy2 = Math.abs(lasty2);
                                if (((tempx2 + tempy2) > 0) && ((tempx2 + tempy2) < odlMin)) odlMin = tempx2 + tempy2;
                            }

                    for (int k = 0; k < horlines.size(); k++)
                        if (lastx2 >= horlines.get(k).xmin && lastx2 <= horlines.get(k).xmax)
                            if (lasty2 == horlines.get(k).y) {
                                tempx2 = Math.abs(lastx2);
                                tempy2 = Math.abs(lasty2);
                                if (((tempx2 + tempy2) > 0) && ((tempx2 + tempy2) < odlMin)) odlMin = tempx2 + tempy2;
                            }
                }
            } ///// END if c==R


            if (c == 'L') {
                while (temp > (w * (-1))) {
                    lastx2--;
                    temp--;

                    for (int j = 0; j < verLines.size(); j++)
                        if (lastx2 == verLines.get(j).x)
                            if (lasty2 <= verLines.get(j).ymax && lasty2 >= verLines.get(j).ymin) {
                                tempx2 = Math.abs(lastx2);
                                tempy2 = Math.abs(lasty2);
                                if (((tempx2 + tempy2) > 0) && ((tempx2 + tempy2) < odlMin)) odlMin = tempx2 + tempy2;
                            }

                    for (int k = 0; k < horlines.size(); k++)
                        if (lastx2 >= horlines.get(k).xmin && lastx2 <= horlines.get(k).xmax)
                            if (lasty2 == horlines.get(k).y) {
                                tempx2 = Math.abs(lastx2);
                                tempy2 = Math.abs(lasty2);
                                if (((tempx2 + tempy2) > 0) && ((tempx2 + tempy2) < odlMin)) odlMin = tempx2 + tempy2;
                            }
                }
            } ///// END if c==L


            if (c == 'U') {
                while (temp < w) {
                    lasty2++;
                    temp++;

                    for (int j = 0; j < horlines.size(); j++)
                        if (lasty2 == horlines.get(j).y)
                            if (lastx2 <= horlines.get(j).xmax && lastx2 >= horlines.get(j).xmin) {
                                tempx2 = Math.abs(lastx2);
                                tempy2 = Math.abs(lasty2);
                                if (((tempx2 + tempy2) > 0) && ((tempx2 + tempy2) < odlMin)) odlMin = tempx2 + tempy2;
                            }

                    for (int k = 0; k < verLines.size(); k++)
                        if (lasty2 >= verLines.get(k).ymin && lasty2 <= verLines.get(k).ymax)
                            if (lastx2 == verLines.get(k).x) {
                                tempx2 = Math.abs(lastx2);
                                tempy2 = Math.abs(lasty2);
                                if (((tempx2 + tempy2) > 0) && ((tempx2 + tempy2) < odlMin)) odlMin = tempx2 + tempy2;
                            }
                }
            } ///// END if c==U


            if (c == 'D') {

                while (temp > (w * (-1))) {
                    lasty2--;
                    temp--;

                    for (int j = 0; j < horlines.size(); j++)
                        if (lasty2 == horlines.get(j).y)
                            if (lastx2 <= horlines.get(j).xmax && lastx2 >= horlines.get(j).xmin) {
                                tempx2 = Math.abs(lastx2);
                                tempy2 = Math.abs(lasty2);
                                if (((tempx2 + tempy2) > 0) && ((tempx2 + tempy2) < odlMin)) odlMin = tempx2 + tempy2;
                            }

                    for (int k = 0; k < verLines.size(); k++)
                        if (lasty2 >= verLines.get(k).ymin && lasty2 <= verLines.get(k).ymax)
                            if (lastx2 == verLines.get(k).x) {
                                tempx2 = Math.abs(lastx2);
                                tempy2 = Math.abs(lasty2);
                                if (((tempx2 + tempy2) > 0) && ((tempx2 + tempy2) < odlMin)) odlMin = tempx2 + tempy2;
                            }
                }
            } ///// END if c==D

        }


        System.out.println("Answer = " + odlMin);

    }
}
