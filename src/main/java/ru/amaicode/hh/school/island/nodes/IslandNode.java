package ru.amaicode.hh.school.island.nodes;

import java.util.stream.Stream;

public interface IslandNode {
    Stream<IslandNode> getNeighbors();
    void addNeighbor(IslandNode node);

    int getWaterLevel();
    int getWaterVolume();

    void pourWaterTo(int height);
    boolean drainWaterTo(int height);

    boolean isBorder();
    default boolean isInner() {
        return !isBorder();
    }
}
