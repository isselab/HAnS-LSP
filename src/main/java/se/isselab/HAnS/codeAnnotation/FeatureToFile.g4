grammar FeatureToFile;


//Parser rules
document: file Newline features;

file: Dateiname;

features: FEATURENAME (Kommer FEATURENAME)* ;

//lexer rules
WS: [ \t] -> skip;
Newline:[\r\n] | [\n];
FEATURENAME:
    [a-zA-Z_][a-zA-Z_0-9:]*;
Kommer: ',';
Dateiname: [a-zA-Z_][a-zA-Z_0-9:./]*  ;