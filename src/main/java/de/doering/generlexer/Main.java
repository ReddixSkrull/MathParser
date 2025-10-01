package de.doering.generlexer;

import de.doering.generlexer.models.token.BaseTokentype;
import de.doering.generlexer.models.token.Token;
import de.doering.generlexer.models.tree.LexemNode;
import de.doering.generlexer.models.tree.Utility;

public class Main {
    public static void main(String[] args) {
        Lexer lexer = new Lexer();
        lexer.addKeyword("public", BaseTokentype.ACCESS_MODIFIER);
        lexer.addKeyword("private", BaseTokentype.ACCESS_MODIFIER);
        lexer.addKeyword("pirat", BaseTokentype.FREIBEUTER);
        lexer.addKeyword("protected", BaseTokentype.ACCESS_MODIFIER);
        lexer.addKeyword("static", BaseTokentype.KEYWORD);
        lexer.addKeyword("final", BaseTokentype.KEYWORD);
        lexer.addKeyword("abstract", BaseTokentype.KEYWORD);
        lexer.addKeyword("interface", BaseTokentype.KEYWORD);
        lexer.addKeyword("void", BaseTokentype.KEYWORD);
        lexer.addKeyword("puffer", BaseTokentype.KEYWORD);
        lexer.addKeyword(" ", BaseTokentype.WHITESPACE);
        lexer.buildLexemTrees();
        System.out.println("Lexem Trees :");
        System.out.println(lexer.getLexemeTrees());
        for (LexemNode ln : lexer.getLexemeTrees()) {
            Utility.printAsciiTree(ln);
        }
        for (Token s : lexer.lexLine("public protected pirat")) {
            System.out.println(s);
        }
    }
}
