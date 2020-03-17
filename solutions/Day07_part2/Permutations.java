package Day07_part2;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

    private List<int[]> outList = new ArrayList<>();
    public List<int[]> getOutList() {
        return outList;
    }

    void permute(int[] tab, int start) {
        if (tab.length == start) {
            outList.add(tab);
        } else {
            for (int i = start; i < tab.length; i++) {
                int[] input = tab.clone();
                int temp = input[i];
                input[i] = input[start];
                input[start] = temp;
                permute(input, start + 1);
            }
        }
    }


}
