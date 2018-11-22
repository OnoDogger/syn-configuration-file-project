package org.ono.utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ono on 2018/11/22.
 */
public class FileUtils {

    public static List<Path> convertString2Path(List<String> fileUrls){
        List<Path> paths = new ArrayList<>(fileUrls.size());
        for (String fileUrl: fileUrls){
            paths.add(Paths.get(fileUrl));
        }
        return paths;
    }
}
