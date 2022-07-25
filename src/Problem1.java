/**
 * This problem was recently asked by Google.
 *
 * Given a list of numbers and a number k, return whether any two numbers from the list add up to k.
 *
 * For example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is 17.
 *
 * Bonus: Can you do this in one pass?
 */

import java.util.*;
import static java.lang.System.*;
public class Problem1 {
    public static void main(String[] args) {
        out.println(firstApproach(new int[]{1,5,7,3},8));
        out.println(secondApproach(new int[]{1,7,2,3},11));
        out.println(thirdApproach(new int[]{1,2,7,3},8));
        out.println(thirdApproach(new int[]{1,3,2,3},7));
    }
    public static boolean firstApproach(int[] nums,int k){
        // Brute force Approach or Naive Approach
        //Time Complexity : O(n*n)
        //Space Complexity : O(1)
        for(int i=0;i<nums.length;i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[i] + nums[j] == k) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean secondApproach(int[] nums,int k){
        //Approach Using a Set
        //Time Complexity : O(n)
        //Space Complexity : O(n)
        Set<Integer> seen = new HashSet<>();
        for(int num:nums){
            if(seen.contains(k-num)){
                return true;
            }
            //If not found in the set then add the element into the set
            seen.add(num);
        }
        return false;
    }
    public static boolean thirdApproach(int[] nums,int k){
        //Approach Using BinarySearch
        //Time Complexity : O(n*log(n))
        //Space Complexity : O(1)
        Arrays.sort(nums);// Array has to be sorted prior to binary search
        for(int i=0;i<nums.length;i++){
            int start = 0,end = nums.length-1;
            int target = k - nums[i];
            int targetIndex = -1;
            while(start <= end){
                int mid  = (start + end)/2;
                if(nums[mid] == target){
                    targetIndex = mid;
                    break;
                }
                else if(target < nums[mid]){
                    end = mid-1;
                }
                else{
                    start = mid+1;
                }
            }
            //If the target element is not found
            if(targetIndex == -1){
                continue;
            }
            //If the target element is found
            else if(targetIndex != i){
                return true;
            }
            //If the target element index is same as the current index then check for adjacent elements
            else if(targetIndex+1 < nums.length && nums[targetIndex+1] == nums[i]){
                return true;
            }
            //If the target element index is same as the current index then check for adjacent elements
            else if(targetIndex-1 >=0 && nums[targetIndex-1] == nums[i]){
                return true;
            }
        }
        return false;
    }


}
