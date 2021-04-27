package leetcode;

import java.util.*;

/**
 * 剑指 Offer 38. 字符串全排列
 * 输入一个字符串，打印出该字符串中字符的所有排列
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 */
public class LK38 {

    public static void main(String[] args) {
        String s = "abc";
        String[] result = permutation(s);
        System.out.println(Arrays.asList(result).toString());
    }

    // 回溯算法: 将不同的数字存放辅助数组中.并做好原数组取出数字标记.用完放回去;
    // 最主要的就是向下递归的选择把握以及递归完毕对状态信息的回朔调整
    public static String[] permutation(String s) {
        char[] origin = s.toCharArray();
        Set<String> result = new HashSet<>();
        fullArrangement(origin, "", result, new boolean[origin.length]);
        return result.toArray(new String[result.size()]);
    }

    // 每个位置按照不同的顺序将字符取出再放回,注意重复剪枝即可;
    public static void fullArrangement(char[] origin, String s, Set<String> result, boolean[] visit){
        if(s.length() == origin.length){
            result.add(s);
            return;
        }
        // 记录当前数组存放内容以及访问过的元素
        for(int i = 0; i < origin.length; i++){
            if(visit[i]){
                continue;
            }
            visit[i] = true;
            fullArrangement(origin, s.concat(String.valueOf(origin[i])), result, visit);
            visit[i] = false;
        }
    }

}
