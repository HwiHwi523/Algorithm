package L4;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> combined = new ArrayList<>();

        int nums1Idx = 0;
        int nums2Idx = 0;

        while (nums1Idx < nums1.length && nums2Idx < nums2.length) {
            if (nums1[nums1Idx] <= nums2[nums2Idx]) {
                combined.add(nums1[nums1Idx++]);
            } else {
                combined.add(nums2[nums2Idx++]);
            }
        }

        while (nums1Idx < nums1.length) {
            combined.add(nums1[nums1Idx++]);
        }

        while (nums2Idx < nums2.length) {
            combined.add(nums2[nums2Idx++]);
        }

        double result = combined.get(combined.size() >> 1);

        if ((combined.size() & 1) == 0) {
            result += combined.get((combined.size() >> 1) - 1);
            result /= 2;
        }

        return result;
    }
}
