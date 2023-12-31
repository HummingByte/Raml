package org.hummingbyte.core.parser.ast;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hummingbyte.core.visitor.ResultVisitor;
import org.hummingbyte.core.visitor.Visitor;


@AllArgsConstructor
@Getter
@Setter
public class ObjectExpr extends Expr{
    private PairExpr[] pairs;

    @Override
    public void visit(Visitor visitor) {
        visitor.visitObject(this);
    }

    @Override
    public <T> T visit(ResultVisitor<T> visitor) {
        return visitor.visitObject(this);
    }
}
