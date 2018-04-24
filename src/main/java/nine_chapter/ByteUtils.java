package nine_chapter;

/**
 * Bytes数组处理工具
 * @author
 */
public class ByteUtils {

    /**
     * byte转int
     * @param b
     * @param start
     * @param len
     * @return
     */
    public static int bytes2Int(byte[] b, int start, int len) {
        int sum = 0;
        int end = start + len;
        for (int i = start; i < end; i++) {
            //在byte转int时，需要保持二进制补码的一致性，所以要& 0xff。见我的博客：https://blog.csdn.net/u011305680/article/details/79921058
            int n = ((int) b[i]) & 0xff;
            n <<= (--len) * 8;
            sum = n + sum;
        }
        return sum;
    }

    /**
     * int转byte
     * @param value
     * @param len
     * @return
     */
    public static byte[] int2Bytes(int value, int len) {
        byte[] b = new byte[len];
        for (int i = 0; i < len; i++) {
            b[len - i - 1] = (byte) ((value >> 8 * i) & 0xff);
        }
        return b;
    }

    /**
     * byte转string
     * @param b
     * @param start
     * @param len
     * @return
     */
    public static String bytes2String(byte[] b, int start, int len) {
        return new String(b, start, len);
    }

    /**
     * String转byte
     * @param str
     * @return
     */
    public static byte[] string2Bytes(String str) {
        return str.getBytes();
    }

    /**
     * 字节替换
     * @param originalBytes
     * @param offset
     * @param len
     * @param replaceBytes
     * @return
     */
    public static byte[] bytesReplace(byte[] originalBytes, int offset, int len, byte[] replaceBytes) {
        byte[] newBytes = new byte[originalBytes.length + (replaceBytes.length - len)];
        System.arraycopy(originalBytes, 0, newBytes, 0, offset);
        System.arraycopy(replaceBytes, 0, newBytes, offset, replaceBytes.length);
        System.arraycopy(originalBytes, offset + len, newBytes, offset + replaceBytes.length, originalBytes.length - offset - len);
        return newBytes;
    }
}


