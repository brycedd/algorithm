/**
 * @author Bryce
 * @date 2021/4/8
 *
 * 将一个有序数组进行旋转，旋转后找出其最小值
 * 其中数组元素的值在[-5000,5000]中
 *
 */
public class FindMin {

    public static void main(String[] args) {
        int[] nums = {1,3,5,7,9};
        System.out.println(findMin(nums));
        System.out.println(findMinPublic(nums));

    }

    public static int findMin(int[] nums) {

        if(null == nums || nums.length == 0)
            return 0;

        if(nums.length == 1)
            return nums[0];

        int minNum = -5001;

        int lastNum = -5001;
        for (int num : nums) {
            if(lastNum > num) {
                minNum = num;
            }
            lastNum = num;
        }

        if(-5001 == minNum)
            minNum = nums[0];

        return minNum;
    }
    public static int findMinPublic(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (nums[pivot] < nums[high]) {
                high = pivot;
            } else {
                low = pivot + 1;
            }
        }
        return nums[low];
    }


}
