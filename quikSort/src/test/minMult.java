package test;

import java.util.Scanner;

public class minMult {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        boolean asc = true;
        if(a==b){
            printResult(a);
            return;
        }
        if(a>b){
            asc=false;
        }
        if(asc){
            for(int i=a;i<=b;i++){
                printResult(i);
            }
        }else{
            for(int i=a;i>=b;i--){
                printResult(i);
            }
        }
    }
    public  static void printResult(int a){
        if(a<=0){
            System.out.println(a);
        }else if(a%3==0){
            if(a%5==0){
                System.out.println("foobar");
                return;
            }
            System.out.println("foo");
        }else if(a%5==0){
            System.out.println("bar");
        }else{
            System.out.println(a);
        }
    }
}
