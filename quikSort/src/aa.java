import java.util.HashMap;
import java.util.Map;

public class aa {

    public static void main(String[] args) {
        System.out.println(mostFrequentLetter("12345._abcd"));
    }


    public static Character mostFrequentLetter(String string) {

        char[] arr = string.toLowerCase().toCharArray();
        Map<Integer,Integer> map = new HashMap();
        for(int i=0;i<arr.length;i++){
            Integer num = Integer.valueOf(arr[i]);
            if(arr[i]<97||arr[i]>122){
                continue;
            }
            if(map.containsKey(num)){
                Integer totle = map.get(num);
                totle++;
                map.put(num, totle);
            }else{
                map.put(num, 1);
            }
        }
        if(!map.isEmpty()){

            Integer max = 0;
            Integer maxLocation = 0;
            for(Map.Entry<Integer,Integer> entry : map.entrySet()){
                if(entry.getValue().compareTo(max)>0){
                    max = entry.getValue();
                    maxLocation = entry.getKey();
                    continue;
                }
                if(entry.getValue().compareTo(max)==0){
                    if(entry.getKey().compareTo(maxLocation)<0){
                        maxLocation = entry.getKey();
                    }
                }
            }
            return (char)(int)maxLocation;
        }

        return null;
    }
}
