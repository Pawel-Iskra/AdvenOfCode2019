import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day24_part1 {

    static int fillTopLeft(int[] tab, int size) {

        int bug = 1;
        int empty = 0;
        int corner = 0;

        if (tab[corner] == bug) {

            if (tab[corner + 1] == bug && tab[corner + size] == empty) return bug;
            if (tab[corner + 1] == empty && tab[corner + size] == bug) return bug;
            else return empty;

        } else if (tab[corner] == empty) {

            if (tab[corner + 1] == bug || tab[corner + size] == bug) return bug;
            else return empty;

        } else return empty;
    }

    static int fillTopRight(int[] tab, int size) {

        int bug = 1;
        int empty = 0;
        int corner = size - 1;

        if (tab[corner] == bug) {

            if (tab[corner - 1] == bug && tab[corner + size] == empty) return bug;
            if (tab[corner - 1] == empty && tab[corner + size] == bug) return bug;
            else return empty;

        } else if (tab[corner] == empty) {

            if (tab[corner - 1] == bug || tab[corner + size] == bug) return bug;
            else return empty;

        } else return empty;
    }

    static int fillBottomLeft(int[] tab, int size) {

        int bug = 1;
        int empty = 0;
        int corner = size * (size - 1);

        if (tab[corner] == bug) {

            if (tab[corner + 1] == bug && tab[corner - size] == empty) return bug;
            if (tab[corner + 1] == empty && tab[corner - size] == bug) return bug;
            else return empty;

        } else if (tab[corner] == empty) {

            if (tab[corner + 1] == bug || tab[corner - size] == bug) return bug;
            else return empty;

        } else return empty;
    }

    static int fillBottomRight(int[] tab, int size) {

        int bug = 1;
        int empty = 0;
        int corner = size * size - 1;

        if (tab[corner] == bug) {

            if (tab[corner - 1] == bug && tab[corner - size] == empty) return bug;
            if (tab[corner - 1] == empty && tab[corner - size] == bug) return bug;
            else return empty;

        } else if (tab[corner] == empty) {

            if (tab[corner - 1] == bug || tab[corner - size] == bug) return bug;
            else return empty;

        } else return empty;
    }

    static int fillTop(int i, int[] tab, int size) {

        int bug = 1;
        int empty = 0;

        if (tab[i] == bug) {
            if (tab[i - 1] == bug && tab[i + 1] == empty && tab[i + size] == empty) return bug;
            else if (tab[i - 1] == empty && tab[i + 1] == bug && tab[i + size] == empty) return bug;
            else if (tab[i - 1] == empty && tab[i + 1] == empty && tab[i + size] == bug) return bug;
            else return empty;
        }

        if (tab[i] == empty) {
            if (tab[i - 1] == empty && tab[i + 1] == empty && tab[i + size] == empty) return empty;
            if (tab[i - 1] == bug && tab[i + 1] == bug && tab[i + size] == bug) return empty;
            else return bug;
        }
        return empty;
    }

    static int fillBottom(int i, int[] tab, int size) {

        int bug = 1;
        int empty = 0;

        if (tab[i] == bug) {
            if (tab[i - 1] == bug && tab[i + 1] == empty && tab[i - size] == empty) return bug;
            else if (tab[i - 1] == empty && tab[i + 1] == bug && tab[i - size] == empty) return bug;
            else if (tab[i - 1] == empty && tab[i + 1] == empty && tab[i - size] == bug) return bug;
            else return empty;
        }

        if (tab[i] == empty) {
            if (tab[i - 1] == empty && tab[i + 1] == empty && tab[i - size] == empty) return empty;
            if (tab[i - 1] == bug && tab[i + 1] == bug && tab[i - size] == bug) return empty;
            else return bug;
        }
        return empty;
    }

    static int fillLeft(int i, int[] tab, int size) {

        int bug = 1;
        int empty = 0;

        if (tab[i] == bug) {
            if (tab[i - size] == bug && tab[i + size] == empty && tab[i + 1] == empty) return bug;
            else if (tab[i - size] == empty && tab[i + size] == bug && tab[i + 1] == empty) return bug;
            else if (tab[i - size] == empty && tab[i + size] == empty && tab[i + 1] == bug) return bug;
            else return empty;
        }

        if (tab[i] == empty) {
            if (tab[i - size] == empty && tab[i + size] == empty && tab[i + 1] == empty) return empty;
            if (tab[i - size] == bug && tab[i + size] == bug && tab[i + 1] == bug) return empty;
            else return bug;
        }
        return empty;
    }

    static int fillRight(int i, int[] tab, int size) {

        int bug = 1;
        int empty = 0;

        if (tab[i] == bug) {
            if (tab[i - size] == bug && tab[i + size] == empty && tab[i - 1] == empty) return bug;
            else if (tab[i - size] == empty && tab[i + size] == bug && tab[i - 1] == empty) return bug;
            else if (tab[i - size] == empty && tab[i + size] == empty && tab[i - 1] == bug) return bug;
            else return empty;
        }

        if (tab[i] == empty) {
            if (tab[i - size] == empty && tab[i + size] == empty && tab[i - 1] == empty) return empty;
            if (tab[i - size] == bug && tab[i + size] == bug && tab[i - 1] == bug) return empty;
            else return bug;
        }
        return empty;
    }

    static int fillTheRest(int i, int[] tab, int size) {

        int bug = 1;
        int empty = 0;

        if (tab[i] == bug) {
            if (tab[i - size] == bug && tab[i + size] == empty && tab[i - 1] == empty && tab[i + 1] == empty)
                return bug;
            if (tab[i - size] == empty && tab[i + size] == bug && tab[i - 1] == empty && tab[i + 1] == empty)
                return bug;
            if (tab[i - size] == empty && tab[i + size] == empty && tab[i - 1] == bug && tab[i + 1] == empty)
                return bug;
            if (tab[i - size] == empty && tab[i + size] == empty && tab[i - 1] == empty && tab[i + 1] == bug)
                return bug;
            else return empty;
        }

        if (tab[i] == empty) {
            if (tab[i - size] == empty && tab[i + size] == empty && tab[i - 1] == empty && tab[i + 1] == empty)
                return empty;
            else if (tab[i - size] == bug && tab[i + size] == bug && tab[i - 1] == bug && tab[i + 1] == bug)
                return empty;

            else if (tab[i - size] == empty && tab[i + size] == bug && tab[i - 1] == bug && tab[i + 1] == bug)
                return empty;
            else if (tab[i - size] == bug && tab[i + size] == empty && tab[i - 1] == bug && tab[i + 1] == bug)
                return empty;
            else if (tab[i - size] == bug && tab[i + size] == bug && tab[i - 1] == empty && tab[i + 1] == bug)
                return empty;
            else if (tab[i - size] == bug && tab[i + size] == bug && tab[i - 1] == bug && tab[i + 1] == empty)
                return empty;
            else return bug;
        }
        return bug;
    }

    static boolean chceckWithList(int[] tab, List<int[]> list, int size) {

        boolean flag = false;
        int listSize = list.size();
        int tabSize = size * size;

        for (int i = 0; i < listSize; i++) {

            int count = 0;

            for (int j = 0; j < tabSize; j++)
                if (tab[j] == list.get(i)[j]) count++;

            if (count == tabSize) return true;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = 5;
        List<int[]> layouts = new ArrayList<>();


        int[] lastLayout = new int[size * size];
        for (int i = 0; i < size; i++) {

            char[] input = br.readLine().toCharArray();

            for (int j = 0; j < size; j++) {
                if (input[j] == '#') lastLayout[i * size + j] = 1;
                if (input[j] == '.') lastLayout[i * size + j] = 0;
            }
        }
        layouts.add(lastLayout);


        int answer = 0;
        while (true) {

            int[] newLayout = new int[size * size];
            for (int i = 0; i < size * size; i++) {

                if (i == 0) newLayout[i] = fillTopLeft(lastLayout, size);
                else if (i == (size - 1)) newLayout[i] = fillTopRight(lastLayout, size);
                else if (i == (size * (size - 1))) newLayout[i] = fillBottomLeft(lastLayout, size);
                else if (i == (size * size - 1)) newLayout[i] = fillBottomRight(lastLayout, size);
                else if (i < size) newLayout[i] = fillTop(i, lastLayout, size);
                else if (i > size * (size - 1)) newLayout[i] = fillBottom(i, lastLayout, size);
                else if (i % size == 0) newLayout[i] = fillLeft(i, lastLayout, size);
                else if (i % size == (size - 1)) newLayout[i] = fillRight(i, lastLayout, size);
                else newLayout[i] = fillTheRest(i, lastLayout, size);
            } // end for


            if (chceckWithList(newLayout, layouts, size)) {

                for (int i = 0; i < (size * size); i++) {
                    if (newLayout[i] == 1)
                        answer = answer + (int) Math.pow(2, i);
                }
                break;
            }

            layouts.add(newLayout);

            /// REWRITING TABS ///
            lastLayout = new int[size * size];
            for (int i = 0; i < lastLayout.length; i++)
                lastLayout[i] = newLayout[i];

        } // end while


        System.out.println("Answer = " + answer);
    }
}
