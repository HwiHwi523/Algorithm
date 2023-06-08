import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class L_239_Sliding_Window_Maximum {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // 1
        System.out.println(Arrays.toString(solution.maxSlidingWindow(new int[] {1,3,-1,-3,5,3,6,7}, 3)));

        // 2
        System.out.println(Arrays.toString(solution.maxSlidingWindow(new int[] {1}, 1)));
    }

    static class Solution {
        // segment
        public int[] maxSlidingWindow(int[] nums, int k) {
            // 결과
            int[] result = new int[nums.length - k + 1];

            // 세그먼트 트리 생성
            int[] segment = new int[nums.length << 1];

            // 원소 삽입 및 트리 초기화
            for (int i = nums.length; i < segment.length; i++)
                segment[i] = nums[i - nums.length];
            for (int i = nums.length - 1; i >= 1; i--)
                segment[i] = Math.max(segment[i << 1], segment[(i << 1) + 1]);

            // 정답 구하기
            for (int i = k; i <= nums.length; i++)
                result[i - k] = getFromSegment(segment, i - k, i, nums.length);

            return result;
        }
        // get from segment
        private int getFromSegment(int[] segment, int begin, int end, int n) {
            int result = segment[begin + n];
            begin += n;
            end += n;

            while (begin < end) {
                if ((begin & 1) == 1)
                    result = Math.max(result, segment[begin++]);
                if ((end & 1) == 1)
                    result = Math.max(result, segment[--end]);
                begin >>= 1;
                end >>= 1;
            }

            return result;
        }

        // heap + hash map
//        public int[] maxSlidingWindow(int[] nums, int k) {
//            // 결과
//            int[] result = new int[nums.length - k + 1];
//
//            // 최대 힙
//            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
//            // 힙에 들어있는 각 원소의 개수
//            Map<Integer, Integer> quantity = new HashMap<>();
//
//            // 첫 k 개의 원소 처리
//            for (int i = 0; i < nums.length; i++) {
//                // 힙에 원소 추가
//                if (i >= k)
//                    result[i - k] = maxHeap.peek();
//
//                // 처음 등장하는 수일 경우 quantity 초기값 삽입
//                if (!quantity.containsKey(nums[i]))
//                    quantity.put(nums[i], 0);
//                // 힙에 없다면 삽입
//                int value = quantity.get(nums[i]);
//                if (value == 0)
//                    maxHeap.add(nums[i]);
//                // 수량 증가
//                quantity.put(nums[i], quantity.get(nums[i]) + 1);
//
//                // i가 k 보다 클 경우, 가장 앞에 있는 원소 하나씩 삭제
//                if (i >= k) {
//                    // quantity 설정
//                    quantity.put(nums[i - k], quantity.get(nums[i - k]) - 1);
//
//                    // 힙에서 더미 데이터 삭제
//                    while (quantity.get(maxHeap.peek()) == 0)
//                        maxHeap.poll();
//                }
//            }
//            // 마지막 삽입 & 삭제에 대한 처리
//            result[result.length - 1] = maxHeap.peek();
//
//            return result;
//        }
    }
}
