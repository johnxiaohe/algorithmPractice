package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class simplePath {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String oldPath = sc.nextLine();
        if(oldPath.equals("")){
            System.out.println("");
            return;
        }
        String[] dityArr = oldPath.split("/");
        List<String>  list = new ArrayList();
        for(int i=0;i<dityArr.length;i++){
            if(dityArr[i].equals("")){
                continue;
            }
            if(dityArr[i].equals(".")){
                continue;
            }
            if(dityArr[i].equals("..")){
                if(i>0&&dityArr[i-1].equals("..")){
                    continue;
                }
                if(list.size()>1){
                    list.remove(list.size()-1);
                }
                continue;
            }
            list.add(dityArr[i].trim());
        }
        if(list.size()<1){
            System.out.println("");
            return;
        }
        StringBuilder sb = new StringBuilder("/");
        for(String s : list){
            sb.append(s).append("/");
        }

        System.out.println(sb.substring(0,sb.length()-1));
    }



}
