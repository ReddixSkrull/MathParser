package de.doering;

public class Token {

    private String lexeme;
    private int line;
    private int column;
    private String value;

    public Token() {
    }

    public Token(String lexeme, int line, int column, String value) {
        this.lexeme = lexeme;
        this.line = line;
        this.column = column;
        this.value = value;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Token{" +
                "lexeme='" + lexeme + '\'' +
                ", line=" + line +
                ", column=" + column +
                ", value='" + value + '\'' +
                '}';
    }

    public boolean isDigit(){
        return lexeme.equals("digit");
    }

    public boolean isOperator(){
        return lexeme.equals("operation");
    }

}
