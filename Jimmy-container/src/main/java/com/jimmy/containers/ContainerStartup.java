package com.jimmy.containers;

import java.util.ServiceLoader;

public class ContainerStartup {
    public static void startup() {
        //获取Container
        ServiceLoader<Container> containersServiceLoader = ServiceLoader.load(Container.class);
        containersServiceLoader.forEach(Container::start);
    }
}
