package ru.amaicode.hh.school.island.nodes;

import org.junit.Test;

import static java.util.stream.Collectors.toList;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IslandNodeImplTest {

    @Test
    public void addNeighbor() throws Exception {
        IslandNode n1 = new IslandNodeImpl(1);

        assertFalse("n1 should not have neighbors",
                n1.getNeighbors().findAny().isPresent());

        IslandNode n2 = new IslandNodeImpl(2);
        n1.addNeighbor(n2);

        assertTrue("n1 should have 1 neighbor after n1.addNeighbor(n2)",
                n1.getNeighbors().collect(toList()).size() == 1);

        assertEquals("n1's neighbor should n2",
                n2,
                n1.getNeighbors().collect(toList()).get(0)
        );
    }

    @Test
    public void pourWaterTo() throws Exception {
        IslandNode n1 = new IslandNodeImpl(10);

        n1.pourWaterTo(20);
        assertEquals("n1 water level after pourWaterTo(10) should be 20",
                20,
                n1.getWaterLevel()
        );

        n1.pourWaterTo(-1);
        assertEquals("n1 water level after pourWaterTo(-1) should be 20",
                20,
                n1.getWaterLevel()
        );

        n1.pourWaterTo(21);
        assertEquals("n1 water volume after pourWaterTo(21) should be 11",
                11,
                n1.getWaterVolume()
        );

    }

    @Test
    public void drainWaterTo() throws Exception {
        IslandNode n1 = new IslandNodeImpl(10);

        assertFalse("Can't drain water to 15 from n1",
                n1.drainWaterTo(15));
        assertEquals("n1's water level should be 10",
                10,
                n1.getWaterLevel()
        );

        assertFalse("Can't drain water to 5 from n1",
                n1.drainWaterTo(5));

        n1.pourWaterTo(15);
        assertTrue("Can drain water to 7 after pour from n1",
                n1.drainWaterTo(7));
        assertEquals("n1's water level should be 10",
                10,
                n1.getWaterLevel()
        );
    }

}