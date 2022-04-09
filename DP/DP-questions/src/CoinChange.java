import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CoinChange {
    //given amount of coins find minimum number of coins to sum up V
    public static void main(String[] args) {

        int n=11;
        int[] coins={1,2,5};

        int a=MinCoinsBottomUp(n,coins);
        System.out.println("Bottom up= "+a);

        int[] r= new int[n+1];
       int[] s= new int[n+1];
        for(int i=0;i<n+1;i++){
            r[i]=0;
            s[i]=0;
        }
        int b=MinCoinsTopDown(n,coins,r,s);
      System.out.println("top down= "+b);
      //if you want to print solution
       printOptSol( n,s);

        System.out.println("-----demo for no solution case-----");
        int d=MinCoinsTD(n,coins);
        System.out.println(d);

        int e=MinCoinsBU(n,coins);
        System.out.println(e);
    }



    //assuming the solution always exists and unlimited number of each coin
    static private int MinCoinsBottomUp(int n,int[] coins){
        int[] r=new int[n+1];
        int[] s=new int[n+1];
        r[0]=0;
        s[0]=0;
        for(int i=1;i<=n;i++){
            int q=Integer.MAX_VALUE;
            int t=0;
            for(int x:coins){
                if(x<=i){
                    if(q>1+r[i-x]){
                        q=1+r[i-x];
                        t=x;
                    }
                }
            }
            r[i]=q;
            s[i]=t;
        }
        int old=n;
        //print the optimized answer
        while(n>0){
            System.out.println("coin of value= "+s[n]);
            n=n-s[n];
        }
        return r[old];
    }

    static private int MinCoinsTopDown(int n,int[] coins,int[]r,int[]s){
        if(r[n] > 0)
            return r[n];
        if(n==0)
            return 0;
        int q=Integer.MAX_VALUE;
        int ss=0;
        for(int x: coins){
            if(x<=n){
                int t=1+MinCoinsTopDown(n-x,coins,r,s);
                if(q > t){
                    q=t;
                    ss=x;
                }
            }
        }
        r[n]=q;
        s[n]=ss;

        return r[n];
    }

    private static void printOptSol(int n, int[] s) {
        while(n>0){
            System.out.println("coin of value= "+s[n]);
            n=n-s[n];
        }
    }

    //solution might not exist so return -1 in that case unlimited number of coins
    static int MinCoinsTD(int n,int[] coins){
        if(n==0 )
            return 0;
        if(coins.length ==0 )
            return -1;

        long[] r= new long[n+1];
        Arrays.fill(r,-1);
        long ans=MinCoinsTDAux(n,coins,r);
        if(ans >=(Integer.MAX_VALUE))
            return -1;
        else return (int) ans;
    }

    private static long MinCoinsTDAux(int n, int[] coins, long[] r) {
        if(r[n] != -1)
            return r[n];
        if(n==0)
            return 0;
        if(n<0)
            return Integer.MAX_VALUE;
        long q=Integer.MAX_VALUE;
        for(int i :coins){
            System.out.println("in loop");
            if(i<=n){
                System.out.println("found a coin within limit");
                q=Math.min(q,1+MinCoinsTDAux(n-i,coins,r));
            }
        }
        r[n]=q;
        return r[n];
    }

    private static int MinCoinsBU(int n, int[] coins) {
        int[] r= new int[n+1];
        Arrays.fill(r,n+1);
        r[0]=0;
        for(int x:coins){
            for(int i=x;i<=n;i++){
                r[i]=Math.min(r[i],1+r[i-x]);
            }
        }
        return r[n] > n?-1:r[n];
    }

}
