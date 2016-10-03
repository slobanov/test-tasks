package ru.amaicode.hh.school.island.algs;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import ru.amaicode.hh.school.island.Island;
import ru.amaicode.hh.school.island.nodes.IslandNode;

import java.util.List;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static ru.amaicode.hh.school.island.algs.FloodingAlgorithmUtils.diveUnderWater;
import static ru.amaicode.hh.school.utils.StreamUtils.exists;

class RelaxingAlgorithm implements FloodingAlgorithm {
    private static final XLogger LOGGER = XLoggerFactory.getXLogger(RelaxingAlgorithm.class);

    @Override
    public void flood(Island island) {
        LOGGER.info("In RelaxingAlgorithm.flood");

        diveUnderWater(island);
        LOGGER.debug("After dive: " + System.lineSeparator() + "{}", island);

        boolean wasRelaxed;
        do {
            List<Boolean> relaxed = island.getIslandNodes().
                    filter(IslandNode::isInner).
                    map(this::relax).collect(toList());
            wasRelaxed = exists(identity(), relaxed);
            LOGGER.debug("Next iter:" + System.lineSeparator() + "{}", island);
        } while (wasRelaxed);

    }

    private Boolean relax(IslandNode node) {
        int newLevel = node.getNeighbors().
                mapToInt(IslandNode::getWaterLevel).
                min().
                orElse(node.getWaterLevel());
        return node.drainWaterTo(newLevel);
    }
}
