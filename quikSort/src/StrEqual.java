import java.util.Scanner;

public class StrEqual {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        int[] arr = new int[count];
        int less = 0;
        String result = "YES";
        Boolean hZ = false;
        for(int i = 0 ;  i < count ; i++ ){

            int now = sc.nextInt();
            if(now==0){
                hZ = true;
            }
            if(i==0){
                less = now;
            }
            if(now<less){
                less = now;
            }
            arr[i] = now;
        }
        if(hZ){
            result = "NO";
        }else{
            for(int i = 0 ; i < count ; i++ ){
                if(arr[i]==0){
                    result = "NO";
                    break;
                }
                if(arr[i]-less==0){
                    continue;
                }

                int comp = arr[i]*2;

                if((comp/less)%2==0){
                    continue;
                }

                result = "NO";
                break;
            }
        }

    }
}
