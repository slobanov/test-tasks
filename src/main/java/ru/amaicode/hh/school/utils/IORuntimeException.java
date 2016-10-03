package ru.amaicode.hh.school.utils;

import java.io.IOException;

final class IORuntimeException extends RuntimeException {

    IORuntimeException(IOException ioe) {
        super(ioe);
    }

    IORuntimeException(String message) {
        super(message);
    }
}
