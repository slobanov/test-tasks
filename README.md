### Тропический остров
 
__Идея алгоритма__: 

1. "Погружаем" весь остров (кроме границы) полностью под воду.
2. Создаем очередь областей с приоритетом по высоте над уровнем моря.
3. Добавляем в очередь все граничные области.
4. Пока очередь не пуста: <br />
  4.1. Достаем самую низкую область из очереди. <br />
  4.2. Сливаем воду из соседних областей через выбранную область (если возможно). <br />
  4.3. Если удалось уменьшить уровень в соседней области - добавляем ее в очередь. <br />
  

__NB__: Алгоритм реализован в классе ```ru .amaicode.hh.school.island.algs.LowestSpillAlgorithm``` 
Сложность алгоритма - O(N\*M)log(N\*M) - каждая область побывает в очереди один раз + необходимо поддерживать порядок.

### Бесконечная последовательность

__Идея алгоритма__:

Просто алгоритм Бойера — Мура.
Алгоритм, время работы которого не зависит от длины минимальной последовательности, не придумал.
Алгоритм реализован в классе ```ru.amaicode.hh.school.seq.algs.BMMatchingAlgorithm```

### Сборка и запуск

* Собираем fat-jar: <br />
```$ mvn package ```

* Запуск первого задания (Тропический остров): <br />
```$ java -cp target/test-tasks.jar ru.amaicode.hh.school.runners.IslandFloodingRunner```

* Запуск второго задания (Бесконечная последовательность): <br />
```$ java -cp target/test-tasks.jar ru.amaicode.hh.school.runners.SearchSubsequencesRunner```

