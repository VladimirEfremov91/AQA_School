package org.lesson14;

public class XMLUtils {

    public static String createEmptyElement(String tagName) {
        if (tagName == null || tagName.isEmpty()) {
            return "<invalid/>";
        }

        return "<" + tagName + "></" + tagName + ">";
    }
}