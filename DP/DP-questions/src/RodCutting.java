import java.util.ArrayList;
import java.util.Arrays;

public class RodCutting {
    //using DP solve rod cutting problem
    //modifications may be inserted like price list->price map to construct n from random different smaller lengths
    //not necessarily the interval 1->n
    public static void main(String[] args) {
        int[] prices={0,1,5,8,9,10,17,17,20,24,30};
        int n= 7;
        int[] memo=new int[n+1];
        for(int i=0;i<n+1;i++)
            memo[i]=Integer.MIN_VALUE;

        int b=RodCutTopDown(n,prices,memo);
        System.out.println("top down= "+b);
        int c= RodCutBottomUp(n,prices);
        System.out.println("bottom up= "+c);
        int a=RodCutNaive(n,prices);
        System.out.println("naive= "+a);

        PrintTheOpAnswer(n,prices);


    }


    static int RodCutNaive(int n,int[] price){
        if(n==0)
            return 0;
        int max=Integer.MIN_VALUE;
        for(int i=1;i<=n;i++){
            max=Math.max(max,price[i]+RodCutNaive(n-i,price));
        }
        return max;
    }

    static int RodCutTopDown(int n,int[]prices,int[]memo){
        if(n==0) {
            memo[0]=0;
            return 0;
        }
        if(memo[n] > Integer.MIN_VALUE)
            return memo[n];

        int max=Integer.MIN_VALUE;
        for(int i=1;i<=n;i++){
            max=Math.max(max,prices[i]+RodCutTopDown(n-i,prices,memo));
        }
        memo[n]=max;
        return memo[n];
    }

    static int RodCutBottomUp(int n,int[]prices){
        int[]memo = new int[n+1];
        memo[0]=0;
        for(int i=1;i<=n;i++){
            int max=Integer.MIN_VALUE;
            for(int j=1;j<=i;j++){
                max=Math.max(max,prices[j]+memo[i-j]);
            }
            memo[i]=max;
        }
        return memo[n];
    }

    private static void PrintTheOpAnswer(int n,int[] prices) {
        int[]s=new int[n+1];
        RodCutBottomUpMod(n,prices,s);
        System.out.println("answer by RodCutBottomUpMod ");
        while(n>0){
            System.out.println("cut at: "+s[n]);
            n=n-s[n];
        }

        ArrayList<Integer> ss=new ArrayList();
        int[] memo=new int[n+1];
        for(int i=0;i<n+1;i++)
            memo[i]=Integer.MIN_VALUE;

        RodCutTopDownMod(n,prices,memo,ss);
        System.out.println("answer by RodCutTopDownMod ");
        for(int x:ss){
            System.out.println("cut at: "+x);

        }

    }
    static int RodCutBottomUpMod(int n,int[]prices,int[]s){
        int[]memo = new int[n+1];
        memo[0]=0;
        for(int i=1;i<=n;i++){
            int q=Integer.MIN_VALUE;
            for(int j=1;j<=i;j++){
                if(q<prices[j]+memo[i-j]) {
                    q=prices[j]+memo[i-j];
                    s[i]=j;
                }
            }
            memo[i] = q;
        }
        return memo[n];
    }

    static int RodCutTopDownMod(int n, int[]prices, int[]memo, ArrayList<Integer> s){
        if(n==0) {
            memo[0]=0;
            return 0;
        }
        if(memo[n] > Integer.MIN_VALUE) {
            s.add(n);
            System.out.print("im here"+s);
            return memo[n];
        }

        int max=Integer.MIN_VALUE;
        for(int i=1;i<=n;i++){
            max=Math.max(max,prices[i]+RodCutTopDown(n-i,prices,memo));
        }
        memo[n]=max;
        return memo[n];
    }

}
