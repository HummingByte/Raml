package org.hummingbyte.core.mapper;

import org.hummingbyte.core.parser.Parser;
import org.hummingbyte.core.visitor.Serializer;

public class Mapper {
    public static <T> T readValue(String str, Class<T> clazz){
        var expr = Parser.parse(str);
        return expr.deserialize(clazz);
    }

    public static String writeValue(Object obj){
        return writeValue(obj, false);
    }

    public static String writeValue(Object obj, boolean pretty){
        var serializer = new Serializer();
        var expr = serializer.serialize(obj);
        var expanded = expr.expand();
        return expanded.stringify(pretty);
    }
}
