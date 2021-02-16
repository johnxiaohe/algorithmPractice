package main.java.leetcode;

public class LK287 {
    /**
     * 题目是n+1大小的数组中有1-n整数。所以一定有重复
     * @param args
     */
    public static void main(String[] args) {
        /**
         * 二分法查找重复值
         *  二分法查找一定范围内的数值总数是否大于该范围的差值，大于该差值则表明该范围内一定有重复的数字，但是不保证另一个里面没有重复的数字。
         */
        int[] nums1 = {1,3,4,2,2};
        System.out.println(findDuplicate(nums1));

        /**
         * 快慢指针法查找重复值
         * 快指针一次跳两步，慢指针一次跳一步。
         * 链表有闭环时，它们会相遇，当相遇时，快指针比慢指针多走一个闭环的步长。
         * 此时慢指针再有K步将走到闭环终点，K步就是从链表头部走到闭环起点的距离
         */
        int[] nums2 = {2,5, 4 ,6,9,3,8, 9 ,7,1};
        System.out.println(fastAndSlowPoint(nums2));
    }
    public static int findDuplicate(int[] nums) {
        if(nums==null||nums.length<1){
            return 0;
        }
        int start = 1;
        int end = nums.length-1;
        while(end>=start){
            int middle = ((end-start)>>1)+start;
            int count = countRange(start,middle,nums);
            if(end==start){
                if(count>1){
                    return start;
                }else{
                    break;
                }
            }
            if(count>(middle-start+1)){
                end = middle;//middle此时有可能是重复的，所以缩小二分查找范围的时候要将middle包含在内
            }else{
                start = middle+1;//+1是必要的，因为在之前的检查中已经排除了middle是重复的可能性。而且不+1将陷入当middle、end、start相等的死循环
            }
        }
        return 0;
    }
    private static int countRange(int start , int end , int[] nums ){
        if(nums==null){
            return 0;
        }
        int count = 0 ;
        for(int i = 0 ; i <nums.length ;i++){
            if(nums[i]<=end&&nums[i]>=start){
                count++;
            }
        }
        return count;
    }

    //快慢指针
    public static int fastAndSlowPoint(int[] nums){
        int fast = 0 , slow = 0;
        while(true){
            fast = nums[nums[fast]];
            slow = nums[slow];
            if(slow==fast){
                fast = 0;
                while(nums[slow]!=nums[fast]){
                    fast = nums[fast];
                    slow = nums[slow];
                }
                return nums[fast];
            }
        }
    }
}
