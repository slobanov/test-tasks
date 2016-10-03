package ru.amaicode.hh.school.island.nodes;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

public class IslandNodeFactory {
    private static final XLogger LOGGER = XLoggerFactory.getXLogger(IslandNodeFactory.class);
    public static final int MAX_HEIGHT = 1000;
    public static final int MIN_HEIGHT = 1;

    public IslandNode newNode(int height) {
        if (height < MIN_HEIGHT || height > MAX_HEIGHT) {
            String errorString = "Can't create island node with height = " + height;
            LOGGER.error(errorString);
            throw new IllegalArgumentException(errorString);
        }
        LOGGER.debug("New node with height ({})", height);
        return new IslandNodeImpl(height);
    }
}
