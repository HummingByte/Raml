# Raml (Reference Associative Markup Language)

Cycles in Json and XML can by represented using workarounds which have to be resolved before using the deserialized object.

```javascript
{
    "obj" : {
        "myself" : {
            "$ref": "#.obj"
        }
    }   
}
```

In this Project we expand the JSON format to have a compact readable format which can resolve references.
Like in Programming Languages we use Variables to bind the objects.
The difference to programming languages is, that the variables will bind in any order of the objects in a raml format.

```javascript
abc123 = { "obj": abc123 }  
```

Not only one but arbitrary many definitions are possible and have to be seperated using a semicolon

```javascript
lhs = {"other" : rhs};
rhs = {"other" : lhs}
```

The last seperated value is the result of a deserialization.
To change the result you also can use an identifier of a object.

```javascript
lhs = {"other" : rhs};
rhs = {"other" : lhs};
lhs
```

The value you can assign to an identifier covers the entire JSON syntax including identifier as references.

```javascript
self = {
    "some" : {
        "deep" : {
            "nested" : {
                "object" : self, 
                "with" : ["a", "list", "including", self]
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