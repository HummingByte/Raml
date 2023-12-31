package org.hummingbyte.core.lexer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Token {
    private Kind kind;
    private Enter enter;
    private String symbol;

    public enum Kind{
        Ident, String, Boolean, Number, Decimal, Null,
        Assign,
        LeftBracket, RightBracket,
        LeftBrace, RightBrace,
        Comma, Semi, Colon,
        EOL, Error, Comment
    }

    public enum Enter{
        Token, Space, NL
    }
}
