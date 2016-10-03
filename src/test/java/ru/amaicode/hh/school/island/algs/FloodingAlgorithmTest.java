package ru.amaicode.hh.school.island.algs;

import org.junit.Test;

import java.io.FileNotFoundException;

import static junit.framework.TestCase.assertEquals;
import static ru.amaicode.hh.school.runners.TestRunner.floodingTestRun;
import static ru.amaicode.hh.school.utils.PrintUtils.printlns;

abstract class FloodingAlgorithmTest {

    private void compareTest(String testName, int... testAns) throws FileNotFoundException {
        String ansString = printlns(testAns);
        assertEquals(testName + " answer should be: " + System.lineSeparator() + ansString,
                ansString,
                floodingTestRun(testName, getAlgorithm()));
    }

    @Test
    public void flood() throws FileNotFoundException {
        compareTest("island/test1", 2, 7, 0); // Тест из примера к заданию

        /* Тесты с wikipedia
            https://en.wikipedia.org/wiki/Water_retention_on_mathematical_surfaces
         */
        compareTest("island/test2", 136, 365, 405, 417, 418);
        compareTest("island/test3", 797, 1408, 0, 219822);

        compareTest("island/test4", 0, 2, 0, 22, 19600); // "Самодельные" тесты
    }

    abstract FloodingAlgorithm getAlgorithm();
}
