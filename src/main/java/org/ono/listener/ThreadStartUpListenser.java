package org.ono.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 启动时创建线程
 * @author leoALL
 *
 */
@Component
public class ThreadStartUpListenser implements ServletContextListener
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadStartUpListenser.class);
    private static WatchFilePathTask r = new WatchFilePathTask();

    /*
     * tomcat启动的时候创建一个线程
     * */
    @Override
    public void contextInitialized(ServletContextEvent paramServletContextEvent)
    {
        r.start();
        LOGGER.info("ImportFileFromFileTask is started!");
    }

    /*
     * tomcat关闭的时候销毁这个线程
     * */
    @Override
    public void contextDestroyed(ServletContextEvent paramServletContextEvent)
    {
        r.interrupt();
    }

}
