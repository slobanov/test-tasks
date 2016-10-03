package ru.amaicode.hh.school.island;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import ru.amaicode.hh.school.island.nodes.IslandNode;
import ru.amaicode.hh.school.island.nodes.IslandNodeFactory;
import ru.amaicode.hh.school.utils.FasterScanner;

import static java.util.stream.Stream.generate;
import static ru.amaicode.hh.school.utils.PrintUtils.deepToString;


public class IslandReader {
    private static final XLogger LOGGER = XLoggerFactory.getXLogger(IslandReader.class);

    private final FasterScanner scanner;
    private final IslandNodeFactory nodeFactory;

    public Island readNextIsland() {
        LOGGER.info("In IslandReader.readNextIsland");
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        IslandNode[][] islandNodes =
                generate(() -> readIslandLine(m)).
                limit(n).
                toArray(s -> new IslandNode[n][]);

        fillNeighbors(islandNodes, n, m);

        LOGGER.debug("Next Island: "
                + System.lineSeparator()
                + deepToString(islandNodes)
        );

        return new IslandImpl(islandNodes);
    }

    private void fillNeighbors(IslandNode[][] nodes, int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i != 0) {
                    nodes[i][j].addNeighbor(nodes[i-1][j]);
                }
                if (j != 0) {
                    nodes[i][j].addNeighbor(nodes[i][j-1]);
                }
                if (i != n-1) {
                    nodes[i][j].addNeighbor(nodes[i+1][j]);
                }
                if (j != m-1) {
                    nodes[i][j].addNeighbor(nodes[i][j+1]);
                }
            }
        }

    }
    private IslandNode[] readIslandLine(int size) {
        return generate(this::readNode).
                limit(size).
                toArray(s -> new IslandNode[size]);
    }

    private IslandNode readNode() {
        return nodeFactory.newNode(scanner.nextInt());
    }

    private IslandReader(FasterScanner scanner, IslandNodeFactory nodeFactory) {
        this.scanner = scanner;
        this.nodeFactory = nodeFactory;
    }

    public static IslandReader newIslandReader(
            FasterScanner scanner,
            IslandNodeFactory nodeFactory
    ) {
        return new IslandReader(scanner, nodeFactory);
    }
}
