package org.hummingbyte.core.visitor.stringifier;

import org.hummingbyte.core.parser.ast.Expr;

public interface Stringifier {
    String stringify(Expr expr);
}
