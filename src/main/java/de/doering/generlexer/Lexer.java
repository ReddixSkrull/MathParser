package de.doering.generlexer;

import de.doering.generlexer.models.token.BaseTokentype;
import de.doering.generlexer.models.OpenCloseLexem;
import de.doering.generlexer.models.token.Token;
import de.doering.generlexer.models.token.Tokentype;
import de.doering.generlexer.models.tree.LexemNode;

import java.util.*;

// der plan ist eine sdl zu bauen mit der man ein dokument lexen kann
// am besten wäre wenn man tokens beim lexer mittels regeln in form von methoden registriert und der lexer diese regeln
// dann verwendet um das dokument zu lexen
// vielleiht mittels builderpattern?
public class Lexer {

    private String currentLexem = "";
    private ArrayList<String> keywordLexeme = new ArrayList<>();
    private HashMap<Tokentype, String> keywordLexemMap = new HashMap<>();
    private ArrayList<OpenCloseLexem> openCloseLexeme = new ArrayList<>();
    private ArrayList<LexemNode> lexemeTrees = new ArrayList<>();


    /*
     * keywords:
     * public
     * private
     * const
     *
     * baum 1
     * c - o - n - s - t
     *
     * baum 2
     * p - u - b - l - i - c
     *   - r - i - v - a - t - e
     *
     * */
    // ich lass die muster lexeme erstmal weg
    public void buildLexemTrees() {
        LexemNode currentLexemNode = null;
        for (String keyword : keywordLexeme) {
            List<String> keywordSplit = Arrays.stream(keyword.split("")).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
            // herausfinden ob es eine kette mit gleichem anfangsbuchstaben gibt
            // sonst neue anlegen
            List<LexemNode> result = lexemeTrees.stream().filter(node -> node.getValue().equals(keywordSplit.getFirst())).toList();
            if (!result.isEmpty()) {
                currentLexemNode = result.getFirst();
                keywordSplit.removeFirst();
            } else {
                currentLexemNode = new LexemNode(keywordSplit.removeFirst());
                lexemeTrees.add(currentLexemNode);
            }
            // für jeden buchstaben prüfen ob er in der kette ist
            // wenn eine verzweigung in der kette ist prüfen ob der buchstabe gleich einer der beiden verziweigungen ist
            // wenn ein buchstabe nicht mehr in der kette ist von der kette spalten und ab dort alle buchstaben als nodes aneinenander hängen
            for (String s : keywordSplit) {
                Optional<LexemNode> matchingChild = currentLexemNode.getChildren().stream().filter(node -> node.getValue().equals(s)).findFirst();
                if (matchingChild.isPresent()) {
                    currentLexemNode = matchingChild.get();
                } else {
                    LexemNode newLexemNode = new LexemNode(s, currentLexemNode);
                    currentLexemNode.addChild(newLexemNode);
                    currentLexemNode = newLexemNode;
                }
            }
        }
    }

    // wir matchen alle regeln
    // wenn eine regel für das aktuelle lexem ein false zurückgibt verwenden wir diese nicht mehr bis zum nächsten lexem
    // das lexem ist so lange wie es mindestens eine einzige regel gibt die true zurückgibt
    // sobald eine andere regel als die vorherige match ist das lexem beendet
    // dann wird ein token der regel zurückgegeben
    /*
     * gedankenspiel
     * 1 + 1 soll gelexed werden
     * gewünschtes ergebniss: [zahl(1)][operation(+)][zahl(1)]
     *
     * der lexer ließt ein zeichen ein
     *   wenn zeichen
     *   wenn ziffer:
     *
     * eigentlich sollte dem lexer egal sein an welche position etwas steht oder?
     * bsp
     * var a = b + c
     * [keyword(var);variable(a);assignment(=);variable(b);operator(+);variable(c)]
     * */

    /*
     * das heißt der lexer muss eine funktion haben um keywords zu registrieren
     * außerdem muss der lexer
     * folgende funktionen braucht der lexer:
     * - tokens für keywords/symbole/operatoren registrieren
     * - tokens für zeichenfolgen registrieren die ein bestimmtes musster haben bsp strings sind oft so aufgebaut "irgendeinezeichenkette"
     *   -> das musster wäre hier "öffnen "schließen
     *   -> (öffnen )schließen
     * baum aus den einzelnen buchstaben der keywords bauen?
     * mehrere bäume neben einenader bsp:
     *

     *
     * */
    // eventuell die keywords direkt als token übergeben?
    public Token[] lexLine(String line) {
        ArrayList<Token> tokens = new ArrayList<>();
        ArrayList<String> lineSplit = Arrays.stream(line.split("")).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        StringBuilder stringBuilder = new StringBuilder();
        Optional<LexemNode> currentLexemNode = Optional.empty();
        ArrayList<LexemNode> currentLexemNodes = lexemeTrees;

        for (String s : lineSplit) {
            currentLexemNode = currentLexemNodes.stream().filter(node -> node.getValue().equals(s)).findFirst();
            stringBuilder.append(s);
            if (currentLexemNode.isEmpty()) {
                Token newToken = new Token(stringBuilder.toString(), 0, 0, BaseTokentype.UNKNOWN);
                tokens.add(newToken);
                stringBuilder = new StringBuilder();
                currentLexemNodes = lexemeTrees;
            } else {
                if (currentLexemNode.get().getChildren().isEmpty()) {
                    Token newToken = matchStringToToken(stringBuilder.toString());
                    tokens.add(newToken);
                    stringBuilder = new StringBuilder();
                    currentLexemNodes = lexemeTrees;
                    continue;
                }
                currentLexemNodes = currentLexemNode.get().getChildren();
            }
        }

        return tokens.toArray(Token[]::new);
    }


    private Token matchStringToToken(String string) {
        return keywordLexemMap
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().contains(string))
                .findFirst()
                .map(entry -> new Token(string, 0, 0, entry.getKey()))
                .orElse(new Token(string, 0, 0, BaseTokentype.UNKNOWN));
    }

    public void addKeyword(String keyword, Tokentype type) {
        keywordLexeme.add(keyword);
        keywordLexemMap.put(type, keyword);
    }

    public void addOpenCloseLexem(OpenCloseLexem lexem) {
        openCloseLexeme.add(lexem);
    }

    public void addOpenCloseLexem(String opening, String closing) {
        openCloseLexeme.add(new OpenCloseLexem(opening, closing));
    }

    public void addOpenCloseLexeme(String lexeme) {
        openCloseLexeme.add(new OpenCloseLexem(lexeme, lexeme));
    }

    public String getCurrentLexem() {
        return currentLexem;
    }

    public void setCurrentLexem(String currentLexem) {
        this.currentLexem = currentLexem;
    }

    public ArrayList<String> getKeywordLexeme() {
        return keywordLexeme;
    }

    public void setKeywordLexeme(ArrayList<String> keywordLexeme) {
        this.keywordLexeme = keywordLexeme;
    }

    public ArrayList<OpenCloseLexem> getOpenCloseLexeme() {
        return openCloseLexeme;
    }

    public void setOpenCloseLexeme(ArrayList<OpenCloseLexem> openCloseLexeme) {
        this.openCloseLexeme = openCloseLexeme;
    }

    public ArrayList<LexemNode> getLexemeTrees() {
        return lexemeTrees;
    }

    public void setLexemeTrees(ArrayList<LexemNode> lexemeTrees) {
        this.lexemeTrees = lexemeTrees;
    }
}
