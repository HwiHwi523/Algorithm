package L15;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {

    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();

        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                counts.put(nums[i], counts.get(nums[i]) - 1);
                counts.put(nums[j], counts.get(nums[j]) - 1);

                int last = -(nums[i] + nums[j]);

                if (counts.getOrDefault(last, 0) > 0) {
                    List<Integer> triplet = new ArrayList<>(List.of(nums[i], nums[j], last));
                    triplet.sort(Comparator.comparingInt(o -> o));

                    result.add(triplet);
                }

                counts.put(nums[i], counts.get(nums[i]) + 1);
                counts.put(nums[j], counts.get(nums[j]) + 1);
            }
        }

        return result.stream().toList();
    }
}
