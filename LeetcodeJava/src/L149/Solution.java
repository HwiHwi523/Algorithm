package L149;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public int maxPoints(int[][] points) {
        if (points.length == 1) {
            return 1;
        }

        Set<Integer>[] lines = new HashSet[points.length];
        for (int i = 0; i < points.length; i++) {
            lines[i] = new HashSet<>();
        }

        int lineId = 0;

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (isOnLine(lines[i], lines[j])) {
                    continue;
                }

                double grad = getGrad(points[i], points[j]);

                lines[i].add(lineId);
                lines[j].add(lineId);

                for (int k = j + 1; k < points.length; k++) {
                    double newGrad = getGrad(points[i], points[k]);

                    if (grad != newGrad && !(points[i][0] == points[j][0] && points[j][0] == points[k][0])) {
                        continue;
                    }

                    lines[k].add(lineId);
                }

                lineId++;
            }
        }

        int[] counts = new int[lineId];
        for (Set<Integer> line : lines) {
            for (int lId : line) {
                counts[lId]++;
            }
        }

        int answer = counts[0];
        for (int count : counts) {
            answer = Math.max(answer, count);
        }

        return answer;
    }

    private boolean isOnLine(Set<Integer> l1, Set<Integer> l2) {
        Set<Integer> intersection = new HashSet<>(l1);

        intersection.retainAll(l2);

        return !intersection.isEmpty();
    }

    private double getGrad(int[] p1, int[] p2) {
        return (double) (p1[1] - p2[1]) / (p1[0] - p2[0]);
    }
}
