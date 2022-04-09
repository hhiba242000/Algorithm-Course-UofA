import java.util.Arrays;

public class Knapsack {
    //given list of items each has a weight and value choose combination to maximize capacity of their bag
    public static void demo(String[] args) {

    }

    //unbounded item number
    static int KSNaive(int w,int[]weights,int[]values){
        if(w==0 || weights.length == 0) return 0;
        int q=Integer.MAX_VALUE;
        for(int i=0;i<weights.length;i++ ){
            if(weights[i] < w){
                q=Math.max(q,values[i]+KSNaive(w-weights[i],weights,values));
            }
        }
        return q;
    }

    static int KSTopDown(int amt,int[]w,int[]v){
        int[] r=new int[amt+1];
        Arrays.fill(r,0);
        return KSTopDownAux(amt,w,v,r);
    }

    private static int KSTopDownAux(int amt,int[]w,int[]v,int[]r){
        if(r[amt] >0) return r[amt];
        if(amt == 0) return 0;
        int q=Integer.MIN_VALUE;
        for(int i=1;i<w.length;i++){
            if(w[i] <= amt){
                q=Math.min(q,v[i]+KSTopDownAux(amt-w[i],w,v,r));
            }
        }
        r[amt] = q;
        return r[amt];
    }

    private static int KSBottomUp(int amt,int[]w,int[]v){
        int []r=new int[amt+1];
        r[0]=0;
        for(int i=1;i<=amt;i++){
            int q=Integer.MIN_VALUE;
            for(int wt:w){
                if(wt<i){
                    q=Math.min(q,r[i-wt]+v[i]);
                }
            }
        }
        return r[amt];
    }
}
