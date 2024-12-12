grammar FeatureToFolder;


//Parser rules


features: feature WS+ (feature)*;

feature: FEATURENAME ('::' FEATURENAME)*;


//lexer rules
WS: [ \t\r\n];
FEATURENAME:
    [a-zA-Z_][a-zA-Z_0-9]*;
Kommer: ',';