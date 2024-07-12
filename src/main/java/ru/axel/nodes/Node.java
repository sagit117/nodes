package ru.axel.nodes;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.BiConsumer;

abstract public class Node {
    final private Set<Node> nodes = new HashSet<>();
    private Node rootNode = null;
    final private Map<String, Set<BiConsumer<Object, Node>>> handlers = new HashMap<>();

    protected Node() {

    }

    public @NotNull Node addChildNode(@NotNull Node node) {
        nodes.add(node.setRootNode(this));
        return this;
    }

    private Node setRootNode(@NotNull Node node) {
        rootNode = node;
        return this;
    }

    public void emit(@NotNull String name, @Nullable Object data) {
        emit(name, data, this);
    }

    public void emit(@NotNull String name) {
        emit(name, null, this);
    }

    protected void emit(@NotNull String name, @Nullable Object data, @NotNull Node node) {
        if (handlers.containsKey(name)) {
            handlers.get(name).forEach(biConsumer -> biConsumer.accept(data, node));
        }

        if (rootNode != null) rootNode.emit(name, data, node);
    }

    @SuppressWarnings("unchecked")
    public <T> void addHandler(@NotNull String name, BiConsumer<T, Node> biConsumer) {
        if (handlers.containsKey(name)) {
            handlers.get(name).add((BiConsumer<Object, Node>)biConsumer);
        } else {
            final Set<BiConsumer<Object, Node>> biConsumers = new HashSet<>();
            biConsumers.add((BiConsumer<Object, Node>)biConsumer);

            handlers.put(name, biConsumers);
        }
    }
}
