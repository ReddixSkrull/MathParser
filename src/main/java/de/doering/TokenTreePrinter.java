package de.doering;

public class TokenTreePrinter {

    public static String prettyPrint(TokenTree tree) {
        StringBuilder sb = new StringBuilder();
        prettyPrintNode(tree.getRoot(), sb, 0);
        return sb.toString();
    }

    private static void prettyPrintNode(TokenTreeToken node, StringBuilder sb, int indent) {
        indent(sb, indent);
        sb.append(node.getToken().getLexeme())
                .append(" (value=")
                .append(node.getToken().getValue())
                .append(", line=")
                .append(node.getToken().getLine())
                .append(", col=")
                .append(node.getToken().getColumn())
                .append(")")
                .append("\n");

        for (TokenTreeToken child : node.getChildren()) {
            prettyPrintNode(child, sb, indent + 2);
        }
    }

    private static void indent(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append(' ');
        }
    }
}

