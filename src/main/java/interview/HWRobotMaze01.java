package interview;

import java.util.Scanner;

/**
 * @ClassName 华为机试二星题--机器人走迷宫
 *              进 # # # # #
 *              # # # # 0 0
 *              0 0 0 # # #
 *              # # # # # 出
 * @Description TODO 题目大致是机器人走一个迷宫,给出迷宫的x和y(x*y的迷宫)并且迷宫中有障碍物,输入k表示障碍物有k个,并且会将障碍物的横纵坐标挨个输入。
 *                  机器人从0,0的位置走到x,y的位置并且只能向x,y增加的方向走,不能回退.
 *                  如上图,#表示可以走的方格,0代表障碍,机器人从0,0的位置只能向下或者向前走到出口.
 *                  其中会有不可达方格和陷阱方格.不可达方格为第四行前三个,该机器人在行走路径上不可能走到的方格,陷阱方格如第一行最后两个,走进之后则不能抵达终点.
 *                  要求: 输出陷阱和不可达方格方格数量
 *                  该题为动态规划题,采用动态规划+标记法将该二维数组通过迭代方法走过能走的路径并用不同标记标记所路过的方块属性即可.
 *                  先采用迭代法迭代到结尾,再从结尾属性一步步回推回来即可
 * @Author HeXiaoyuan
 * @Date 2021-01-31 19:28
 */
public class HWRobotMaze01 {
    public static void main(String[] args) {
        // 0 是未踩过的。 1是墙。 2是踩过的。
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int y = in.nextInt();
        int[][] room = new int[x][y];
        int wall = in.nextInt();
        while(wall-->0){
            int wallX = in.nextInt();
            int wallY = in.nextInt();
            room[wallX][wallY] = 1;
        }
        path(room, 0, 0, x-1, y-1);
        int badPath = 0; //陷阱
        int noWay = 0; // 不可达
        for(int i =0; i< x; i++){
            for(int j=0; j< y; j++){
                if(room[i][j] == 9){
                    badPath += 1;
                }else if(room[i][j] == 0){
                    noWay += 1;
                }
            }
        }
        System.out.println(badPath+" "+noWay);
    }

    //不可达方格 : 机器人无法通过增加X Y值到的方格.走完还是0的代表不可达
    //陷阱方格 : 走到该位置不能正确走到终点的方格。 向前/向上不可达/同为陷阱方格则也标记为陷阱方格 9
    //走过的为2
    private static void path(int[][] room, int nextX, int nextY, int x, int y){
        //判断是墙直接跳过
        if(room[nextX][nextY] ==1){
            return;
        }
        if(room[nextX][nextY] != 0){
            return;
        }
        if(nextX == x && nextY == y){
            room[nextX][nextY] = 2;
            return;
        }
        if(nextX < x){
            path(room, nextX+1, nextY, x, y);
        }
        if(nextY < y){
            path(room, nextX, nextY+1, x, y);
        }

        //该点向上/向前均为不可达/陷阱方格则为陷阱方格
        if(nextX == x || nextY == y){
            if(nextX == x && nextY < y && room[nextX][nextY+1] != 2){
                room[nextX][nextY] = 9;
            }else if(nextY == y && nextX < x && room[nextX+1][nextY] != 2){
                room[nextX][nextY] = 9;
            }else{
                room[nextX][nextY] = 2;
            }
        }else if(room[nextX+1][nextY] !=2 && room[nextX][nextY+1] !=2){
            room[nextX][nextY] = 9;
        }else{
            room[nextX][nextY] = 2;
        }
        return;
    }

}
