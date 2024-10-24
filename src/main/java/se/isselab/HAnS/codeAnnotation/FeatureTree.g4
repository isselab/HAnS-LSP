grammar FeatureTree;


//Parser rules
featuretree: feature ((featuretree )+ | ( featuretree )* (LO (featuretree)( featuretree)+)) | feature;

feature: FEATURENAME (OPTIONAL )?;

//lexer rules
WS: [ \r\n\t]+ -> skip;
LO: 'xor'
    | 'or'
;
FEATURENAME:
    [a-zA-Z_][a-zA-Z_0-9:]*;
OPTIONAL: '?';