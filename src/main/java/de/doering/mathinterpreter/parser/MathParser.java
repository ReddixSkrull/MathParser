package de.doering.mathinterpreter.parser;

import de.doering.mathinterpreter.exceptions.InvalidLexemException;
import de.doering.mathinterpreter.exceptions.InvalidRootException;
import de.doering.mathinterpreter.models.Token;
import de.doering.mathinterpreter.models.TokenTree;
import de.doering.mathinterpreter.models.TokenTreeToken;
import de.doering.mathinterpreter.util.TokenTreePrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MathParser {

    public TokenTree parseTokens(Token[] tokens) {
        List<Token> tokenList = new ArrayList<>(Arrays.stream(tokens).toList());
        TokenTree tree = new TokenTree();
        TokenTreeToken lastAddedToken = null;
        TokenTreeToken lastAddedParent = null;

        while (!tokenList.isEmpty()) {
            Token firstToken = tokenList.removeFirst();
            if (firstToken.isDigit()) {
                if (tree.getRoot() == null) {
                    TokenTreeToken newRootToken = new TokenTreeToken();
                    newRootToken.setToken(firstToken);
                    tree.setRoot(newRootToken);
                    lastAddedToken = newRootToken;
                } else if (lastAddedToken.getToken().isOperator()) {
                    TokenTreeToken newTokenTreeToken = new TokenTreeToken();
                    newTokenTreeToken.setToken(firstToken);
                    lastAddedParent = lastAddedToken;
                    lastAddedToken.addChild(newTokenTreeToken);
                    lastAddedToken = newTokenTreeToken;
                } else {
                    throw new InvalidRootException("Tree Root war keine Operation oder Leer. Deshalb konnte kein das Token"
                            + firstToken
                            + " nicht hinzugefügt werden.\n"
                            + "Tree: \n"
                            + TokenTreePrinter.prettyPrint(tree));
                }
            } else if (firstToken.isOperator()) {
                if (firstToken.getLexem().equals("*") || firstToken.getLexem().equals("/")) {
                    if (lastAddedToken.getToken().isDigit()) {
                        TokenTreeToken newTokenTreeToken = new TokenTreeToken();
                        newTokenTreeToken.setToken(firstToken);
                        newTokenTreeToken.addChild(lastAddedToken);
                        if (lastAddedParent == null) {
                            tree.setRoot(newTokenTreeToken);
                            lastAddedToken = newTokenTreeToken;
                        } else {
                            lastAddedParent.getChildren().remove(lastAddedToken);
                            lastAddedParent.addChild(newTokenTreeToken);
                            lastAddedToken = newTokenTreeToken;
                        }
                    }
                } else if (firstToken.getLexem().equals("+") || firstToken.getLexem().equals("-")) {
                    TokenTreeToken newTokenTreeToken = new TokenTreeToken();
                    newTokenTreeToken.setToken(firstToken);
                    newTokenTreeToken.addChild(tree.getRoot());
                    tree.setRoot(newTokenTreeToken);
                    lastAddedToken = newTokenTreeToken;
                    lastAddedParent = null;
                }
            } else {
                throw new InvalidLexemException("Es konnte nicht festgestellt werden von welchem Typ das Token: "+ firstToken + " ist.");
            }
        }

        return tree;
    }


}
