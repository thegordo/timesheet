package timeSheet.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * User: John Lawrence
 * Date: 12/21/10
 * Time: 8:50 PM
 */
public class SHA {
    private byte[] sha1hash;

    public SHA(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(text.getBytes(), 0, text.length());
            sha1hash = md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        for (byte aSha1hash : sha1hash) {
            int halfByte = (aSha1hash >>> 4) & 0x0F;
            int two_halves = 0;
            do {
                if ((0 <= halfByte) && (halfByte <= 9))
                    buf.append((char) ('0' + halfByte));
                else
                    buf.append((char) ('a' + (halfByte - 10)));
                halfByte = aSha1hash & 0x0F;
            } while (two_halves++ < 1);
        }
        return buf.toString();
    }
}
