package leetcode;

import java.util.Stack;

/**
 * @ClassName LKJZOF09
 * @Description TODO 力扣剑指offer 09 用两个栈实现一个队列
 * @Author HeXiaoyuan
 * @Date 2020-07-23 23:39
 */
public class LKJZOF09 {

    private Stack<Integer> originStack;
    private Stack<Integer> revertStack;

    public LKJZOF09() {
        originStack = new Stack<>();
        revertStack = new Stack<>();
    }

    public void appendTail(int value) {
        originStack.push(value);
    }

    public int deleteHead() {
        Integer result = -1;
        if(!revertStack.empty()){
            result = revertStack.pop();
        }else if(!originStack.empty()){
            revert();
            result = revertStack.pop();
        }
        return result;
    }

    private void revert(){
        while (!originStack.empty()){
            revertStack.push(originStack.pop());
        }
    }


}
