package leetcode;

/**
 * 力扣695题: 最大岛屿面积
 * 递归+深度优先遍历
 */
public class LK695 {

    public static void main(String[] args) {
        int[][] weak = new int[8][13];
        weak[0] = new int[]{0,0,1,0,0,0,0,1,0,0,0,0,0};
        weak[1] = new int[]{0,0,0,0,0,0,0,1,1,1,0,0,0};
        weak[2] = new int[]{0,1,1,0,1,0,0,0,0,0,0,0,0};
        weak[3] = new int[]{0,1,0,0,1,1,0,0,1,0,1,0,0};
        weak[4] = new int[]{0,1,0,0,1,1,0,0,1,1,1,0,0};
        weak[5] = new int[]{0,0,0,0,0,0,0,0,0,0,1,0,0};
        weak[6] = new int[]{0,0,0,0,0,0,0,1,1,1,0,0,0};
        weak[7] = new int[]{0,0,0,0,0,0,0,1,1,0,0,0,0};
        System.out.println(maxArea(weak));
    }

    // 先每行遍历,当节点为1进入计算面积方法.
    // 计算面积方法计算完面积则将该地方置为0
    // 从当前点向上下左右进行面积计算
    public static Integer maxArea(int[][] weak){
        int maxArea = 0;
        if(weak.length < 1){
            return maxArea;
        }
        // 纵向遍历
        for(int i=0; i < weak.length; i++){
            // 横向遍历
            for(int j=0; j < weak[i].length; j++){
                // 当该节点出现岛屿时,执行计算面积方法.计算面积方法向该点的四面八方搜索
                if(weak[i][j] == 1){
                    maxArea = Math.max(maxArea, search(weak, i, j));
                }
            }
        }

        return maxArea;
    }

    /**
     *
     * @param weak 湖
     * @param i 纵坐标
     * @param j 横坐标
     * @return
     */
    private static Integer search(int[][] weak, int i, int j){
        if(weak[i][j] ==0){
            return 0;
        }
        int currentArea = 1;
        // 将当前节点置为0,毕竟已经搜索过了算在这个岛的面积上了.就不在另一个岛上计算了
        weak[i][j] = 0;
        // 向上
        if(i-1 >= 0){
            currentArea = currentArea + search(weak, i-1, j);
        }
        // 向下
        if(i+1 < weak.length){
            currentArea = currentArea + search(weak, i+1, j);
        }
        // 向前
        if(j-1 >= 0){
            currentArea = currentArea + search(weak, i, j-1);
        }
        // 向后
        if(j+1 < weak[i].length){
            currentArea = currentArea + search(weak, i, j+1);
        }
        return currentArea;
    }

}
