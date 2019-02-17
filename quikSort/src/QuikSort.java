import java.util.Arrays;

public class QuikSort {
    public static void main(String[] args){
        int[] a1 = new int[] {3,5,7,8,9,5,1,2,6};
        //数组从0开始 从数组最后一个数字的位置结束 0-length-1
        quikSort1(a1,0,a1.length-1);
        System.out.println(Arrays.toString(a1));
    }

    public static void quikSort1(int[] a,int start,int enden){
        //快排要有一个基准数，以此基准数为标准将数组分为左右两部分
        //以升序排序为例：左边要比基准数小或者相等，右边要比基准数大 然后递归循环两段数组
        if(start<enden){
            int sign = a[start];
            int begin = start;
            int over = enden;
            //如果起始位置比结束位置小说明没有遍历完就继续遍历
            while(begin<over){
                //最后一个数字比起始位置大说明不需要替换，就往后遍历
                while(begin<over&&a[over]>=sign){
                    over--;
                }
                //小了就将换到前面
                //这里有一个问题就是如果是因为前面begin<over不成立才出来的互换有什么问题么
                //答案是没有，因为此时over和begin是相等的
                a[begin]=a[over];
                //然后从前面比较，如果前面的比标准数小就往前遍历
                while(begin<over&&a[begin]<sign){
                    begin++;
                }
                //大了就换到后面
                a[over]=a[begin];

            }
            //遍历完之后，我们需要将标准数加进去，加到begin或者over位置都可以，因为此时它们是相等的
            a[begin]=sign;
            //递归
            quikSort1(a,start,begin);
            quikSort1(a,begin+1,enden);
        }
    }



}
