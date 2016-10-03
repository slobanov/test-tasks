package ru.amaicode.hh.school.runners;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import ru.amaicode.hh.school.island.Island;
import ru.amaicode.hh.school.island.IslandReader;
import ru.amaicode.hh.school.island.algs.FloodingAlgorithm;
import ru.amaicode.hh.school.island.nodes.IslandNodeFactory;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.stream.IntStream;

import static java.util.stream.IntStream.generate;
import static ru.amaicode.hh.school.island.IslandReader.newIslandReader;


final class IslandFloodingRunner extends AbstractRunner {

    private static final XLogger LOGGER = XLoggerFactory.getXLogger(IslandFloodingRunner.class);

    private final FloodingAlgorithm algorithm;

    public static void main(String args[]) {
        try (IslandFloodingRunner runner = IslandFloodingRunner.newRunner(
                System.in,
                System.out,
                FloodingAlgorithm.spillingAlgorithm()
        )) {
            runner.run();
        }
    }

    private IslandFloodingRunner(InputStream in, OutputStream out, FloodingAlgorithm algorithm) {
        super(in, out);
        this.algorithm = algorithm;
    }

    static IslandFloodingRunner newRunner(
            InputStream in,
            OutputStream out,
            FloodingAlgorithm algorithm
    ) {
        return new IslandFloodingRunner(in, out, algorithm);
    }

    @Override
    void run() {
        LOGGER.info("In IslandFloodingRunner.run");
        IslandReader islandReader = newIslandReader(
                scanner,
                new IslandNodeFactory()
        );

        int n = scanner.nextInt();
        IntStream results =
                generate(() -> processNextIsland(islandReader.readNextIsland())).
                limit(n);

        writer.writelnIntSteam(results);
    }

    private int processNextIsland(Island island) {
        LOGGER.info("In IslandFloodingRunner.processNextIsland");
        LOGGER.debug("Island before:" + System.lineSeparator() + "{}", island);

        algorithm.flood(island);
        int result = island.getWaterRetention();

        LOGGER.debug("Island after:" + System.lineSeparator() + "{}", island);
        LOGGER.debug("Water retention = {}", result);

        return result;
    }
}
