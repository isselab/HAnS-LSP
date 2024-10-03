grammar FeatureTree;


//Parser rules
featuretree:  feature |
            feature ((WS featuretree )+ | (WS featuretree )* (LO (featuretree)(WS featuretree)+))
;

feature: FEATURENAME (WS OPTIONAL )?;

//lexer rules
WS: [ \t\r\n]+ -> skip;
LO: 'xor'
    | 'or'
;
FEATURENAME:
    [a-zA-Z_][a-zA-Z_0-9:]*;
OPTIONAL: '?';

//grfg
    //ggg ?
        //gGegeg