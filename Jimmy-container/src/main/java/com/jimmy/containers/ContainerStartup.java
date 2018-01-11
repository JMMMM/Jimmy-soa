package com.jimmy.containers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ServiceLoader;

public class ContainerStartup {

    public static void startup() {
        ContainerLoader.loadContainers().forEach(Container::start);
    }

}
