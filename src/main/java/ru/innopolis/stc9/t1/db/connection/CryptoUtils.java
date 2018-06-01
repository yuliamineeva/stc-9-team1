package ru.innopolis.stc9.t1.db.connection;

import org.apache.log4j.Logger;

public class CryptoUtils {
    private final static Logger logger = Logger.getLogger(CryptoUtils.class);

    public static byte[] computeHash(String stringToHash) throws Exception {
        java.security.MessageDigest messageDigest = null;
        messageDigest = java.security.MessageDigest.getInstance("SHA-256");
        messageDigest.reset();
        messageDigest.update(stringToHash.getBytes());
        return  messageDigest.digest();
    }

    public static String byteArrayToHexString(byte[] b){
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++){
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString();
    }

    public static String computeHashPassword(String passwordToHash) {
        String hashPassword = null;
        try {
            hashPassword = CryptoUtils.byteArrayToHexString(CryptoUtils.computeHash(passwordToHash));
        } catch (Exception e) {
            logger.error("Error trying to get hashpassword", e);
        }
        return hashPassword;
    }
}