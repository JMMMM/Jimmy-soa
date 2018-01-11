package com.jimmy.containers.logback;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.util.StatusPrinter;
import com.jimmy.containers.Container;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.InputStream;

public class LogbackContainer implements Container {
    @Override
    public void start() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream logbackCnfgStream = new BufferedInputStream(classLoader.getResourceAsStream("logback.xml"))) {
            LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(lc);
            lc.reset();
            configurator.doConfigure(logbackCnfgStream);

            StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
        } catch (Exception e) {
            System.out.println("LogbackContainer failed, ignoring ..." + e.getMessage());
        }
    }

    @Override
    public void stop() {

    }
}
