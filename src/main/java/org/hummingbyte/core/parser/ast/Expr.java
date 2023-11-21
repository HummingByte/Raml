package org.hummingbyte.core.parser.ast;

import lombok.Getter;
import lombok.Setter;
import org.cyon.core.visitor.*;
import org.hummingbyte.core.visitor.*;
import org.hummingbyte.core.visitor.stringifier.PrettyStringifier;

@Getter
@Setter
public abstract class Expr {
    public static final Expr[] EMPTY_ARRAY = new Expr[0];

    private Expr alt;

    public void toggle(Expr value){
        alt = alt == null ? value : null;
    }

    public abstract void visit(Visitor visitor);
    public abstract <T> T visit(ResultVisitor<T> visitor);

    public final Expr expand(){
        var expander = new Expander();
        return expander.expand(this);
    }

    public final Expr reduce(){
        var reducer = new Reducer();
        return reducer.reduce(this);
    }

    public final <T> T deserialize(Class<T> clazz){
        var deserializer = new Deserializer();
        return deserializer.deserialize(this, clazz);
    }

    public final String stringify(boolean pretty){
        var stringifier = new PrettyStringifier(pretty);
        return stringifier.stringify(this);
    }
}
