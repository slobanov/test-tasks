package ru.amaicode.hh.school.island.algs;

public class LowestSpillAlgorithmTest extends FloodingAlgorithmTest {

    @Override
    FloodingAlgorithm getAlgorithm() {
        return FloodingAlgorithm.spillingAlgorithm();
    }
}