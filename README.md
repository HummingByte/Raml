# Raml (Reference Associative Markup Language)

Cycles in Json and XML can by represented using workarounds which have to be resolved before using the deserialized object.

```json
{
    "obj" : {
        "myself" : {
            "$ref": "#.obj"
        }
    }   
}
```

In this Project we expanded the JSON logic to have a compact readable format which can resolve references.
Like in Programming Languages we use Variables to bind the objects.
The difference is, that the variables will bind in any order of the objects in a raml format.

```
abc123 = { "obj": abc123 }  
```

Not only one but arbitrary many definitions are possible and have to be seperated using a semicolon

```json
lhs = {"other" : rhs};
rhs = {"other" : lhs}
```

The last seperated value is the result of a deserialization.
To change the result you also can use a identifier of a object.

```json
lhs = {"other" : rhs};
rhs = {"other" : lhs};
lhs
```

The value you can assign to a identifier covers the entire JSON syntax inclusind identifier as references.

```json
self = {
    "some" : {
        "deep" : {
            "nested" : {
                "object" : self, 
                "with" : ["a", "list"]
            }
        }
    }
}
```

Like the Jackson library you can use
```kotlin
var obj = Mapper.readValue(ramlStr, DummyObject.class)
```
to deserialize an object and
```kotlin
var str = Mapper.writeValue(dummy);
```
to serialize an object.

# Contribute

Contributions from the community are welcome and appreciated.