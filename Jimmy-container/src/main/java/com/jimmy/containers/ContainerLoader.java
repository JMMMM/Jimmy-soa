package com.jimmy.containers;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.ServiceLoader;

public class ContainerLoader {
    public static final List<Container> containers = loadContainers();

    private static List<Container> loadContainers() {
        ServiceLoader<Container> serviceLoader = ServiceLoader.load(Container.class, ContainerStartup.class.getClassLoader());
        return ImmutableList.<Container>builder().addAll(serviceLoader).build();
    }
}
