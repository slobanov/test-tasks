package ru.amaicode.hh.school.seq;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;

class InfiniteSequenceImpl implements InfiniteSequence {

    private static final XLogger LOGGER = XLoggerFactory.getXLogger(InfiniteSequenceImpl.class);

    private static final int CACHE_DEPTH = 1_000_000;
    private static final Map<BigInteger, Integer> CACHE =
            new LinkedHashMap<BigInteger, Integer>(
                    16,
                    0.75f,
                    true
            ) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<BigInteger, Integer> eldest) {
                    return size() > CACHE_DEPTH;
                }
            };

    @Override
    public int get(BigInteger i) {
        LOGGER.debug("Getting char for {}", i);
        if (!CACHE.containsKey(i)) {
            CACHE.put(i, computeInt(i));
        }
        int digit = CACHE.get(i);
        LOGGER.debug("Got {}", digit);
        return digit;
    }

    private static int computeInt(BigInteger i) {
        LOGGER.debug("Computing char for {}", i);

        int order = 1;
        BigInteger numsInOrder = BigInteger.valueOf(9);

        while (i.compareTo(numsInOrder) > 0) {
            i = i.subtract(numsInOrder);
            order++;
            numsInOrder = getTotalDsInOrder(order);
        }
        i = i.subtract(BigInteger.ONE);

        BigInteger num = getNum(i, order);
        int digN = getDigOrder(i, order);

        return getDigit(BigInteger.TEN.pow(order-1).add(num), digN);
    }

    private static int getDigit(BigInteger n, int digN) {
        while (digN > 1) {
            digN--;
            n = n.divide(BigInteger.TEN);
        }
        return n.mod(BigInteger.TEN).intValue();
    }

    private static BigInteger getNum(BigInteger n, int order) {
        return n.divide(BigInteger.valueOf(order));
    }

    private static int getDigOrder(BigInteger n, int order) {
        return order - n.mod(BigInteger.valueOf(order)).intValue();
    }

    private static BigInteger getTotalDsInOrder(int order) {
        return (BigInteger.TEN.pow(order).
                subtract(BigInteger.TEN.pow(order-1))).multiply(BigInteger.valueOf(order));

    }
}
