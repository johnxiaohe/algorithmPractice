package main.java.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LKMS06
 * @Description TODO 力扣面试题06
 * @Author HeXiaoyuan
 * @Date 2020-05-24 22:13
 */
public class LKMS06 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode two = new ListNode(3);
        ListNode tree = new ListNode(2);
        head.next = two; two.next=tree;
        int[] result = reversePrint(head);
        System.out.println(result);
    }

    public static int[] reversePrint(ListNode head) {
        List<Integer> result = new ArrayList<>();
        dg(head,result);
        int[] data = new int[result.size()];
        for(int i=0;i<result.size();i++){
            data[i]=result.get(i);
        }
        return data;
    }
    private static void dg(ListNode node , List<Integer> result){
        if(node!=null&&node.next!=null){
            dg(node.next,result);
        }
        if(node!=null)
            result.add(node.val);
    }

    static class ListNode {
    int val;
     ListNode next;
    ListNode(int x) { val = x; }
    }


}
