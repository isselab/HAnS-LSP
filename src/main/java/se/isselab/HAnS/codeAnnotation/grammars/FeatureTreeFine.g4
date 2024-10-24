grammar FeatureTree;


//Parser rules
featuretree: feature (((subfeature )+) | ((subfeature )* (orxor))) | feature;

subfeature: featuretree | feature ;

orxor: LO (feature)( feature)+  ;

feature: FEATURENAME (OPTIONAL)?;

//lexer rules
WS: [ \r\n\t] + -> skip;
LO: 'xor'
    | 'or'
;
FEATURENAME:
    [a-zA-Z_][a-zA-Z_0-9:]*;
OPTIONAL: '?';

//grfg
    //ggg ?
        //gGegeg