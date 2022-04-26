import java.util.Arrays;

public class Knapsack {
    //given list of items each has a weight and value choose combination to maximize capacity of their bag
    public static void demo(String[] args) {
        int[] v = {20, 5, 10, 40, 15, 25 };
        int[] w = {1, 2, 3, 8, 7, 4};
        int c = 10;

        System.out.println(KS01Naive(v, w,6, c));
        System.out.println(KS01TopDown(v, w, c));
        System.out.println(KS01BottomUp(v, w, c));
    }

    //unbounded item number
    static int KSNaive(int w, int[] weights, int[] values) {
        if (w == 0 || weights.length == 0) return 0;
        int q = Integer.MAX_VALUE;
        for (int i = 0; i < weights.length; i++) {
            if (weights[i] < w) {
                q = Math.max(q, values[i] + KSNaive(w - weights[i], weights, values));
            }
        }
        return q;
    }

    static int KSTopDown(int amt, int[] w, int[] v) {
        int[] r = new int[amt + 1];
        Arrays.fill(r, 0);
        return KSTopDownAux(amt, w, v, r);
    }

    private static int KSTopDownAux(int amt, int[] w, int[] v, int[] r) {
        if (r[amt] > 0) return r[amt];
        if (amt == 0) return 0;
        int q = Integer.MIN_VALUE;
        for (int i = 1; i < w.length; i++) {
            if (w[i] <= amt) {
                q = Math.min(q, v[i] + KSTopDownAux(amt - w[i], w, v, r));
            }
        }
        r[amt] = q;
        return r[amt];
    }

    private static int KSBottomUp(int amt, int[] w, int[] v) {
        int[] r = new int[amt + 1];
        r[0] = 0;
        for (int i = 1; i <= amt; i++) {
            int q = Integer.MIN_VALUE;
            for (int wt : w) {
                if (wt < i) {
                    q = Math.min(q, r[i - wt] + v[i]);
                }
            }
        }
        return r[amt];
    }

    private static int KS01BottomUp(int[] v, int[] w, int c) {
        int wlen = w.length;
        int[][] r = new int[wlen + 1][c + 1];

        for (int i = 0; i <= wlen; i++)
            r[i][c] = 0;
        for (int j = 0; j <= c; j++)
            r[wlen][j] = 0;

        for (int j = 1; j <= wlen; j++) {
            for (int i = 1; i <= c; i++) {
                if (w[j - 1] <= i)
                    r[j][i] = Math.max(v[j - 1] + r[j - 1][i - w[j - 1]], r[j - 1][i]);
                else
                    r[j][i] = r[j - 1][i];
            }
        }

        return r[wlen][c];
    }

    //KS top down 0-1
    private static int KS01TopDown(int[] v, int[] w, int c) {
        Integer[][] r = new Integer[c + 1][v.length + 1];
        return KS01TopDownAux(v, w, c, v.length, r);
    }

    private static int KS01TopDownAux(int[] v, int[] w, int c, int n, Integer[][] r) {
        if (r[c][n] != null)
            return r[c][n];
        if (c <= 0 || n <= 0)
            return 0;

        int include =0;
        if (w[n - 1] <= c)
            include = v[n - 1] + KS01TopDownAux(v, w, c - w[n - 1], n - 1, r);

        int exclude = KS01TopDownAux(v, w, c, n - 1,r);

        r[c][n] = Math.max(include,exclude);
        return r[c][n];
    }

    private static int KS01Naive(int[]v,int[]w,int n,int c){
        if (c == 0 || n <= 0)
            return 0;
        if(c<0)
            return Integer.MIN_VALUE;
        int include = v[n - 1] + KS01Naive(v, w, n - 1, c - w[n - 1]);

        int exclude = KS01Naive(v, w,n - 1, c );

        return Math.max(include,exclude);
    }
}