package de.doering.mathinterpreter.models;

public class Token {

    private Tokentypes tokentype;
    private int line;
    private int column;
    private String lexem;

    public Token() {
    }

    public Token(Tokentypes tokentype, int line, int column, String lexem) {
        this.tokentype = tokentype;
        this.line = line;
        this.column = column;
        this.lexem = lexem;
    }

    public Tokentypes getTokentype() {
        return tokentype;
    }

    public void setTokentype(Tokentypes tokentype) {
        this.tokentype = tokentype;
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

    public String getLexem() {
        return lexem;
    }

    public void setLexem(String lexem) {
        this.lexem = lexem;
    }

    @Override
    public String toString() {
        return "Token{" +
                "lexeme='" + tokentype + '\'' +
                ", line=" + line +
                ", column=" + column +
                ", value='" + lexem + '\'' +
                '}';
    }

    public boolean isDigit(){
        return tokentype.equals(Tokentypes.DIGIT);
    }

    public boolean isOperator(){
        return tokentype.equals(Tokentypes.OPERATION);
    }

}
