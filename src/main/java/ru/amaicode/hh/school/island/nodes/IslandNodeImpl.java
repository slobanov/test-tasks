package ru.amaicode.hh.school.island.nodes;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static ru.amaicode.hh.school.island.nodes.IslandNodeFactory.MAX_HEIGHT;
import static ru.amaicode.hh.school.island.nodes.IslandNodeFactory.MIN_HEIGHT;

final class IslandNodeImpl implements IslandNode {
    private static final XLogger LOGGER = XLoggerFactory.getXLogger(IslandNodeImpl.class);
    private static final int MAX_NEIGHBORS = 4;

    private final int baseHeight;
    private int curHeight;
    private final Set<IslandNode> neighbors = new HashSet<>();

    IslandNodeImpl(int baseHeight) {
        if (baseHeight > MAX_HEIGHT || baseHeight < MIN_HEIGHT) {
            String errorString =
                    "Can't create node with height =" + baseHeight;
            LOGGER.error(errorString);
            throw new IllegalStateException(errorString);
        }
        this.curHeight = baseHeight;
        this.baseHeight = baseHeight;
    }

    @Override
    public Stream<IslandNode> getNeighbors() {
        return neighbors.stream();
    }

    @Override
    public void addNeighbor(IslandNode node) {
        if (neighbors.size() < MAX_NEIGHBORS || neighbors.contains(node)) {
           neighbors.add(node);
        } else {
            String errorString =
                    "Can't add more neighbors; MAX_NEIGHBORS=" + MAX_NEIGHBORS;
            LOGGER.error(errorString);
            throw new IllegalStateException(errorString);
        }
    }

    @Override
    public int getWaterLevel() {
        return curHeight;
    }

    @Override
    public int getWaterVolume() {
        return curHeight-baseHeight;
    }

    @Override
    public void pourWaterTo(int height) {
        curHeight = Math.max(curHeight, height);
    }

    @Override
    public boolean drainWaterTo(int height) {
        if (height >= curHeight) {
            return false;
        } else {
            boolean hadWater = getWaterVolume() != 0;
            curHeight = Math.max(baseHeight, Math.min(curHeight, height));
            return hadWater;
        }
    }

    @Override
    public boolean isBorder() {
        return neighbors.size() < MAX_NEIGHBORS;
    }

    @Override
    public String toString() {
        return Integer.toString(curHeight);
    }
}
