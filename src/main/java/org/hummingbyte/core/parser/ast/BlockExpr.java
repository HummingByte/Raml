package org.hummingbyte.core.parser.ast;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hummingbyte.core.visitor.ResultVisitor;
import org.hummingbyte.core.visitor.Visitor;

@AllArgsConstructor
@Getter
public class BlockExpr extends Expr{
    private Expr[] exprs;

    @Override
    public void visit(Visitor visitor) {
        visitor.visitBlock(this);
    }

    @Override
    public <T> T visit(ResultVisitor<T> visitor) {
        return visitor.visitBlock(this);
    }
}
