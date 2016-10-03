package ru.amaicode.hh.school.runners;

import ru.amaicode.hh.school.utils.FasterScanner;
import ru.amaicode.hh.school.utils.OutputWriter;

import java.io.InputStream;
import java.io.OutputStream;

abstract class AbstractRunner implements AutoCloseable {
    final FasterScanner scanner;
    final OutputWriter writer;

    AbstractRunner(InputStream in, OutputStream out) {
        this.scanner = FasterScanner.from(in);
        this.writer = OutputWriter.from(out);
    }

    @Override
    public final void close() {
        scanner.close();
        writer.close();
    }

    abstract void run();
}
