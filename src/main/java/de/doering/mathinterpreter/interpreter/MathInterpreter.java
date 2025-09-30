package de.doering.mathinterpreter.interpreter;

import de.doering.mathinterpreter.exceptions.InterpreterExcpetion;
import de.doering.mathinterpreter.lexer.MathLexer;
import de.doering.mathinterpreter.models.Token;
import de.doering.mathinterpreter.models.TokenTree;
import de.doering.mathinterpreter.models.TokenTreeToken;
import de.doering.mathinterpreter.models.Tokentypes;
import de.doering.mathinterpreter.parser.MathParser;
import de.doering.mathinterpreter.util.ArrayUtils;
import de.doering.mathinterpreter.util.TokenTreePrinter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MathInterpreter {
    private String performOperation(String operation, String a, String b) {
        Integer aAsInt = Integer.parseInt(a);
        Integer bAsInt = Integer.parseInt(b);
        return switch (operation) {
            case "+" -> String.valueOf(aAsInt + bAsInt);
            case "-" -> String.valueOf(aAsInt - bAsInt);
            case "*" -> String.valueOf(aAsInt * bAsInt);
            case "/" -> String.valueOf(aAsInt / bAsInt);
            default -> String.valueOf(aAsInt);
        };
    }


    public String calculateTree(TokenTree tree) {
        return calculateNode(tree.getRoot());
    }

    private String calculateNode(TokenTreeToken tokenTreeToken) {
        TokenTreeToken firstChild = tokenTreeToken.getChildren().removeFirst();
        TokenTreeToken secondChild = tokenTreeToken.getChildren().removeFirst();
        String tA = firstChild.getToken().getTokentype().equals(Tokentypes.OPERATION) ? calculateNode(firstChild) : firstChild.getToken().getLexem();
        String tB = secondChild.getToken().getTokentype().equals(Tokentypes.OPERATION) ? calculateNode(secondChild) : secondChild.getToken().getLexem();
        String tOperation = tokenTreeToken.getToken().getLexem();
        return performOperation(tOperation, tA, tB);
    }

    public void calculateFile(String filePath) {
        calculateFile(filePath, false);
    }

    public void calculateFile(String filePath, boolean debug) {
        try (FileReader fileReader = new FileReader(filePath); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            List<String> lines = new ArrayList<>();
            List<String[]> linesSplit = new ArrayList<>();
            List<Token[]> lexedLines = new ArrayList<>();
            List<TokenTree> tokenTrees = new ArrayList<>();
            List<String> results = new ArrayList<>();
            MathParser mathParser = new MathParser();
            MathLexer mathLexer = new MathLexer();
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
                linesSplit.add(line.split(""));
            }
            if (debug) {
                for (String[] l : linesSplit) {
                    stringBuilder.append(ArrayUtils.arrayToString(l));
                    stringBuilder.append("\n");
                }
                stringBuilder.append("Number of Lines: ");
                stringBuilder.append(linesSplit.size());
                stringBuilder.append("\n");
            }
            for (int i = 0; i < linesSplit.size(); i++) {
                lexedLines.add(mathLexer.lexLine(linesSplit.get(i), i));
            }
            for (Token[] tl : lexedLines) {
                tokenTrees.add(mathParser.parseTokens(tl));
            }
            if (debug) {
                for (int i = 0; i < tokenTrees.size(); i++) {
                    stringBuilder.append(ArrayUtils.arrayToString(lexedLines.get(i)));
                    stringBuilder.append("\n");
                    stringBuilder.append(TokenTreePrinter.prettyPrint(tokenTrees.get(i)));
                    stringBuilder.append("\n");
                }
            }
            for (TokenTree tr : tokenTrees) {
                results.add(calculateTree(tr));
            }
            for (int i = 0; i < results.size(); i++) {

                stringBuilder.append(lines.get(i));
                stringBuilder.append(" = ");
                stringBuilder.append(results.get(i));
                stringBuilder.append("\n");
            }
            System.out.println(stringBuilder);
        } catch (IOException e) {
            throw new InterpreterExcpetion("Fehler beim Interpretieren der Rechnungen.", e.getCause());
        }
    }
}
