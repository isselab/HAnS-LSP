//&Begin[]
//&Line[]
//&End[]

grammar TextDocument;

//Parser rules
document: (block | text+? )+;

block: (begin | end | line) '[' feature ']' ;

feature: FEATURENAME;

begin: Begin;
end: End;
line: Line;

text: Text;


//lexer rules


WS: [ \t\r\n] -> skip;

Begin: '&Begin';
End:'&End';
Line:'&Line';

FEATURENAME: [a-zA-Z_][a-zA-Z_0-9:]*;

Text: [a-zA-Z_][a-zA-Z_0-9:] .*?;