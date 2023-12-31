package org.hummingbyte.core.visitor.stringifier;

import org.cyon.core.parser.ast.*;
import org.hummingbyte.core.visitor.Visitor;
import org.hummingbyte.core.parser.ast.*;

public class PrettyStringifier implements Visitor, Stringifier {
    private static final int WIDTH = 4;
    private final StringBuilder sb = new StringBuilder();
    private int depth = 0;
    private boolean init = true;
    private boolean pretty;

    public PrettyStringifier(boolean pretty){
        this.pretty = pretty;
    }

    @Override
    public String stringify(Expr expr){
        sb.setLength(0);
        expr.visit(this);
        return sb.toString();
    }

    private void increase(){
        depth+=WIDTH;
        nl();
    }

    private void decrease(){
        depth-=WIDTH;
        nl();
    }

    private void nl(){
        if(!pretty) return;
        sb.append("\n");
        init = true;
    }

    private void print(String str){
        if(pretty && init){
            init = false;
            sb.append(" ".repeat(depth));
        }

        sb.append(str);
    }

    @Override
    public void visitList(ListExpr list) {
        print("[");
        if(list.getElems().length > 0){
            increase();
            boolean remaining = false;
            for(var elem : list.getElems()){
                if(remaining){
                    print(",");
                    nl();
                }
                remaining = true;
                elem.visit(this);
            }
            decrease();
        }
        print("]");
    }

    @Override
    public void visitObject(ObjectExpr obj) {
        print("{");
        if(obj.getPairs().length > 0) {
            increase();
            boolean remaining = false;
            for (var pair : obj.getPairs()) {
                if (remaining) {
                    print(",");
                    nl();
                }
                remaining = true;
                pair.visit(this);
            }
            decrease();
        }
        print("}");
    }

    public void printEscaped(String str){
        for( char cha : str.toCharArray() ){
            switch (cha){
                case '\n': sb.append("\\n"); break;
                case '\r': sb.append("\\r"); break;
                case '\t': sb.append("\\t"); break;
                case '\f': sb.append("\\f"); break;
                case '\b': sb.append("\\b"); break;
                case '\"': sb.append("\\\""); break;
                case '\\': sb.append("\\\\"); break;
                default: sb.append(cha);
            }
        }
    }

    @Override
    public void visitLiteral(LiteralExpr literal) {
        var value = literal.getValue();
        if(value == null){
            print("null");
        }else if(value instanceof String){
            print("\"");
            printEscaped((String) value);
            print("\"");
        }else{
            print(value.toString());
        }
    }

    @Override
    public void visitIdent(IdentExpr ident) {
        print("$");
        print(ident.getKey());
    }

    @Override
    public void visitAssign(AssignExpr assign) {
        assign.getKey().visit(this);
        if(pretty){
            print(" = ");
        }else{
            print("=");
        }
        assign.getValue().visit(this);
    }

    @Override
    public void visitBlock(BlockExpr block) {
        var remaining = false;
        for(var expr : block.getExprs()){
            if(remaining){
                print(";");
                nl();
            }
            expr.visit(this);
            remaining = true;
        }
    }

    @Override
    public void visitPair(PairExpr pair) {
        pair.getKey().visit(this);
        if(pretty){
            print(" : ");
        }else{
            print(":");
        }
        pair.getValue().visit(this);
    }
}
