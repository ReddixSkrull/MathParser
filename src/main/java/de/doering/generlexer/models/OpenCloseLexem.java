package de.doering.generlexer.models;

public class OpenCloseLexem {
    private String opening = "";
    private String closing = "";

    public OpenCloseLexem() {
    }

    public OpenCloseLexem(String opening, String closing) {
        this.opening = opening;
        this.closing = closing;
    }

    public String getClosing() {
        return closing;
    }

    public void setClosing(String closing) {
        this.closing = closing;
    }

    public String getOpening() {
        return opening;
    }

    public void setOpening(String opening) {
        this.opening = opening;
    }
}
