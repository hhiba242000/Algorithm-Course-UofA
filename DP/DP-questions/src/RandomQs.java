import java.util.ArrayList;

public class RandomQs {
    public static void main(String[] args) {
        int[] nums={15, 12, 16, 13, 14, 10, 20, 3, 25, 7};
        System.out.println(LMIS(nums));

    }

    //given a sequence e.g [15, 12, 16, 13, 14, 10, 20, 3, 25, 7]
    // find the longest monotonically increasing subsequence ,here it's [12, 13, 14,  20, 25]
    private static ArrayList<Integer> LMIS(int[] nums){
       ArrayList<Integer> temp= new ArrayList<>();
       ArrayList<Integer> ans = new ArrayList<>();
       int count=0;
       for(int i=0;i< nums.length;i++){
           temp.add(count,nums[i]);
           count++;
           for(int j =i;j< nums.length;j++){
               if(nums[j] > temp.get(count-1)){
                   temp.add(count,nums[j]);
                   count++;
               }
           }
           System.out.println(temp);
           if(temp.size() > ans.size()){
               ans= (ArrayList<Integer>) temp.clone();
           }

           temp.clear();
           count=0;
       }
       return ans;
    }
}
