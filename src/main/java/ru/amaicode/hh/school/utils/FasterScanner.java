package ru.amaicode.hh.school.utils;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public final class FasterScanner {
    private static final XLogger LOGGER = XLoggerFactory.getXLogger(FasterScanner.class);
    private final BufferedReader reader;
    private StringTokenizer tokenizer;

    private FasterScanner(InputStream inputStream) {
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public static FasterScanner from(InputStream inputStream) {
        return new FasterScanner(inputStream);
    }

    public String nextLine() {
        try {
            return reader.readLine();
        } catch (IOException ioe) {
            LOGGER.error("Failed to read next line");
            throw new IORuntimeException(ioe);
        }
    }

    public int nextInt() {
        if (tokenizer == null || !tokenizer.hasMoreElements()) {
            String nextLine = nextLine();
            if (nextLine == null) {
                LOGGER.error("nextLine is null");
                throw new IORuntimeException("nextLine is null");
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return Integer.parseInt(tokenizer.nextToken());
    }

    public void close() {
        try {
            reader.close();
        } catch (IOException ioe) {
            LOGGER.error("Failed to close gracefully", ioe);
            throw new IORuntimeException(ioe);
        }
    }

}
