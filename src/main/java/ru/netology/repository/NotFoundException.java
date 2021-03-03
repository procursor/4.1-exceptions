package ru.netology.repository;

public class NotFoundException extends RuntimeException {
    public static final String NO_SUCH_PRODUCT = "no such product id=";

    public NotFoundException(int id) {
        super(NO_SUCH_PRODUCT + id);
    }
}
