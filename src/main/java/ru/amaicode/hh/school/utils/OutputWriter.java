package ru.amaicode.hh.school.utils;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.stream.IntStream;

public final class OutputWriter {
    private static final XLogger LOGGER = XLoggerFactory.getXLogger(OutputWriter.class);
    private final BufferedWriter writer;

    private OutputWriter(OutputStream outputStream) {
        writer = new BufferedWriter(new OutputStreamWriter(outputStream));
    }

    public static OutputWriter from(OutputStream outputStream) {
        return new OutputWriter(outputStream);
    }

    public void writeBigInt(BigInteger i) {
        try {
            writer.write(i.toString());
        } catch (IOException ioe) {
            LOGGER.error("Failed to write int: {}", i, ioe);
            throw new IORuntimeException(ioe);
        }
    }

    public void newLine() {
        try {
            writer.write(System.lineSeparator());
        } catch (IOException ioe) {
            LOGGER.error("Failed to write '\\n'", ioe);
            throw new IORuntimeException(ioe);
        }
    }

    public void writelnIntSteam(IntStream stream) {
        String resString = PrintUtils.printlns(stream);

        try {
            writer.write(resString);
        } catch (IOException ioe) {
            LOGGER.error("Failed to write stream: {}", resString, ioe);
            throw new IORuntimeException(ioe);
        }
    }

    public void close() {
        try {
            writer.close();
        } catch (IOException ioe) {
            LOGGER.error("Failed to close gracefully", ioe);
            throw new IORuntimeException(ioe);
        }
    }
}
