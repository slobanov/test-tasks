package ru.amaicode.hh.school.seq.algs;

import ru.amaicode.hh.school.seq.InfiniteSequence;

import java.math.BigInteger;

public interface MatchingAlgorithm {
    static MatchingAlgorithm bmMatchingAlgorithm() {
        return new BMMatchingAlgorithm();
    }
    BigInteger findFirstMatch(InfiniteSequence sequence, int[] pattern);
}
