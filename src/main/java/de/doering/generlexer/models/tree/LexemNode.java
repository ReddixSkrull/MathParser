package de.doering.generlexer.models.tree;

import java.util.ArrayList;

public class LexemNode {
    private LexemNode parent;
    private ArrayList<LexemNode> children = new ArrayList<LexemNode>();
    private String value;

    public LexemNode() {

    }

    public LexemNode(String value) {
        this.value = value;
    }

    public LexemNode(String value, LexemNode parent) {
        this.value = value;
        this.parent = parent;
    }

    public LexemNode(LexemNode parent, ArrayList<LexemNode> children, String value) {
        this.parent = parent;
        this.children = children;
        this.value = value;
    }

    public LexemNode getParent() {
        return parent;
    }

    public void setParent(LexemNode parent) {
        this.parent = parent;
    }

    public ArrayList<LexemNode> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<LexemNode> children) {
        this.children = children;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void addChild(LexemNode child) {
        children.add(child);
    }

    @Override
    public String toString() {
        return "LexemNode{" +
//                "parent=" + parent == null ? parent.getValue() : "root"  +
                ", children=" + children +
                ", value='" + value + '\'' +
                '}';
    }
}
