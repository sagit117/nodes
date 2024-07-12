package ru.axel;

import ru.axel.nodes.Node;

import java.util.function.BiConsumer;

public class Main {
    public static void main(String[] args) {
        Node root = new Node() {};
        Node child1 = new Node() {};
        Node child2 = new Node() {};

        root.addHandler("test", (o, node) -> System.out.println("init node: " + node.getClass().getTypeName() + " " + o));
//        child1.addHandler("test", (BiConsumer<Integer, Node>) (o, node) -> System.out.println(o + 1));

        root
                .addChildNode(child2)
                .addChildNode(child1);

        System.out.println(root.toString());
    }
}