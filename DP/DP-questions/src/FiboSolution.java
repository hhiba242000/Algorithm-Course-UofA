public class FiboSolution {
    //using DP solve fibonacci numbers problem
    public static void demo(int n) {
        int[]memo=new int[n+1];
        int b= FiboTopDown(n,memo);
        System.out.println("b= "+b);
        int c= FiboBottomUp(n);
        System.out.println("c= "+c);
        int d= OpFiboBottomUp(n);
        System.out.println("d= "+d);
        int a=FiboNaive(n);
        System.out.println("a= "+a);

    }
    private static int FiboNaive(int n){
        if(n==0||n==1)
            return n;
        return FiboNaive(n-2)+FiboNaive(n-1);
    }

    private static int FiboTopDown(int n, int[] memo){
        if(n==0 || n==1)
            return n;
        if(memo[n] == 0)
            memo[n]=FiboTopDown(n-1,memo)+FiboTopDown(n-2,memo);

        return memo[n];
    }

    private static int FiboBottomUp(int n){
        int[]memo=new int[n+1];
        memo[0]=0;memo[1]=1;
        for(int i=2;i<=n;i++){
            memo[i]=memo[i-1] + memo[i-2];
        }
        return memo[n];
    }

    private static int OpFiboBottomUp(int n){
        int[]memo=new int[n+1];
        int memo0=0,memo1=1;
        for(int i=2;i<=n;i++){
            int temp=memo1;
            memo1=memo0 + memo1;
            memo0=temp;
        }
        return memo1;
    }
}
