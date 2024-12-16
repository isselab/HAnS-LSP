grammar FeatureToFolder;

//Parser rules
features: feature ((WS)* Kommer (WS)+ feature)* ;

feature:(FEATURENAME ('::' FEATURENAME)+) | (FEATURENAME);

//lexer rules

WS: [ \t];
Newline:[\n\r] -> skip ;
FEATURENAME:
    [a-zA-Z_][a-zA-Z_0-9]*;
Kommer: ',';

