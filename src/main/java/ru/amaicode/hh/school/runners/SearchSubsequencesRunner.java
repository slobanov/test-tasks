package ru.amaicode.hh.school.runners;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import ru.amaicode.hh.school.seq.InfiniteSequence;
import ru.amaicode.hh.school.seq.algs.MatchingAlgorithm;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;

import static ru.amaicode.hh.school.utils.StreamUtils.mapToInt;

final class SearchSubsequencesRunner extends AbstractRunner {

    private static final XLogger LOGGER = XLoggerFactory.getXLogger(SearchSubsequencesRunner.class);

    private final MatchingAlgorithm algorithm;
    private final InfiniteSequence sequence;

    public static void main(String args[]) {
        try(SearchSubsequencesRunner runner = newRunner(
                System.in,
                System.out,
                MatchingAlgorithm.bmMatchingAlgorithm(),
                InfiniteSequence.newSequence()
        )) {
            runner.run();
        }
    }

    static SearchSubsequencesRunner newRunner(
            InputStream in,
            OutputStream out,
            MatchingAlgorithm algorithm,
            InfiniteSequence sequence
    ) {
        return new SearchSubsequencesRunner(in, out, algorithm, sequence);
    }

    private SearchSubsequencesRunner(
            InputStream in,
            OutputStream out,
            MatchingAlgorithm algorithm,
            InfiniteSequence sequence) {
        super(in, out);
        this.algorithm = algorithm;
        this.sequence = sequence;
    }

    @Override
    void run() {
        LOGGER.info("In SearchSubsequencesRunner.run");

        String line;
        boolean isFirstLine = true;
        while ((line = scanner.nextLine()) != null) {
            LOGGER.debug("Processing line: {}", line);

            if (!isFirstLine) {
                writer.newLine();
            } else {
                isFirstLine = false;
            }

            BigInteger firstOccurrence = findFirstOccurrence(line);
            writer.writeBigInt(firstOccurrence);


            LOGGER.debug("First Occurrence: ", firstOccurrence);
        }
    }

    private BigInteger findFirstOccurrence(String subSequence) {
        return algorithm.findFirstMatch(sequence, mapToInt(subSequence.toCharArray()));
    }

}
