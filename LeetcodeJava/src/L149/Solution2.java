package L149;

public class Solution2 {

    public int maxPoints(int[][] points) {
        if (points.length == 1) {
            return 1;
        }

        int answer = 0;

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double grad = getGrad(points[i], points[j]);
                int count = 2;

                for (int k = j + 1; k < points.length; k++) {
                    double newGrad = getGrad(points[i], points[k]);

                    if (grad == newGrad
                        || (points[i][0] == points[j][0] && points[j][0] == points[k][0])) {
                        count++;
                    }
                }

                answer = Math.max(answer, count);
            }
        }

        return answer;
    }

    private double getGrad(int[] p1, int[] p2) {
        return (double) (p1[1] - p2[1]) / (p1[0] - p2[0]);
    }
}
