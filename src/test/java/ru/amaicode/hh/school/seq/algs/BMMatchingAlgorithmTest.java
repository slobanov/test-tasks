package ru.amaicode.hh.school.seq.algs;

import org.junit.Test;
import ru.amaicode.hh.school.seq.InfiniteSequence;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;
import static ru.amaicode.hh.school.runners.TestRunner.sequenceTestRun;
import static ru.amaicode.hh.school.utils.PrintUtils.printlns;

public class BMMatchingAlgorithmTest {

    private void compareTest(String testName, int... testAns) throws FileNotFoundException {
        String ansString = printlns(testAns);
        assertEquals(testName + " answer should be: " + System.lineSeparator() + ansString,
                ansString,
                sequenceTestRun(
                        testName,
                        MatchingAlgorithm.bmMatchingAlgorithm(),
                        InfiniteSequence.newSequence()
                )
        );
    }

    @Test
    public void find() throws FileNotFoundException {
        compareTest("seq/test1", 6, 12);
        compareTest("seq/test2", 44445, 107162, 52652, 21515, 3228228);
    }

}