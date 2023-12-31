package org.hummingbyte.core.visitor;

import org.cyon.core.parser.ast.*;
import org.hummingbyte.core.parser.ast.*;

public class Binder implements Visitor{
    public void bind(Expr expr){
        expr.visit(this);
    }

    @Override
    public void visitAssign(AssignExpr assign) {
        var key = assign.getKey();
        if(key.getAlt() != null){
            throw new RuntimeException("Assign twice not possible!");
        }
        key.setAlt(assign.getValue());
    }

    @Override
    public void visitBlock(BlockExpr block) {
        for(var elem : block.getExprs()){
            elem.visit(this);
        }
    }

    @Override
    public void visitList(ListExpr list) {}

    @Override
    public void visitObject(ObjectExpr obj) {}

    @Override
    public void visitLiteral(LiteralExpr literal) {}

    @Override
    public void visitIdent(IdentExpr ident) {}

    @Override
    public void visitPair(PairExpr block) {}
}
