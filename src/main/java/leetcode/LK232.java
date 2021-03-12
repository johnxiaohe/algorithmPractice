package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LK232
 * @Description TODO 力扣232题 用栈实现队列
 * @Author HeXiaoyuan
 * @Date 2020-07-24 0:38
 */
public class LK232 {

    List<Integer> original;
    Integer head = 0 , tail = -1;

    public static void main(String[] args) {
        LK232 lk232 = new LK232();
        lk232.push(1);
        lk232.push(2);
        System.out.println(lk232.peek());
        System.out.println(lk232.pop());
        System.out.println(lk232.empty());
    }

    /** Initialize your data structure here. */
    public LK232() {
        original = new ArrayList<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        original.add(x);
        tail ++;
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
       Integer result = original.get(head);
       head++;
       return result;
    }

    /** Get the front element. */
    public int peek() {
        return original.get(head);
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        if(original.isEmpty() || head>tail){
            return true;
        }
        return false;
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
