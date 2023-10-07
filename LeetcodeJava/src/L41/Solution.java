package L41;

public class Solution {
    public int firstMissingPositive(int[] nums) {
        // i 번째 인덱스에 i + 1 원소가 오도록 정렬
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1  // 현재 위치에 다른 원소가 있을 경우
                && 1 <= nums[i] && nums[i] <= nums.length  // 현재 값이 1 ~ nums.length 범위에 있다면
                && nums[nums[i] - 1] != nums[i])  // 바꾸려고 하는 위치에도 다른 원소가 있을 경우 (중복 원소 처리)
                swap(nums, i, nums[i] - 1);
        }

        // 나오지 않은 수가 있다면 바로 반환
        for (int i = 0; i < nums.length; i++)
            if (nums[i] != i + 1)
                return i + 1;
        // 1 ~ nums.length 까지 원소가 모두 존재하면 nums.length + 1 반환
        return nums.length + 1;
    }

    private static void swap(int[] nums, int a, int b) {
        nums[a] ^= nums[b];
        nums[b] ^= nums[a];
        nums[a] ^= nums[b];
    }
}
