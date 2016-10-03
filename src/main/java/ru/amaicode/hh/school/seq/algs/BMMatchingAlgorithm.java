package ru.amaicode.hh.school.seq.algs;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import ru.amaicode.hh.school.seq.InfiniteSequence;

import java.math.BigInteger;
import java.util.Arrays;

import static java.util.stream.IntStream.generate;
import static java.util.stream.IntStream.range;

final class BMMatchingAlgorithm implements MatchingAlgorithm {
    private static final XLogger LOGGER = XLoggerFactory.getXLogger(BMMatchingAlgorithm.class);

    private static final int ALPHABET_SIZE = "0123456789".length();

    private static int[] computeStopSymbols(int[] pattern) {
        int pl = pattern.length;
        int[] stopSymbols = generate(() -> pl).
                limit(ALPHABET_SIZE).
                toArray();

        range(0, pl-1).forEach(i -> stopSymbols[pattern[i]] = pl-1-i);

        return stopSymbols;
    }

    private static int[] computeSuffixes(int[] pattern) {
        int pl = pattern.length;

        int[] suffices = new int[pl];
        suffices[pl-1] = pl;

        int g = pl-1;
        int f = 0;

        for(int i = pl-2; i >= 0; i--) {
            if (i > g && suffices[i+pl-1-f] < i - g) {
                suffices[i] = suffices[i+pl-1-f];
            } else {
                if (i < g) {
                    g = i;
                }
                f = i;
                while (g >= 0 && pattern[g] == pattern[g+pl-1-f]) {
                    g--;
                }
                suffices[i] = f-g;
            }
        }
        return suffices;
    }

    private static int[] computeSuffixShifts(int[] pattern) {
        int pl = pattern.length;
        int[] suffices = computeSuffixes(pattern);

        int[] res = new int[pl];
        for (int i = 0;i < pl; i++) {
            res[i] = pl;
        }
        int j = 0;
        for (int i = pl-1; i >= 0; i--) {
            if (suffices[i] == i+1) {
                while (j < pl-1-i) {
                    if (res[j] == pl) {
                        res[j] = pl-i-1;
                    }
                    j++;
                }
            }
        }
        for(int i = 0; i < pl-1; i++) {
            res[pl-1-suffices[i]] = pl-i-1;
        }

        return res;
    }

    public BigInteger findFirstMatch(InfiniteSequence sequence, int[] pattern) {
        LOGGER.info("In findFirstMatch for pattern {}", pattern);

        int pl = pattern.length;
        if (pl == 0) {
            return BigInteger.ZERO;
        }
        int stopSymbols[] = computeStopSymbols(pattern);
        int suffixShifts[] = computeSuffixShifts(pattern);

        LOGGER.debug("stopSymbols: {}", Arrays.toString(stopSymbols));
        LOGGER.debug("suffixShifts: {}", Arrays.toString(suffixShifts));

        BigInteger pos = BigInteger.valueOf(0);
        BigInteger firstOccPos = BigInteger.valueOf(-1);
        boolean found = false;

        while (!found) {
            int i = pattern.length-1;
            while (i >= 0 && (sequence.get(pos.add(BigInteger.valueOf(i))) == pattern[i])) {
                i--;
            }
            if (i < 0) {
                found = true;
                firstOccPos = pos;
            } else {
                int shift = Math.max(
                        stopSymbols[sequence.get(pos.add(BigInteger.valueOf(i)))]-pl+1+i,
                        suffixShifts[i]
                );
                LOGGER.debug("Shift: {}", shift);
                pos = pos.add(BigInteger.valueOf(shift));
            }
        }

        return firstOccPos;
    }
}
