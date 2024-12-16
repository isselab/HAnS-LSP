//&Begin[]
//&Line[]
//&End[]



grammar TextDocument;
options { caseInsensitive = true; }

//Parser rules
document: .*? (block)*;

block: AND type '[' features ']' .*? ;

type: (Begin |End | Line) ;

features: feature(',' feature)* ;

feature: FEATURENAME ('::' FEATURENAME)*;

//lexer rules


WS: [ \t\r\n] -> skip;

AND: '&';

Begin: 'Begin';
End:'End';
Line:'Line';

FEATURENAME: [a-z_][a-z_0-9:]*;

Text: . -> skip;