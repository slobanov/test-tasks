package ru.amaicode.hh.school.island.algs;

import ru.amaicode.hh.school.island.Island;
import ru.amaicode.hh.school.island.nodes.IslandNode;

import static ru.amaicode.hh.school.island.nodes.IslandNodeFactory.MAX_HEIGHT;

enum FloodingAlgorithmUtils {
    ;

    static void diveUnderWater(Island island) {
        island.getIslandNodes().
                filter(IslandNode::isInner).
                forEach(n -> n.pourWaterTo(MAX_HEIGHT+1));
    }
}
