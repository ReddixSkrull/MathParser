package de.doering.mathinterpreter.models;

import java.util.ArrayList;
import java.util.List;

public class TokenTreeToken {

    private Token token;
    private List<TokenTreeToken> children = new ArrayList<TokenTreeToken>();

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public List<TokenTreeToken> getChildren() {
        return children;
    }

    public void setChildren(List<TokenTreeToken> children) {
        this.children = children;
    }

    public void addChild(TokenTreeToken child) {
        this.children.add(child);
    }

    public void removeChild(TokenTreeToken child) {
        this.children.remove(child);
    }

    @Override
    public String toString() {
        return "TokenTreeToken{" +
                "token=" + token +
                ", children=" + children +
                '}';
    }
}
