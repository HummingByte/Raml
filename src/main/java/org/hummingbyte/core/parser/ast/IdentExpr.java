package org.hummingbyte.core.parser.ast;

import lombok.Getter;
import lombok.Setter;
import org.hummingbyte.core.visitor.ResultVisitor;
import org.hummingbyte.core.visitor.Visitor;

@Getter
@Setter
public class IdentExpr extends Expr{
    private String key;

    public IdentExpr(){}

    public IdentExpr(String key){
        this.key = key;
    }

    @Override
    public void visit(Visitor visitor) {
        visitor.visitIdent(this);
    }

    @Override
    public <T> T visit(ResultVisitor<T> visitor) {
        return visitor.visitIdent(this);
    }
}
