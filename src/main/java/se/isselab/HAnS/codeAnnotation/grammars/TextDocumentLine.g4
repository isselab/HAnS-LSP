grammar TextDocumentLine;

//Parser rules
document: ( text* block NEWLINE | text NEWLINE)+?;



block: (AND annotation text)|(AND text)  ;

annotation: (begin | end | line) '[' feature ']';

feature: FEATURENAME;

begin: Begin;
end: End;
line: Line;

text: Text;


//lexer rules

WS: [ \t] -> skip;

NEWLINE: [\r\n] | [\n];

Begin: 'Begin';
End:'End';
Line:'Line';

AND:'&';

FEATURENAME: [a-zA-Z_][a-zA-Z_0-9:]*;

Text: [a-zA-Z_][a-zA-Z_0-9:] .*?;