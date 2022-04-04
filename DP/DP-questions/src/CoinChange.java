import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CoinChange {
    //given amount of coins find minimum number of coins to sum up V
    public static void main(String[] args) {
        

        int n=6249;
        int[] coins={186,419,83,408};
        int c=MinCoins(n,coins);
        System.out.println(c);

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
//        printOptSol( n,s);
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
//        while(n>0){
//            System.out.println("coin of value= "+s[n]);
//            n=n-s[n];
//        }
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

    //solution might not exist so return -1 in that case
    static int MinCoins(int n,int[] coins){
        Arrays.sort(coins);
        int[] r=new int[n+1];
        r[0]=0;
        for(int i=1;i<=n;i++){
            if(i<coins[0])
                r[i]=-1;
            else{
                int q=Integer.MAX_VALUE;
                boolean found=false;
                for(int x:coins){
                    if(x<=i){
                        int t=r[i-x];
                        if(t==-1 ){
                            if(!found){
                            q=-1;
                        }
                        }
                        else{
                           q=Math.min(q,1+t);
                           found=true;
                        }
                    }
                }
                r[i]=q;
            }
        }
        System.out.println(Arrays.toString(r));
        return r[n];
    }

}
