package de.doering;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    private static String arrayToString(Token[] arr) {
        StringJoiner sj = new StringJoiner(",");
        for (Token t : arr) {
            sj.add(t.toString());
        }
        return sj.toString();
    }

    private static String arrayToString(String[] arr) {
        StringJoiner sj = new StringJoiner(", ");
        for (String s : arr) {
            sj.add(s);
        }
        return sj.toString();
    }

    private static String[] removeWhitespaces(String[] arr) {
        return Arrays.stream(arr).filter(s -> !s.isBlank()).toArray(String[]::new);
    }

    // ich könnte das mit einer statemachine regeln
    // erstmal einen baum bauen?
    // fangen wir erstmal naiv an
    // der hat jetzt das problem das man nur zahlen die eine ziffer haben parsen kann
    private static Token[] parseLine(String[] arr, int lineNumber) {
        List<Token> tokens = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            String s = arr[i];
            switch (s) {
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                    tokens.add(TokenFactory.createDigitToken(0, i, s));
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    tokens.add(TokenFactory.createOperationToken(0, 0, s));
                    break;
                case "(":
                    tokens.add(TokenFactory.createOpeningBracketToken(0, i, s));
                    break;
                case ")":
                    tokens.add(TokenFactory.createClosingBracketToken(0, i, s));
                    break;
                default:
                    tokens.add(TokenFactory.createUnknownToken(0, i, s));
            }
        }
        return tokens.toArray(Token[]::new);
    }

    private static boolean isDigit(String c) {
        char cast = c.charAt(0);
        return cast >= '0' && cast <= '9';
    }

    private static boolean isOperation(String c) {
        char cast = c.charAt(0);
        return cast == '+' || cast == '*' || cast == '/' || cast == '-';
    }

    private static boolean isOpeningBracket(String c) {
        char cast = c.charAt(0);
        return cast == '(' || cast == '[' || cast == '{';
    }

    private static boolean isClosingBracket(String c) {
        char cast = c.charAt(0);
        return cast == ']' || cast == '}' || cast == ')';
    }

    private static Token[] parseLine2(String[] arr, int lineNumber) {
        List<Token> tokens = new ArrayList<>();
        StringBuilder tokenValue = new StringBuilder();
        int bracketCount = 0;
        // solannge zeichen zu currentokenvalue hinzufügen bis das nächste zeichen ein anders token wäre
        for (int i = 0; i < arr.length; i++) {
            String currentTokenString = arr[i];
            if (isDigit(currentTokenString)) {

                if (isDigit(arr[(i + 1) % arr.length])) {
                    tokenValue.append(currentTokenString);
                    if (i + 1 >= arr.length) {
                        tokens.add(TokenFactory.createDigitToken(0, i, tokenValue.toString()));
                        tokenValue = new StringBuilder();
                    }
                } else {
                    tokenValue.append(currentTokenString);
                    tokens.add(TokenFactory.createDigitToken(0, i, tokenValue.toString()));
                    tokenValue = new StringBuilder();
                }
            }
            if (isOperation(currentTokenString)) {
                tokenValue.append(currentTokenString);
                tokens.add(TokenFactory.createOperationToken(0, i, tokenValue.toString()));
                tokenValue = new StringBuilder();
            }
            if (isOpeningBracket(currentTokenString)) {
                bracketCount++;
                tokenValue.append(currentTokenString);
                tokens.add(TokenFactory.createOpeningBracketToken(0, i, tokenValue.toString()));
                tokenValue = new StringBuilder();
            }
            if (isClosingBracket(currentTokenString)) {
                bracketCount--;
                tokenValue.append(currentTokenString);
                tokens.add(TokenFactory.createClosingBracketToken(0, i, tokenValue.toString()));
                tokenValue = new StringBuilder();
            }
        }
        if (bracketCount != 0) {
            System.out.println("Bracket count mismatch");
        }
        return tokens.toArray(Token[]::new);
    }

    // ich muss die operationen ordnen damit zuerst die multiplikationen berechnet werden
    // ich muss den baum ursprünglich anders bauen
    // alle multiplikationen müssen endnodes sein, oder aneinander gehängt werden
    private static TokenTree buildTree(Token[] tokens) {
        List<Token> tokenList = new ArrayList<>(Arrays.stream(tokens).toList());
        TokenTree tree = new TokenTree();


        Token currentToken = tokenList.removeFirst();
        TokenTreeToken tokenTreeToken = new TokenTreeToken();
        tokenTreeToken.setToken(currentToken);
        tree.setRoot(tokenTreeToken);

        TokenTreeToken currentTreeNode = tree.getRoot();

        while (!tokenList.isEmpty()) {
            currentToken = tokenList.removeFirst();
            if (currentToken.getLexeme().equals("operation")) {
                TokenTreeToken nextTreeToken = new TokenTreeToken();
                nextTreeToken.setToken(currentToken);
                nextTreeToken.addChild(currentTreeNode);
                tree.setRoot(nextTreeToken);
                currentTreeNode = nextTreeToken;
            } else if (currentToken.getLexeme().equals("digit")) { // wenn es eine zahl ist
                TokenTreeToken nextTreeToken = new TokenTreeToken();
                nextTreeToken.setToken(currentToken);
                currentTreeNode.addChild(nextTreeToken);
            }
        }

        return tree;
    }

    private static String performOperation(String operation, String a, String b) {
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


    private static String caluclateTree(TokenTree tree) {
        TokenTreeToken root = tree.getRoot();
        String result = caluclateNode(root);
        System.out.println(result);
        return "";
    }

    private static String caluclateNode(TokenTreeToken tokenTreeToken) {
        TokenTreeToken firstChild = tokenTreeToken.getChildren().removeFirst();
        TokenTreeToken secondChild = tokenTreeToken.getChildren().removeFirst();
        String tA = firstChild.getToken().getLexeme().equals("operation") ? caluclateNode(firstChild) : firstChild.getToken().getValue();
        String tB = secondChild.getToken().getLexeme().equals("operation") ? caluclateNode(secondChild) : secondChild.getToken().getValue();
        String tOperation = tokenTreeToken.getToken().getValue();
        return performOperation(tOperation, tA, tB);
    }


    public static void main(String[] args) {
        try (FileReader fileReader = new FileReader("src/main/resources/example_1.txt"); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line = bufferedReader.readLine();
            String[] split = line.split("");
            split = removeWhitespaces(split);
            Token[] tokens = parseLine2(split, 0);
            TokenTree tree = buildTree(tokens);
            System.out.println(arrayToString(split));
            System.out.println(arrayToString(tokens));
            System.out.println(tree);
            System.out.println(TokenTreePrinter.prettyPrint(tree));
            caluclateTree(tree);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}