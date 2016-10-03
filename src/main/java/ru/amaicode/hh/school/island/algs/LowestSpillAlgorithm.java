package ru.amaicode.hh.school.island.algs;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import ru.amaicode.hh.school.island.Island;
import ru.amaicode.hh.school.island.nodes.IslandNode;

import java.util.PriorityQueue;
import java.util.Queue;

import static java.util.stream.Collectors.toList;
import static ru.amaicode.hh.school.island.algs.FloodingAlgorithmUtils.diveUnderWater;

final class LowestSpillAlgorithm implements FloodingAlgorithm {
    private static final XLogger LOGGER = XLoggerFactory.getXLogger(LowestSpillAlgorithm.class);

    @Override
    public void flood(Island island) {
        LOGGER.info("In LowestSpillAlgorithm.flood");

        diveUnderWater(island);
        LOGGER.debug("After dive: " + System.lineSeparator() + "{}", island);

        Queue<IslandNode> spillingQueue = new PriorityQueue<>(
                (n1, n2) -> Integer.compare(n1.getWaterLevel(), n2.getWaterLevel())
        );

        spillingQueue.addAll(island.getIslandNodes().
                filter(IslandNode::isBorder).
                collect(toList())
        );
        while(!spillingQueue.isEmpty()) {
            IslandNode node = spillingQueue.poll();
            LOGGER.debug("processing node ({})", node);
            node.getNeighbors().filter(IslandNode::isInner).forEach(
                    n-> offerSpilled(spillingQueue, n, node)
            );
            LOGGER.debug("Next iter:" + System.lineSeparator() + "{}", island);
        }
    }

    private void offerSpilled(Queue<IslandNode> queue, IslandNode from, IslandNode to) {
        boolean spilled = spill(from, to);
        if (spilled) {
            queue.offer(from);
        }
    }

    private boolean spill(IslandNode from, IslandNode to) {
        return from.drainWaterTo(to.getWaterLevel());
    }

}
