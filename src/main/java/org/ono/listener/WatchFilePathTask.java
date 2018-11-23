package org.ono.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

/**
 * 指定目录文件变化监控类
 * @author leoALL
 *
 */
public class WatchFilePathTask extends Thread
{
    private static final Logger LOGGER = LoggerFactory.getLogger(WatchFilePathTask.class);
    private static final String filePath = "D:\\test1";

    private WatchService watchService;

    private Path path;

    @Override
    public void run()
    {
        try
        {
            //获取监控服务
            watchService = FileSystems.getDefault().newWatchService();
            LOGGER.debug("获取监控服务"+watchService);
            path = FileSystems.getDefault().getPath(filePath);
            LOGGER.debug("@@@:Path:"+path);
            // 注册监控服务，监控新增事件
            path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
            while (true) {
            WatchKey key = watchService.take();
            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();
                if (kind != StandardWatchEventKinds.ENTRY_MODIFY) {
                    continue;
                }

                //获取目录下修改的文件名
                String fileName = event.context().toString();

                String filePath = path.toFile().getAbsolutePath()+File.separator+fileName;
                LOGGER.info("import filePath:"+filePath);

                //启动线程将文件数据导入数据库
//                ImportFileFromFileTask task = (ImportFileFromFileTask) .getApplicationContext().getBean("importFileFromFileTask");//new ImportFileFromFileTask(filePath);
//                task.setFileName(filePath);
//                task.start();
//                LOGGER.debug("启动线程将文件数据导入数据库"+task);
            }
            key.reset();
        }
        } catch (IOException e)
        {
            LOGGER.error(e.getMessage(),e);
        } catch (InterruptedException e)
        {
            LOGGER.error(e.getMessage(),e);
        }
    }
}
