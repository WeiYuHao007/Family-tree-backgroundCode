package com.orange.familyTree.pojo.util;

import java.io.File;
import java.util.UUID;

public class FileUtil {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
