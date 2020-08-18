package ui.base;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Password {
    private static int[] sMaps = new int[] {3,5,0,4,2,8,9,1,7,6};
    private static String[] sBCDs = new String[] {"0000","0001","0010","0011","0100","0101","0110","0111","1000","1001"};
    private static int[] sManagerIds = new int[] {5, 1, 8};

    private int mRandom;
    private Map<Integer, Integer> mPasswordIDs = new HashMap<>();  // <password, id>

    public Password() {
        mRandom = new Random(System.currentTimeMillis()).nextInt(10000);
        for (int id : sManagerIds) {
            mPasswordIDs.put(generatePassword(id), id);
            System.out.println(generatePassword(id));
        }
    }

    public int getRandom() {
        return mRandom;
    }

    public int checkForId(int password) {
        return mPasswordIDs.getOrDefault(password, -1);
    }

    /**
     * id >= 1
     */
    private int generatePassword(int id) {
        int a = sMaps[mRandom / 1000];
        int b = sMaps[(mRandom % 1000) / 100];
        int c = sMaps[(mRandom % 100) / 10];
        int d = sMaps[mRandom % 10];
        char[] bcds = getBCDs(a, b, c, d);
        bcds = circleLeftMove(bcds, id);
        return getNumByBCDs(bcds);
    }

    private char[] getBCDs(int a, int b, int c, int d) {
        String bcdStr = sBCDs[a] + sBCDs[b] + sBCDs[c] + sBCDs[d];
        return bcdStr.toCharArray();
    }

    private char[] circleLeftMove(char[] bits, int times) {
        if (times <= 0) return bits;
        times = times % bits.length;
        char[] tmp = new char[times];
        System.arraycopy(bits, 0, tmp, 0, times);
        System.arraycopy(bits, times, bits, 0, bits.length - times);
        System.arraycopy(tmp, 0, bits, bits.length - times, times);
        return bits;
    }

    private int getNumByBCDs(char[] bcds) {
        int a = getNumByBCD(Arrays.copyOfRange(bcds, 0, 4));
        int b = getNumByBCD(Arrays.copyOfRange(bcds, 4, 8));
        int c = getNumByBCD(Arrays.copyOfRange(bcds, 8, 12));
        int d = getNumByBCD(Arrays.copyOfRange(bcds, 12, 16));
        if (d >= 10) {
            d -= 10;
            c += 1;
        }
        if (c >= 10) {
            c -= 10;
            b += 1;
        }
        if (b >= 10) {
            b -= 10;
            a += 1;
        }
        if (a >= 10) {
            a -= 10;
        }
        return a * 1000 + b * 100 + c * 10 + d;
    }

    private int getNumByBCD(char[] bcd) {
        if (bcd.length != 4) throw new NumberFormatException("非法BCD码");
        int ret = 0;
        if (bcd[0] == '1') ret += 8;
        if (bcd[1] == '1') ret += 4;
        if (bcd[2] == '1') ret += 2;
        if (bcd[3] == '1') ret += 1;
        return ret;
    }
}
