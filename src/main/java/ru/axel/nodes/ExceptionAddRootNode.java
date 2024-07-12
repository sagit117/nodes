package ru.axel.nodes;

import org.jetbrains.annotations.NotNull;

final public class ExceptionAddRootNode extends RuntimeException {
    public ExceptionAddRootNode(@NotNull String message) {
        super(message);
    }
}
