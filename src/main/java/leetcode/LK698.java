package leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * labuladong算法文章 : https://mp.weixin.qq.com/s/fsLKaWBvSWtM0jA-CfOxyA
 *
 * 题目: 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 *
 * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * 输出： True
 * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
 *
 * 解读: 该题总和都相等提供了一个条件 : 每个子集总和其实只能是整数数组整除K的结果
 *      因为每个子集和大于该值则总和大小大于数组总数,小于该值总和大小小于数组总数.
 * 在递归之前「做选择」，在递归之后「撤销选择」.可从数组和桶两方面考虑不同解法
 */
public class LK698 {

    public static void main(String[] args) {
        int[] nums = new int[]{10,10,10,7,7,7,7,7,7,6,6,6};
        int k = 3;
        canPartitionKSubsets(nums, k);
    }

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        // 如果数组数量小于k则无法完成平分
        if(k > nums.length) return false;
        int sum = 0;
        int max = 0;
        for(int i: nums) {
            sum += i;
            if(i > max){
                max = i;
            }
        }
        // ！！！比较重要: 按上面说的,子集和只能是整除K的结果.所以这里无法整除则意味着不能平分
        if(sum % k !=0) return false;
        // 这里还有一个条件,当桶的和小于数组中的一个值,则也不能完成平分
        int target = sum / k;
        if(max > target) return false;

        // 以整数数组为回溯目标的方法
//        return numsToDo(target, k, nums);
        // 以桶为回溯目标的方法
        return bucketToDo(target, k, nums);
    }

    static boolean numsToDo(int target, int k, int[] nums){
        //数组由大到小排序,让大数先入桶减少递归次数.
        // 从小到大拍
        Arrays.sort(nums);
        for(int i = 0, j = nums.length-1; i < j; i++, j--){
            // 交换 nums[i] 和 nums[j]
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        //定义一个桶
        int[] bucket = new int[k];

        return buckTrackByNum(target, bucket, 0, nums);
    }


    static boolean bucketToDo(int target, int k, int[] nums){
        boolean[] used = new boolean[nums.length];

        return bucketTrackBybucket(target, k, 0, 0, nums, used);
    }

    /**
     * 以整数数组为回溯目标,每次回溯找到第一个能放入的桶中(小于等于目标值,因为向下递归的时候并不知道那个桶已经满了,所以每次需要从头递归桶)
     * 然后接着下一个整数找桶放.当数字完了查看每个桶是否和目标值一致
     * @return
     */
    static boolean buckTrackByNum(int target, int[] bucket, int numIndex, int[] nums){
        if(numIndex == nums.length){
            //数组遍历完毕查看桶中数字是否满足和相同
            for(int i : bucket){
                if(i != target){
                    return false;
                }
            }
            return true;
        }
        for(int i=0; i<bucket.length; i++){
            // 计算当前桶和整数结果是否大于目标值,大于则表示不适合该桶则换下一个桶试,每个数字有K个选择.都不符合则表示不能满足题目
            int sum = bucket[i] + nums[numIndex];
            if(sum > target) continue;

            // 如果值小于等于目标值则将桶的值更新并递归至下一个数字
            bucket[i] = sum;

            //总会递归到整数数组结尾/没有合适的结果结尾,从结尾开始回归结果,如果都是true则继续向上级返回true.否则回溯进行下一个桶尝试
            if(buckTrackByNum(target, bucket, numIndex+1, nums)){
                return true;
            }
            // 回溯算法中将值恢复是很重要的步骤.恢复之前的状态换一个新的分支再次尝试执行
            bucket[i] = sum - nums[numIndex];

        }
        return false;
    }

    /**
     * 以桶为回溯目标,则每次执行以将一个桶填满为目的,记录好当前使用的数组.
     * 一个桶满后则开始下一个桶装填(以回溯的方式装填),如果下一个桶装填无效则回溯至该桶,切换其他元素尝试.如果均无果则返回false
     * @return
     */
    static boolean bucketTrackBybucket(int target, int k, int bucketNum, int numsIndex, int[] nums, boolean[] used){
        if(k == 0){
            // 只有一个桶满了才会递归至下一个桶,所以这里表示已经装填完毕.不用担心有余数,有余数再取余那块就过不去了.
            return true;
        }
        // 执行判断,判断是否可以进行下一个桶的递归.
        if(bucketNum == target){
            return bucketTrackBybucket(target, k-1, 0, 0, nums, used);
        }
        // 从该桶上上个执行过的方法中的索引继续
        for(int i=numsIndex; i<nums.length; i++){
            // 判断当前数字是否使用过以及是否满足桶要求.否则及时剪枝
            if(used[i]) continue;
            int sum = bucketNum + nums[i];
            if(sum > target) continue;

            used[i] = true;
            bucketNum = sum;
            // 当前桶满了就去下一个桶继续装填,
            if(bucketTrackBybucket(target, k, bucketNum, i+1, nums, used)){
                //当前桶没满,递归继续装填当前桶.如果下一个桶装填递归分支成功则会一路返回成功到这一步(true一定是从最后一级开始向上返回的),直接继续向上反馈成功消息即可.
                return true;
            }
            // false从任意一级失败都可能返回,失败则返回false.这时进行参数值回溯再循环递归尝试其他数字分支
            used[i] = false;
            bucketNum = sum - nums[i];

        }
        // 在最后返回的都是遍历完都没找到合适数字的,一定是false
        return false;
    }
}
