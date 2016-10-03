package ru.amaicode.hh.school.island;

import ru.amaicode.hh.school.island.nodes.IslandNode;

import java.util.stream.Stream;

public interface Island {
    Stream<IslandNode> getIslandNodes();

    default int getWaterRetention() {
        return getIslandNodes().
                mapToInt(IslandNode::getWaterVolume).
                sum();
    }
}
