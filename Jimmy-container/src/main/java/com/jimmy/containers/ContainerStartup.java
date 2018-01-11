package com.jimmy.containers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ServiceLoader;

public class ContainerStartup {

    public static void startup() {
        //获取Container
        ServiceLoader<Container> containersServiceLoader = ServiceLoader.load(Container.class, ContainerStartup.class.getClassLoader());
        for (Container container:containersServiceLoader){
            System.out.println("cao");
        }
        containersServiceLoader.forEach(Container::start);
    }

}
