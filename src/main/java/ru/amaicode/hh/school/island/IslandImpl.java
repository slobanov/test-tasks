package ru.amaicode.hh.school.island;

import ru.amaicode.hh.school.island.nodes.IslandNode;

import java.util.stream.Stream;

import static ru.amaicode.hh.school.utils.PrintUtils.deepToString;
import static ru.amaicode.hh.school.utils.StreamUtils.flatten2d;

final class IslandImpl implements Island {
    private final IslandNode[][] islandNodes;

    IslandImpl(IslandNode[][] islandNodes) {
        this.islandNodes = islandNodes;
    }

    @Override
    public Stream<IslandNode> getIslandNodes() {
        return flatten2d(islandNodes);
    }

    @Override
    public String toString() {
        return deepToString(islandNodes);
    }
}
