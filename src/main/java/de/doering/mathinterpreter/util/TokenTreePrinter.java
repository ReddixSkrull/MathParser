package de.doering.mathinterpreter.util;

import de.doering.mathinterpreter.models.TokenTree;
import de.doering.mathinterpreter.models.TokenTreeToken;

public class TokenTreePrinter {

    public static String prettyPrint(TokenTree tree) {
        StringBuilder sb = new StringBuilder();
        prettyPrintNode(tree.getRoot(), sb, "", true);
        return sb.toString();
    }

    private static void prettyPrintNode(TokenTreeToken node, StringBuilder sb, String prefix, boolean isTail) {
        sb.append(prefix)
                .append(isTail ? "└── " : "├── ")
                .append(node.getToken().getTokentype())
                .append(" (value=")
                .append(node.getToken().getLexem())
                .append(", line=")
                .append(node.getToken().getLine())
                .append(", col=")
                .append(node.getToken().getColumn())
                .append(")")
                .append("\n");

        var children = node.getChildren();
        for (int i = 0; i < children.size(); i++) {
            boolean last = (i == children.size() - 1);
            prettyPrintNode(children.get(i), sb, prefix + (isTail ? "    " : "│   "), last);
        }
    }
}


