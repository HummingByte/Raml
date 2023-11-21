package org.hummingbyte.core.parser.ast;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hummingbyte.core.visitor.ResultVisitor;
import org.hummingbyte.core.visitor.Visitor;

@AllArgsConstructor
@Getter
@Setter
public class PairExpr extends Expr{
    public static final PairExpr[] EMPTY_ARRAY = new PairExpr[0];

    private Expr key;
    private Expr value;

    @Override
    public void visit(Visitor visitor) {
        visitor.visitPair(this);
    }

    @Override
    public <T> T visit(ResultVisitor<T> visitor) {
        return visitor.visitPair(this);
    }
}
