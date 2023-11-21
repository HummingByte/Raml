package org.hummingbyte.core;

import org.hummingbyte.core.exception.ParseException;
import org.hummingbyte.core.parser.Parser;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

public class EOLParserTest {

    public void assertParseException(String str){
        assertThrows(ParseException.class, () -> Parser.parseRaw(str));
    }

    @Test(timeout = 100)
    public void objectEOLAfterKey(){
        assertParseException("{\"key\"");
    }

    @Test(timeout = 100)
    public void objectEOLAfterColon(){
        assertParseException("{\"key\" :");
    }

    @Test(timeout = 100)
    public void objectEOLAfterValue(){
        assertParseException("{\"key\" : \"val\"");
    }

    @Test(timeout = 100)
    public void objectEOLAfterComma(){
        assertParseException("{\"key\" : \"val\",");
    }

    @Test(timeout = 100)
    public void arrayEOLWhileEntry(){
        assertParseException("[\"test");
    }

    @Test(timeout = 100)
    public void arrayEOLAfterStart(){
        assertParseException("[");
    }

    @Test(timeout = 100)
    public void arrayEOLAfterEntry(){
        assertParseException("[\"key\"");
    }

    @Test(timeout = 100)
    public void arrayEOLAfterComma(){
        assertParseException("[\"key\",");
    }
}
