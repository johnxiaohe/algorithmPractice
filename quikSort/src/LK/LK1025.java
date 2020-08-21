package LK;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.security.MessageDigest;

/**
 * @ClassName LK1025
 * @Description TODO 力扣1025题 除数博弈
 * @Author HeXiaoyuan
 * @Date 2020-07-27 1:35
 */
public class LK1025 {

    /**
     *  1. 奇数的约数为奇数。奇数-奇数 = 偶数
     *  2. 当该用户为偶数时，则约数可奇可偶。可以让另一个用户一直轮到奇数。直至让自己至2时则必胜。
     *  3. 所以当爱丽丝是先手并且自己轮到偶数时则必胜，反之轮到奇数时，对手则一直会轮到偶数则队手必胜
     *
     **/
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String result = new String("73.25%");
        byte[] digest = result.getBytes("UTF-8");
        MessageDigest md2 = MessageDigest.getInstance("MD2");
        for(int i= 0 ; i<100000000 ; i++){
            digest = md2.digest(digest);
            digest = HexBin.encode(digest).getBytes("UTF-8");
        }
        System.out.println(HexBin.encode(digest));
    }

}
