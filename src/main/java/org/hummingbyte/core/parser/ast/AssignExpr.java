package org.hummingbyte.core.parser.ast;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hummingbyte.core.visitor.ResultVisitor;
import org.hummingbyte.core.visitor.Visitor;

@AllArgsConstructor
@Getter
@Setter
public class AssignExpr extends Expr{
    private IdentExpr key;
    private Expr value;

    @Override
    public void visit(Visitor visitor) {
        visitor.visitAssign(this);
    }

    @Override
    public <T> T visit(ResultVisitor<T> visitor) {
        return visitor.visitAssign(this);
    }
}
