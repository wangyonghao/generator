package org.joy.util;


import org.apache.commons.lang.StringUtils;

/**
 * @author wyh
 * @version 2017/12/19.
 */
public class CaseUtil {
    private static final char UNDERLINE = '_';

    public static String toUnderLine(String text){
        if (text == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            boolean nextUpperCase = true;

            if (i < (text.length() - 1)) {
                nextUpperCase = Character.isUpperCase(text.charAt(i + 1));
            }

            if ((i >= 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    if (i > 0) {
                        sb.append(UNDERLINE);
                    }
                }
                upperCase = true;
            } else {
                upperCase = false;
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }

    public static String camelCase(String text){
        if (text == null) {
            return null;
        }
        text = text.toLowerCase();
        StringBuilder sb = new StringBuilder(text.length());
        boolean upperCase = false;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (c == UNDERLINE) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * 首字母大写
     * @param str
     * @return
     */
    public static String capitalize(String str) {
        int strLen;
        return str != null && (strLen = str.length()) != 0 ? (new StringBuffer(strLen)).append(Character.toTitleCase(str.charAt(0))).append(str.substring(1)).toString() : str;
    }

    /**
     * 大小写互换
     * @param str
     * @return
     */
    public static String swapCase(String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            StringBuffer buffer = new StringBuffer(strLen);
            for(int i = 0; i < strLen; ++i) {
                char ch = str.charAt(i);
                if (Character.isUpperCase(ch)) {
                    ch = Character.toLowerCase(ch);
                } else if (Character.isTitleCase(ch)) {
                    ch = Character.toLowerCase(ch);
                } else if (Character.isLowerCase(ch)) {
                    ch = Character.toUpperCase(ch);
                }
                buffer.append(ch);
            }
            return buffer.toString();
        } else {
            return str;
        }
    }
}
