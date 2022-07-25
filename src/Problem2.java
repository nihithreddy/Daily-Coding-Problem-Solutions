/**
 * This problem was asked by Uber.
 *
 * Given an array of integers, return a new array such that each element at index i of the new array is the product of all the numbers in the original array except the one at i.
 *
 * For example, if our input was [1, 2, 3, 4, 5], the expected output would be [120, 60, 40, 30, 24]. If our input was [3, 2, 1], the expected output would be [2, 3, 6].
 *
 * Follow-up: what if you can't use division?
 */

import java.util.*;
import static java.lang.System.*;
public class Problem2 {
    public static void main(String[] args) {
        int[] input1 = {1,2,3,4,5};
        int input2[] = {3,2,1};
        int[] ans1 = firstApproach(input1);
        out.println(Arrays.toString(ans1));//[120,60,40,30,24]
        int[] ans2 = firstApproach(input2);//[2,3,6]
        out.println(Arrays.toString(ans2));
        int[] ans3 = secondApproach(input1);
        out.println(Arrays.toString(ans3));//[120,60,40,30,24]
        int[] ans4 = firstApproach(input2);//[2,3,6]
        out.println(Arrays.toString(ans4));

    }
    public static int[] firstApproach(int[] nums){
        //Naive Approach
        //Time Complexity : O(n)
        //Space Complexity : O(n)
        //This approach uses division but this approach will fail if zeros are present in the array Ex:[1,2,0,4,5] and [1,0,2,0,5]

        int totalProduct = 1;
        for(int num : nums){
            totalProduct *= num;
        }
        int[] productArray = new int[nums.length];
        for(int i=0;i< nums.length;i++){
            productArray[i] = totalProduct / nums[i];
        }
        return productArray;
    }

    public static int[] secondApproach(int[] nums){
        // Optimal Approach
        // Time Complexity : O(n)
        // Space Complexity : O(n)
        int n = nums.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        for(int i=0;i<n;i++){
            if(i == 0){
                prefix[0] = nums[0];
            }
            else{
                prefix[i] = nums[i] * prefix[i-1];
            }
        }
        for(int i=n-1;i>=0;i--){
            if(i == n-1){
                suffix[i] = nums[i];
            }
            else{
                suffix[i] = nums[i] * suffix[i+1];
            }
        }
        int[] productArray = new int[n];
        for(int i=0;i<n;i++){
            if(i == 0){
                productArray[i] = suffix[i+1];
            }
            else if(i == n-1){
                productArray[i] = prefix[i-1];
            }
            else{
                productArray[i] = prefix[i-1] * suffix[i+1];
            }
        }
        return productArray;
    }
}
