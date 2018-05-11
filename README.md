## LookupField FilterPredicate API Demo

### Abstract

`LookupField` implementations have an API that allows to set custom
filtering strategy via checking whether item caption matches to search
string:
```java
lookupField.setFilterPredicate((itemCaption, searchString) -> {
    // custom logic here
});
```

### Demo
This repository contains two samples:

- basic example of use (Application -> Predicate Demo)
- ignoring accents in names (Application -> Ignore accents Demo)

The first sample demonstrates how custom strategies (`String::contains`,
`String::startsWith`, `String::endsWith`) affect filtering.

The second sample demonstrates how FilterPredicate API can be used to
ignore letters with accents in French names.