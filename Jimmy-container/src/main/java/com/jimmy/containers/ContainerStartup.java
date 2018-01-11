package com.jimmy.containers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ServiceLoader;

public class ContainerStartup {
    private static Logger logger = LoggerFactory.getLogger(ContainerStartup.class);

    public static void startup() {
        logger.info("start to load containers .... ");
        //获取Container
        ServiceLoader<Container> containersServiceLoader = ServiceLoader.load(Container.class);
        containersServiceLoader.forEach(Container::start);
    }
}
