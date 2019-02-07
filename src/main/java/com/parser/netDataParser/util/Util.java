package com.parser.netDataParser.util;

public class Util {

    public static boolean isValidFileFormat(String fileName){
        int index = fileName.lastIndexOf(".");
        String endPart = fileName.substring(index + 1);
        return index != -1 && FileFormat.get(endPart.toUpperCase()).isPresent();
    }
}
