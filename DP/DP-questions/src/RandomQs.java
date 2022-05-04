import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RandomQs {
    public static void main(String[] args) {
        int[] nums={15, 12, 16, 13, 14, 10, 20, 3, 25, 7};
        int n = nums.length;
        System.out.println(LMISNaive(nums,0,n,Integer.MIN_VALUE));

    }

    //given a sequence e.g [15, 12, 16, 13, 14, 10, 20, 3, 25, 7]
    // find the longest monotonically increasing subsequence ,here it's [12, 13, 14,  20, 25]

    private static int LMISNaive(int[] nums,int i,int n,int prev){
        //credit to -> https://www.techiedelight.com/longest-increasing-subsequence-using-dynamic-programming/
        if(i == n)//we reached end of array
            return 0;

        int exclude = LMISNaive(nums,i+1,n,prev);
        int include = 0;
        if(nums[i] > prev)
            include = 1+ LMISNaive(nums,i+1,n,prev);
       return Math.max(include,exclude);
    }

    private static int LMISBottomUp(int [] nums){
        if(nums.length == 0)
            return 0;

        int []memo = new int[nums.length];
        memo[0]=1;
        for(int i = 1;i<nums.length;i++){
            for(int j =0;j<i;j++){
                if(memo[j] > memo[i] && nums[i] > nums[j])
                    memo[i]=memo[j];
            }
            memo[i]++;
        }

        return Arrays.stream(memo).max().getAsInt();
    }
}
