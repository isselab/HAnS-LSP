grammar FeatureToFile;

//Parser rules
document: (mapping (Newline mapping)+)|(mapping);

mapping: (files Newline features);

files:(file (WS* Kommer WS* file)+) | (file)  ;

file: String '.' String;

features: (feature (WS* Kommer WS* feature)+) | (feature) ;

feature:(String ('::' String)+) | (String);

//lexer rules
WS: [ \t];
Newline:[\n\r];
String:
    [a-zA-Z_][a-zA-Z_0-9]*;
Kommer: ',';

/*
    test.java
    test1
    kp.java
    test2


*/