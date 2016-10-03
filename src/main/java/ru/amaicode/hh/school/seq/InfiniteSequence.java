package ru.amaicode.hh.school.seq;

import java.math.BigInteger;

public interface InfiniteSequence {
    static InfiniteSequence newSequence() {
        return new InfiniteSequenceImpl();
    }

    int get(BigInteger i);
}
