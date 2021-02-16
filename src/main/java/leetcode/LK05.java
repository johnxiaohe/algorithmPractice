package main.java.leetcode;

public class LK05 {

    public static void main(String[] args) {
        String a = "We are happy.";
        LK05 lk05 = new LK05();
        System.out.println(lk05.replaceSpace3(a));
    }

    public String replaceSpace1(String s) {
        if(s==null){
            return null;
        }
        return s.replaceAll(" " , "%20");
    }

    public String replaceSpace2(String s) {
        if(s==null){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< s.length() ; i++){
            char c = s.charAt(i);
            if(c ==' ') sb.append("%20");
            else sb.append(c);
        }
        return sb.toString();
    }

    public String replaceSpace3(String s) {
        char[] sArr = s.toCharArray();
        int spaceNum = 0;
        for(int i=0;i<sArr.length;i++){
            if(sArr[i] == ' '){
                spaceNum++;
            }
        }
        if(spaceNum==0){
            return s;
        }
        int indexOfOriginal = sArr.length-1;
        int indexOfNew = indexOfOriginal+spaceNum*2;
        char[] sArrR = new char[indexOfNew+1];
        while(indexOfOriginal>=0){
            if(sArr[indexOfOriginal] == ' '){
                sArrR[indexOfNew--] = '0';
                sArrR[indexOfNew--] = '2';
                sArrR[indexOfNew--] = '%';
            }else{
                sArrR[indexOfNew--] = sArr[indexOfOriginal];
            }
            --indexOfOriginal;
        }

        return String.valueOf(sArrR);
    }

}
