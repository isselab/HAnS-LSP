grammar FeatureTree;


//Parser rules
featermodle:
    featuretree+
;
featuretree: feature Newline  (((WS+ subfeature Newline)+) | ((WS+ subfeature Newline )* (orxor))) | feature Newline;

subfeature: featuretree Newline | feature Newline ;

orxor: LO (feature Newline )(WS+ feature Newline)+ ;

feature: FEATURENAME WS (OPTIONAL)? Newline;

//lexer rules
WS: [ \r\t] ;

Newline : [\n] ;


LO: 'xor'
    | 'or'
;
FEATURENAME:
    [a-zA-Z_][a-zA-Z_0-9:]*;
OPTIONAL: '?';

//grfg
    //grdfstx
        //gGegeg        W,#;WW
        //gGfdff        e,#;WW
            //ggr       W,#;WWW
                //ggdegdg   W,#;WWWW
    //gdfsv
    //or fefed
    //desf