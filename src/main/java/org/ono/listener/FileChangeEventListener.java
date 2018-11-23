package org.ono.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

/**
 * Created by ono on 2018/11/21.
 */
public class FileChangeEventListener implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileChangeEventListener.class);

    private WatchService watchService;
    private Path path;
    private String dirPath;
    private boolean notDone = true;

    public FileChangeEventListener(String dirPath) {
        this.dirPath = dirPath;
    }

    @Override
    public void run() {
        System.out.print("watch...");
        while (notDone) {
            try {
                path = Paths.get(dirPath);
                try {
                    watchService = FileSystems.getDefault().newWatchService(); // 创建watchService
                    path.register(watchService,StandardWatchEventKinds.ENTRY_MODIFY); // 注册需要监控的事件,ENTRY_CREATE 文件创建,ENTRY_MODIFY 文件修改,ENTRY_MODIFY 文件删除
                } catch (IOException e) {
                    e.printStackTrace();
                }
                WatchKey watchKey = watchService.take(); // 此处将处于等待状态,等待检测到文件夹下得文件发生改变,返回WatchKey对象
                if (watchKey != null) {
                    List<WatchEvent<?>> events = watchKey.pollEvents(); // 获取所有得事件
                    for (WatchEvent event : events) {
                        WatchEvent.Kind<?> kind = event.kind();
                        if (kind != StandardWatchEventKinds.ENTRY_MODIFY) {
                            //
                            continue;
                        }
                        WatchEvent<Path> ev = event;
                        Path path = ev.context();
                        System.out.println("modify " + path.getFileName());
                    }
                    if (!watchKey.reset()) {
                        //已经关闭了进程
                        System.out.println("exit watch server");
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
