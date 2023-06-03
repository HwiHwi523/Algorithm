public class CompareSwap {
    public static void main(String[] args) {
        simulation(1_000_000, 10);
    }

    static void simulation(final int DATA_LENGTH, int times) {
        long avg = 0L;

        for (int i = 1; i <= times; i++) {
            int[] nums = new int[DATA_LENGTH];
            long execTime = swapNTimes(nums, 100_000_000);
            avg += execTime;
            System.out.println("Swap #" + i + " : " + execTime + " ms");
        }
        System.out.println("Avg : " + (double) avg / times + " ms");
    }

    static long swapNTimes(int[] nums, int n) {
        long begin = System.currentTimeMillis();

        while (n-- > 0) {
            int a = (int) (Math.random() * nums.length);
            int b = (int) (Math.random() * nums.length);
//            tempSwap(nums, a, b);
            xorSwap(nums, a, b);
        }

        return System.currentTimeMillis() - begin;
    }

    static void tempSwap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
//        Swap #1 : 5549 ms
//        Swap #2 : 5442 ms
//        Swap #3 : 5467 ms
//        Swap #4 : 5830 ms
//        Swap #5 : 6787 ms
//        Swap #6 : 6548 ms
//        Swap #7 : 5888 ms
//        Swap #8 : 5561 ms
//        Swap #9 : 5289 ms
//        Swap #10 : 5266 ms
//        Avg : 5762.7 ms
    }

    static void xorSwap(int[] nums, int a, int b) {
        nums[a] ^= nums[b];
        nums[b] ^= nums[a];
        nums[a] ^= nums[b];
//        Swap #1 : 5574 ms
//        Swap #2 : 5436 ms
//        Swap #3 : 5525 ms
//        Swap #4 : 5423 ms
//        Swap #5 : 5466 ms
//        Swap #6 : 5359 ms
//        Swap #7 : 5361 ms
//        Swap #8 : 5519 ms
//        Swap #9 : 5458 ms
//        Swap #10 : 6134 ms
//        Avg : 5525.5 ms
    }
}