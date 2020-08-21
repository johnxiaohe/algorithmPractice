package LK;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @ClassName LK225
 * @Description TODO 力扣225题 用队列实现栈
 * @Author HeXiaoyuan
 * @Date 2020-07-24 1:09
 */
public class LK225 {

    //原始队列和协助队列
    private Queue<Integer> offerQueue;
    private Queue<Integer> pollQueue;

    /** Initialize your data structure here. */
    public LK225() {
        offerQueue = new ArrayDeque();
        pollQueue = new ArrayDeque();
    }

    /** 只在添加队列中加入元素*/
    public void push(int x) {
        offerQueue.offer(x);
    }

    /**时刻保持一个队列为空的状态，在获取栈顶元素时，将除队尾元素全转移到空队列中，最后弹出该元素，又形成一个空队列等待下次弹出使用 */
    public int pop() {
        return clearQueue();
    }

    /**
     * 读取的时候依旧执行弹出操作，不过要将该值加入另一个辅助队列中
     * */
    public int top() {
        Integer result = clearQueue();
        if(result!=null){
           offerQueue.offer(result);
        }
        return result;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return offerQueue.isEmpty();
    }
    /** 弹出/查看元素的时候要转移清空一个队列 */
    private Integer clearQueue(){
        if(offerQueue.isEmpty()){
            return null;
        }
        while (offerQueue.size()>1){
            pollQueue.offer(offerQueue.poll());
        }
        Integer popElement = offerQueue.poll();

        Queue<Integer> tQue = offerQueue;
        offerQueue = pollQueue;
        pollQueue = tQue;
        return popElement;
    }
}

/**
 * 这里使用Java队列时，不推荐使用队列的add/remove/element方法 他们会在队列元素条件不满足时抛出异常
 * 对应使用offer/poll/peek方法，在不满足会返回操作结果/Null
 * put/take方法在不满足时会阻塞
 */
