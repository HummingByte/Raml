package org.hummingbyte.core;

import org.hummingbyte.core.dummy.CyclicDummy;
import org.hummingbyte.core.dummy.MapDummy;
import org.hummingbyte.core.dummy.PrimaryArrayDummy;
import org.hummingbyte.core.dummy.PrimaryDummy;
import org.hummingbyte.core.mapper.Mapper;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class MapperTest {

    @Test
    public void simpleMapString(){
        var str = Mapper.readValue("a = \"2\"; a", String.class);
        assertEquals("2", str);
    }

    @Test
    public void simpleMapInteger(){
        var integer = Mapper.readValue("a = 2; a", Integer.class);
        assertEquals(2, (int)integer);
    }

    @Test
    public void simpleMapBoolean(){
        var bool = Mapper.readValue("a = true; a", Boolean.class);
        assertEquals(true, bool);
    }

    @Test
    public void objectMap(){
        var dummy = Mapper.readValue("{\"str\": \"test\", \"number\": 42, \"bool\": true}", PrimaryDummy.class);
        assertIdentical(new PrimaryDummy("test", 42, true), dummy);
    }

    @Test
    public void objectCyclicMap(){
        var dummy = Mapper.readValue("a = {\"parent\": a, \"number\": 42}", CyclicDummy.class);
        assertSame(dummy, dummy.getParent());
        assertEquals(42, dummy.getNumber());
    }

    @Test
    public void mapMap(){
        var dummy = Mapper.readValue("{\"map\": {\"str\": \"test\", \"number\": 42, \"bool\": true}}", MapDummy.class);
        assertIdentical(Map.of("str", "test", "number", 42, "bool", true), dummy.getMap());
    }

    @Test
    public void mapCyclicMap(){
        var dummy = Mapper.readValue("a = {\"parent\": a, \"number\": 42}", Map.class);
        assertIdentical(dummy, dummy.get("parent"));
    }

    @Test
    public void array(){
        var dummy = Mapper.readValue("[1,2,3]", int[].class);
        assertArrayEquals(new int[]{1,2,3}, dummy);
    }

    @Test
    public void cyclicArray(){
        var dummy = Mapper.readValue("a = [a,2,3]", Object[].class);
        assertSame(dummy, dummy[0]);
        assertEquals(2, dummy[1]);
        assertEquals(3, dummy[2]);
    }

    @Test
    public void serialize(){
        var dummy = new PrimaryDummy("test", 42, true);
        var str = Mapper.writeValue(dummy);
        var dummyParsed = Mapper.readValue(str, PrimaryDummy.class);
        assertIdentical(dummy, dummyParsed);
    }

    @Test
    public void serializeCyclic(){
        var dummy = new CyclicDummy(null, 10);
        dummy.setParent(dummy);
        var str = Mapper.writeValue(dummy);
        var dummyParsed = Mapper.readValue(str, CyclicDummy.class);
        assertIdentical(dummy, dummyParsed);
        assertSame(dummyParsed, dummyParsed.getParent());
        assertEquals(dummy.getNumber(), dummyParsed.getNumber());
    }

    @Test
    public void primaryArray(){
        var expectedDummy = new PrimaryArrayDummy(new int[]{1,2,3}, new double[]{1.1, 2.2, 3.3});
        var dummy = Mapper.readValue("{\"ints\": [1,2,3], \"reals\": [1.1,2.2,3.3]}", PrimaryArrayDummy.class);
        assertIdentical(expectedDummy, dummy);
    }

    @Test
    public void nullTest(){
        var expectedDummy = new PrimaryDummy(null, 1, false);
        var dummy = Mapper.readValue("{\"str\": null, \"number\": 1, \"bool\": false}", PrimaryDummy.class);
        assertIdentical(expectedDummy, dummy);
    }

    private void assertIdentical(Object expected, Object actual){
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }
}
