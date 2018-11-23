package org.ono.test;

import org.ono.services.impl.Files;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by ono on 2018/11/21.
 */
public class TestDemo {
    public static void main(String[] args) throws IOException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Files files = (Files) ac.getBean("files");
        System.out.println(files.toString());
    }
}
