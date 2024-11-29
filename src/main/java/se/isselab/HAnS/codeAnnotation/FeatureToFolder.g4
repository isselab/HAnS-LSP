grammar FeatureToFolder;


//Parser rules


features: FEATURENAME (Kommer FEATURENAME)*;



//lexer rules
WS: [ \t\r\n] -> skip;
FEATURENAME:
    [a-zA-Z_][a-zA-Z_0-9:]*;
Kommer: ',';