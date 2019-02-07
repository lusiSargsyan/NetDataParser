package com.parser.netDataParser.util;

import java.util.Arrays;
import java.util.Optional;

public enum FileFormat {
    CSV,
    XLS;

    public static Optional<FileFormat> get(String name){
         return Arrays.stream(FileFormat.values())
                 .filter(x->x.name().equals(name))
                 .findFirst();
    }

}
