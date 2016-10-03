package ru.amaicode.hh.school.island.nodes;

import org.junit.Test;

import static java.util.stream.Collectors.toList;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.amaicode.hh.school.island.nodes.IslandNodeFactory.*;

public class IslandNodeFactoryTest {
    private static final IslandNodeFactory factory = new IslandNodeFactory();

    @Test
    public void newNode() throws Exception {
        int level = 10;
        IslandNode n1 = factory.newNode(level);

        assertEquals("n1 water level should be " + level,
                level,
                n1.getWaterLevel()
        );

        assertEquals("n1 water volume should be 0",
                0,
                n1.getWaterVolume());

        assertTrue("n1 neighbors should be empty",
                n1.getNeighbors().collect(toList()).isEmpty()
        );

        IslandNode n2 = factory.newNode(level+1);
        n1.addNeighbor(n2);
        assertTrue("n1 should have 1 neighbor after adding n2",
                n1.getNeighbors().collect(toList()).size() == 1);

        assertEquals("n1 neighbor should be n2",
                n2,
                n1.getNeighbors().findAny().get()
        );
    }

    @Test
    public void newBoundariesNodes() {
        IslandNode nMin = factory.newNode(MIN_HEIGHT);
        assertEquals("nMin should be " + MIN_HEIGHT,
                MIN_HEIGHT,
                nMin.getWaterLevel()
        );

        IslandNode nMax = factory.newNode(MAX_HEIGHT);
        assertEquals("nMax should be " + MAX_HEIGHT,
                MAX_HEIGHT,
                nMax.getWaterLevel()
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void newUnderGroundNode() {
        factory.newNode(MIN_HEIGHT-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newCloudNode() {
        factory.newNode(MAX_HEIGHT+1);
    }
}