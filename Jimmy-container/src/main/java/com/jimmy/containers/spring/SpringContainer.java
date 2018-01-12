package com.jimmy.containers.spring;

import com.jimmy.containers.Container;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContainer implements Container {
    private static final String SPRING_CONFIG_PATH = "/applicationContext.xml";
    public ClassPathXmlApplicationContext applicationContext = null;

    @Override
    public void start() {
        applicationContext = new ClassPathXmlApplicationContext(SPRING_CONFIG_PATH);
        applicationContext.getBeansWithAnnotation(null);
        applicationContext.start();
    }

    @Override
    public void stop() {
        applicationContext.stop();
    }

    public static void main(String[] args) {
        new SpringContainer().start();
    }
}
