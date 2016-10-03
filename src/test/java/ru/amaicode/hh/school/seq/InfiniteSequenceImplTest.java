package ru.amaicode.hh.school.seq;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class InfiniteSequenceImplTest {
    private InfiniteSequence sequence;

    @Before
    public void init() {
        sequence = new InfiniteSequenceImpl();
    }
    @Test
    public void get() throws Exception {
        checkDig(7, 7);
        checkDig(11, 0);
        checkDig(99, 4);
        checkDig(120, 6);
        checkDig(50001, 2);
    }

    private void checkDig(int pos, int res) {
        assertEquals(pos+"th digit is "+res, res, sequence.get(BigInteger.valueOf(pos)));
    }

}