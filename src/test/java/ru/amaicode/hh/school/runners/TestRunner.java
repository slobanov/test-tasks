package ru.amaicode.hh.school.runners;

import ru.amaicode.hh.school.island.algs.FloodingAlgorithm;
import ru.amaicode.hh.school.seq.InfiniteSequence;
import ru.amaicode.hh.school.seq.algs.MatchingAlgorithm;

import java.io.*;

public enum TestRunner {
    ;

    public static String floodingTestRun(String fileName, FloodingAlgorithm algorithm) throws FileNotFoundException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try(IslandFloodingRunner runner = IslandFloodingRunner.newRunner(
                getTestData(fileName),
                outStream,
                algorithm
        )) {
            runner.run();
        }
        return outStream.toString();
    }

    public static String sequenceTestRun(
            String fileName,
            MatchingAlgorithm algorithm,
            InfiniteSequence sequence
    ) throws FileNotFoundException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try(SearchSubsequencesRunner runner = SearchSubsequencesRunner.newRunner(
                getTestData(fileName),
                outStream,
                algorithm,
                sequence
        )) {
            runner.run();
        }
        return outStream.toString();
    }

    private static InputStream getTestData(String fileName) throws FileNotFoundException {
        return new FileInputStream(new File(
                TestRunner.class.getClassLoader().getResource(fileName).getFile()
        ));
    }

}