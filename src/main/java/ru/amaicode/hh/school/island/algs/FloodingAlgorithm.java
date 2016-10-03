package ru.amaicode.hh.school.island.algs;

import ru.amaicode.hh.school.island.Island;

public interface FloodingAlgorithm {
    static FloodingAlgorithm relaxingAlgorithm() {
        return new RelaxingAlgorithm();
    }
    static FloodingAlgorithm spillingAlgorithm() {
        return new LowestSpillAlgorithm();
    }

    void flood(Island island);
}
