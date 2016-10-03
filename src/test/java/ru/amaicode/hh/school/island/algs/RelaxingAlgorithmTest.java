package ru.amaicode.hh.school.island.algs;

public class RelaxingAlgorithmTest extends FloodingAlgorithmTest {

    @Override
    FloodingAlgorithm getAlgorithm() {
        return FloodingAlgorithm.relaxingAlgorithm();
    }

}