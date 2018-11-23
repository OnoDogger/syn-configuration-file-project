package org.ono.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * 修改文件入库线程,由WatchFilePathTask启动
 * @author leoALL
 *
 */
public class ImportFileFromFileTask extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImportFileFromFileTask.class);
    private String fileName;

    @Override
    public void run() {
        File file = new File(fileName);

        if (file.exists() && file.isFile()) {
            InputStreamReader read;

            try {
                read = new InputStreamReader(new FileInputStream(file), "UTF-8");

                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                int count = 0;
                Boolean f = false;

                while ((lineTxt = bufferedReader.readLine()) != null) {
                    if ((null == lineTxt) || "".equals(lineTxt)) {
                        continue;
                    }

                    if (lineTxt.startsWith("'")) {
                        lineTxt = lineTxt.substring(1, lineTxt.length());
                    }

                    //解析分隔符为', '
                    String[] lines = lineTxt.split("', '");
                    int length = lines.length;

                    if (length < 2) {
                        continue;
                    }

                    if (f) {
                        count++;
                    }
                }

                //注意：要关闭流
                read.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }

            File newFile = new File(file.getPath() +
                    System.currentTimeMillis() + ".complate");
            file.renameTo(newFile);
        } else {
            LOGGER.error(fileName + " file is not exists");
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
