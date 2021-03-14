package leetcode;

import java.util.*;

/**
 * 659. 分割数组为连续子序列
 * 给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个长度至少为 3 的子序列，其中每个子序列都由连续整数组成。
 *
 * 如果可以完成上述分割，则返回 true ；否则，返回 false 。
 * 示例 1：
 *
 * 输入: [1,2,3,3,4,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3
 * 3, 4, 5
 * 示例 2：
 *
 * 输入: [1,2,3,3,4,4,5,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3, 4, 5
 * 3, 4, 5
 *
 * 示例 3：
 *
 * 输入: [1,2,3,4,4,5]
 * 输出: False
 *
 * 参考 玩个斗地主也能玩出算法？(https://mp.weixin.qq.com/s/aBBwqIhwkwSfqW5uHAC2Bg)
 * 解决方法 : 使用贪心算法 --> 优先满足让更多的队列大于等于3即可.否则就满足等于2,否则就添加到大于等于3的队列上,最后考虑新创建队列(每次新创建的队列都有可能让结果为false所以最后考虑)
 *  优化: 不需要存储对应的队列结果,只需要记录对应所需队尾元素的队列有几个即可.
 */
public class LK659 {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,6,7,8};
        System.out.println(isPossible2(nums));
    }

    public static boolean isPossible(int[] nums) {
        if(nums == null || nums.length < 3){
            return false;
        }
        // 使用贪心+标记存储 --> 优先满足让更多的队列大于等于3即可.否则就满足等于2,否则就添加到大于等于3的队列上,最后考虑新创建队列(每次新创建的队列都有可能让结果为false所以最后考虑)
        // 声明一个存放已经满足队列长度的Map. key为队列最后一个数字.
        Map<Integer, List<List<Integer>>> readyMap = new HashMap<>();
        // 声明一个存放还在增长的Map. key为队列最后一个数字,大于三则移至readyMap中
        Map<Integer, List<List<Integer>>> growingMap = new HashMap<>();
        // 声明一个初创建只有一个元素的队列存储Map
        Map<Integer, List<List<Integer>>> createdMap = new HashMap<>();
        for(int i = 0; i< nums.length; i++){
            int currentNum = nums[i];
            int pre = currentNum - 1;
            if(growingMap.containsKey(currentNum-1)){
                List<List<Integer>> growingLists = growingMap.get(pre);
                List<Integer> growingList = growingLists.get(0);
                growingLists.remove(0);
                if(growingLists.isEmpty()){
                    growingMap.remove(pre);
                }

                // 添加完毕已经成长为三元素队列,移除成长Map至已准备好队列. 并判断当前成长队列是否为空,为空则删除当前map中该key
                growingList.add(currentNum);
                List<List<Integer>> readLists = readyMap.getOrDefault(currentNum, new ArrayList<>());
                readLists.add(growingList);
                readyMap.put(currentNum, readLists);

            }else if(createdMap.containsKey(pre)){
                List<List<Integer>> createdLists = createdMap.get(pre);
                List<Integer> createdList = createdLists.get(0);
                createdLists.remove(0);
                if(createdLists.isEmpty()){
                    createdMap.remove(pre);
                }

                // 添加完毕已经成长为二元素队列,移除创建Map至成长队列. 并判断当前队列是否为空,为空则删除当前map中该key
                createdList.add(currentNum);
                List<List<Integer>> growingLists = growingMap.getOrDefault(currentNum, new ArrayList<>());
                growingLists.add(createdList);
                growingMap.put(currentNum, growingLists);

            }else if(readyMap.containsKey(pre)){
                List<List<Integer>> readyLists = readyMap.get(pre);
                List<Integer> readyList = readyLists.get(0);
                readyLists.remove(0);
                if(readyLists.isEmpty()){
                    readyMap.remove(pre);
                }

                readyList.add(currentNum);
                List<List<Integer>> nextReadyLists = readyMap.getOrDefault(currentNum, new ArrayList<>());
                nextReadyLists.add(readyList);
                readyMap.put(currentNum, nextReadyLists);
            }else{
                List<Integer> createdList = new ArrayList<>();
                createdList.add(currentNum);
                List<List<Integer>> createdLists = createdMap.getOrDefault(currentNum, new ArrayList<>());
                createdLists.add(createdList);
                createdMap.put(currentNum, createdLists);
            }
        }

        // 遍历完还有growing的Map则表示为false
        return growingMap.isEmpty() && createdMap.isEmpty();
    }

    public static boolean isPossible1(int[] nums) {
        if(nums == null || nums.length < 3){
            return false;
        }
        // 使用贪心+标记存储 --> 优先满足让更多的队列大于等于3即可.否则就满足等于2,否则就添加到大于等于3的队列上,最后考虑新创建队列(每次新创建的队列都有可能让结果为false所以最后考虑)
        // 声明一个存放已经满足队列长度的Map. key为队列最后一个数字.
        Map<Integer, Integer> readyMap = new HashMap<>();
        // 声明一个存放还在增长的Map. key为队列最后一个数字,大于三则移至readyMap中
        Map<Integer, Integer> growingMap = new HashMap<>();
        // 声明一个初创建只有一个元素的队列存储Map
        Map<Integer, Integer> createdMap = new HashMap<>();
        for(int i = 0; i< nums.length; i++){
            int currentNum = nums[i];
            int pre = currentNum - 1;
            if(growingMap.containsKey(currentNum-1)){
                Integer growingLists = growingMap.get(pre);
                growingLists -=1;
                if(growingLists == 0){
                    growingMap.remove(pre);
                }else{
                    growingMap.put(pre, growingLists);
                }

                // 添加完毕已经成长为三元素队列,移除成长Map至已准备好队列. 并判断当前成长队列是否为空,为空则删除当前map中该key
                Integer readLists = readyMap.getOrDefault(currentNum, 0);
                readLists +=1;
                readyMap.put(currentNum, readLists);

            }else if(createdMap.containsKey(pre)){
                Integer createdLists = createdMap.get(pre);
                createdLists -=1;
                if(createdLists == 0){
                    createdMap.remove(pre);
                }else{
                    createdMap.put(pre, createdLists);
                }

                // 添加完毕已经成长为二元素队列,移除创建Map至成长队列. 并判断当前队列是否为空,为空则删除当前map中该key
                Integer growingLists = growingMap.getOrDefault(currentNum, 0);
                growingLists +=1;
                growingMap.put(currentNum, growingLists);

            }else if(readyMap.containsKey(pre)){
                Integer readyLists = readyMap.get(pre);
                readyLists -=1;
                if(readyLists == 0){
                    readyMap.remove(pre);
                }else{
                    readyMap.put(pre, readyLists);
                }

                Integer nextReadyLists = readyMap.getOrDefault(currentNum, 0);
                nextReadyLists +=1;
                readyMap.put(currentNum, nextReadyLists);
            }else{
                Integer createdLists = createdMap.getOrDefault(currentNum, 0);
                createdLists +=1;
                createdMap.put(currentNum, createdLists);
            }
        }

        // 遍历完还有growing的Map则表示为false
        return growingMap.isEmpty() && createdMap.isEmpty();
    }

    // 最后大佬这个根据递增规律. 根据每个位置和第一个数的差值映射对应位置(如果是连续的则对应差值位置也连续)并将该位置+1; 如果有连续不为0的数据孤岛小于3个的则会返回false
    public static boolean isPossible2(int[] nums) {
        int min = nums[0];
        int max = nums[nums.length-1];
        int[] numNumber = new int[max-min+1];
        for (int i = 0; i<nums.length; i++){
            numNumber[nums[i]-min]++;
        }
        for (int i = 0; i<numNumber.length; i++){
            while (numNumber[i]!=0){
                int num=1;
                for (int j = i; j+1!=numNumber.length && numNumber[j+1]>=numNumber[j];j++){
                    num++;
                }
                if (num<3){
                    return false;
                }
                for (int j = i; j-i<num; j++){
                    numNumber[j]--;
                }
            }
        }
        return true;
    }
}
