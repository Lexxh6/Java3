package Lesson6.dz;

import org.junit.Test;

import java.util.Arrays;


public class MassCheker {
    public int[] masChk(int[] m) {
        int length = m.length ;
        if (length == 0) return null;
        int p = -1;
        for (int i = 0; i <length ; i++) {
            if (m[i] == 4 && i != length-1 ) p = i;
        }
        if (p == -1) throw new RuntimeException("No '4' correct findings");
        return Arrays.copyOfRange(m,++p,length);
    }
    public boolean masChkAB(int[] m){
        int a = 1;
        int b = 4;
        if (m.length < 2) return false;
        boolean isA = false;
        boolean isB = false;
        for (int i: m){
            if (i == a) isA = true;
            else if (i == b) isB = true;
            else return false;
        }
        return (isA && isB)? true : false;
    }
    @Test
    public void asad(){}
}

