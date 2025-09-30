package de.doering.mathinterpreter.models;

public class TokenTree {

    private TokenTreeToken root;

    public TokenTree(){}

    public TokenTree(TokenTreeToken root) {
        this.root = root;
    }

    public TokenTreeToken getRoot() {
        return root;
    }

    public void setRoot(TokenTreeToken root) {
        this.root = root;
    }

    @Override
    public String toString() {
        return "TokenTree{" +
                "root=" + root +
                '}';
    }
}
