package de.doering.mathinterpreter.lexer;

import de.doering.mathinterpreter.exceptions.BracketMismatchException;
import de.doering.mathinterpreter.models.Token;
import de.doering.mathinterpreter.models.TokenFactory;
import de.doering.mathinterpreter.util.ParsingUtils;

import java.util.ArrayList;
import java.util.List;

public class MathLexer {

    public Token[] lexLine(String[] arr, int lineNumber) {
        List<Token> tokens = new ArrayList<>();
        StringBuilder tokenValue = new StringBuilder();
        int bracketCount = 0;
        // solannge zeichen zu currentokenvalue hinzufügen bis das nächste zeichen ein anders token wäre
        for (int i = 0; i < arr.length; i++) {
            String currentTokenString = arr[i];
            if (ParsingUtils.isDigit(currentTokenString)) {

                if (ParsingUtils.isDigit(arr[(i + 1) % arr.length])) {
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
            if (ParsingUtils.isOperation(currentTokenString)) {
                tokenValue.append(currentTokenString);
                tokens.add(TokenFactory.createOperationToken(0, i, tokenValue.toString()));
                tokenValue = new StringBuilder();
            }
            if (ParsingUtils.isOpeningBracket(currentTokenString)) {
                bracketCount++;
                tokenValue.append(currentTokenString);
                tokens.add(TokenFactory.createOpeningBracketToken(0, i, tokenValue.toString()));
                tokenValue = new StringBuilder();
            }
            if (ParsingUtils.isClosingBracket(currentTokenString)) {
                bracketCount--;
                tokenValue.append(currentTokenString);
                tokens.add(TokenFactory.createClosingBracketToken(0, i, tokenValue.toString()));
                tokenValue = new StringBuilder();
            }
        }
        if (bracketCount != 0) {
            throw new BracketMismatchException("Bracket count mismatch. " + (bracketCount < 0 ? "Es gibt eine schließende Klammer zu viel." : "Es gibt eine schließende Klammer zu wenig."));
        }
        return tokens.toArray(Token[]::new);
    }

}
