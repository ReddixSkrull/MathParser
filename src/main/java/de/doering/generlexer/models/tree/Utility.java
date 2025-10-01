package de.doering.generlexer.models.tree;

public class Utility {

    public static void printAsciiTree(LexemNode node) {
        printAsciiTree(node, "", true);
    }

    private static void printAsciiTree(LexemNode node, String prefix, boolean isLast) {
        // Knoten selbst ausgeben
        System.out.println(prefix + (isLast ? "└── " : "├── ") + node.getValue());

        // Kinder durchlaufen
        if (node.getChildren()!= null && !node.getChildren().isEmpty()) {
            for (int i = 0; i < node.getChildren().size(); i++) {
                boolean lastChild = (i == node.getChildren().size() - 1);
                String newPrefix = prefix + (isLast ? "    " : "│   ");
                printAsciiTree(node.getChildren().get(i), newPrefix, lastChild);
            }
        }
    }
}
