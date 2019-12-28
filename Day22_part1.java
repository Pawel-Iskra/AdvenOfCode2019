import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day22_part1 {

    static void dealWithCut(int n, List<Integer> cards) {
        List<Integer> temp = new ArrayList<>();

        if (n < 0) {

            n = n * (-1);
            int size = cards.size();

            temp.addAll(cards.subList(size - n, size));
            cards.removeAll(temp);
            temp.addAll(cards);
            cards.removeAll(cards);
            cards.addAll(temp);

        } else {

            temp.addAll(cards.subList(0, n));
            cards.removeAll(temp);
            cards.addAll(temp);
        }
    }

    static void dealIntoNewStack(List<Integer> cards) {
        Collections.reverse(cards);
    }

    static void dealWithIncrement(int n, List<Integer> cards) {
        List<Integer> temp = new ArrayList<>();
        int size = cards.size();

        for (int i = 0; i < size; i++) temp.add(0);

        temp.set(0, cards.get(0));

        int j = 0;
        for (int i = 1; i < size; i++) {

            j = j + n;
            if (j > size) j = j - size;
            if (j < 0) j = j + size;

            temp.set(j, cards.get(i));
        }
        cards.removeAll(cards);
        cards.addAll(temp);
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // My Factory Order //
        int size = 10007;
        int cardToFind = 2019;


        List<Integer> cards = new ArrayList<>();
        for (int i = 0; i < size; i++) cards.add(i);

        String line;
        while ((line = br.readLine()) != null) {

            String[] input = line.split(" ");

            if (input[0].equals("cut")) dealWithCut(Integer.parseInt(input[1]), cards);
            else if (input[1].equals("into")) dealIntoNewStack(cards);
            else if (input[1].equals("with")) dealWithIncrement(Integer.parseInt(input[3]), cards);

        }

        int answer = -1;
        for (int i = 0; i < size; i++) {
            if (cards.get(i) == cardToFind) {
                answer = i;
                break;
            }
        }

        System.out.println("Answer = " + answer);
    }
}
