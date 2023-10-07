import java.util.LinkedList;
import java.util.List;

public class CompareList {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 100_000; i++)
            list.add(i);
        System.out.println("START");
        long begin = System.currentTimeMillis();

        long a = 0;
        for (int i = 0; i < 100_000; i++)
            a += list.get(i);
//        for (int temp : list)
//            a += list.get(temp);

        long times = System.currentTimeMillis() - begin;
        System.out.println(times);
    }
}
