grammar FeatureToFile;


//Parser rules
document: ( mapping)*;

mapping: (files Newline features Newline);

files:Dateiname (Kommer WS* Dateiname)* ;

features: feature (WS* Kommer WS* feature)* ;

feature:FEATURENAME ('::' FEATURENAME)*;

//lexer rules
WS: [ \t];
Newline:[\r\n] | [\n];
FEATURENAME:
    [a-zA-Z_][a-zA-Z_0-9]*;
Kommer: ',';
Dateiname: [a-zA-Z_][a-zA-Z_0-9:./]*  ;

/*
    test.java
    test1
    kp.java
    test2


*/