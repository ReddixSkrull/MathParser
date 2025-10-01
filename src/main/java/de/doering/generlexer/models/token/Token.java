package de.doering.generlexer.models.token;

public class Token {
    private String lexeme;
    private int line;
    private int column;
    private Tokentype type;

    public Token(){}

    public Token(String lexeme, int line, int column, Tokentype type) {
        this.lexeme = lexeme;
        this.line = line;
        this.column = column;
        this.type = type;
    }

    public Token(Token token) {
        this.lexeme = token.lexeme;
        this.line = token.line;
        this.column = token.column;
        this.type = token.type;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Tokentype getType() {
        return type;
    }

    public void setType(Tokentype type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Token token = (Token) o;
        return getLine() == token.getLine() && getColumn() == token.getColumn() && getLexeme().equals(token.getLexeme()) && getType().equals(token.getType());
    }

    @Override
    public int hashCode() {
        int result = getLexeme().hashCode();
        result = 31 * result + getLine();
        result = 31 * result + getColumn();
        result = 31 * result + getType().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Token{" +
                "lexeme='" + lexeme + '\'' +
                ", line=" + line +
                ", column=" + column +
                ", type=" + type +
                '}';
    }
}
