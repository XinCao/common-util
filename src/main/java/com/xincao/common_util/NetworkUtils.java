package com.xincao.common_util;

/**
 * @author KID, -Nemesiss-
 */
public class NetworkUtils {

    /**
     * check if IP address match pattern
     *
     * @param pattern *.*.*.* , 192.168.1.0-127 ,
     *
     * @param address - 192.168.1.1<BR>
     * <code>address = 10.2.88.12  pattern = *.*.*.*   result: true<BR>
     * address = 10.2.88.12 pattern = * result: true<BR>
     * address = 10.2.88.12 pattern = 10.2.88.12-13 result: true<BR>
     * address = 10.2.88.12 pattern = 10.2.88.13-125 result: false<BR></code>
     * @return true if address match pattern
     */
    public static boolean checkIPMatching(String pattern, String address) {
        if (pattern.equals("*.*.*.*") || pattern.equals("*")) {
            return true;
        }
        String[] mask = pattern.split("\\.");
        String[] ip_address = address.split("\\.");
        for (int i = 0; i < mask.length; i++) {
            if (mask[i].equals("*") || mask[i].equals(ip_address[i])) {
                continue;
            } else if (mask[i].contains("-")) {
                String[] pairS = mask[i].split("-");
                byte min = Byte.parseByte(pairS[0]);
                byte max = Byte.parseByte(pairS[1]);
                byte ip = Byte.parseByte(ip_address[i]);
                if (ip < min || ip > max) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String ...args) {
        boolean result = checkIPMatching("192.168.1.0-127", "192.168.1.1");
        System.out.println(result);
    }
}