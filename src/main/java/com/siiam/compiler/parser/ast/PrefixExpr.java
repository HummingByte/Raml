package com.siiam.compiler.parser.ast;

import com.siiam.compiler.exception.InterpreterException;
import com.siiam.compiler.parser.Op;
import com.siiam.compiler.scope.Scope;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PrefixExpr implements Expr{
    private Expr expr;
    private Op op;

    private Object sub(Object value){
        if(value instanceof Integer){
            return -((Integer) value);
        }

        throw new InterpreterException("Expected integer for unary sub!");
    }

    @Override
    public Object eval(Scope scope) {
        var val = expr.eval(scope);

        switch (op){
            case Sub: return sub(val);
            case Add: return val;
            case Not: return Boolean.FALSE.equals(val);
        }

        throw new InterpreterException("Not implemented " + op + " operation!");
    }
}