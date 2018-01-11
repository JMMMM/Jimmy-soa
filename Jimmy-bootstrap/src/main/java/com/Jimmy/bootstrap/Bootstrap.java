package com.Jimmy.bootstrap;

import com.jimmy.containers.Container;
import com.jimmy.containers.ContainerLoader;
import com.jimmy.containers.ContainerStartup;

public class Bootstrap {
    public static void main(String[] args) {
        ContainerStartup.startup();
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> ContainerLoader.containers.forEach(Container::stop))
        );
    }
}
