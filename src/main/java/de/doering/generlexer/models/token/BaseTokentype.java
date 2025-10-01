package de.doering.generlexer.models.token;

public class BaseTokentype implements Tokentype {


    public BaseTokentype(String token) {
        this.token = token;
    }

    private String token = null;

    public static final BaseTokentype UNKNOWN = new BaseTokentype("UNKNOWN");
    public static final BaseTokentype KEYWORD = new BaseTokentype("KEYWORD");
    public static final BaseTokentype FREIBEUTER = new BaseTokentype("FREIBEUTER");
    public static final BaseTokentype ACCESS_MODIFIER = new BaseTokentype("ACCESS_MODIFIER");
    public static final Tokentype WHITESPACE = new BaseTokentype("WHITESPACE");


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "BaseTokentype{" +
                "token='" + token + '\'' +
                '}';
    }
}
