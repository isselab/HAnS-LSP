grammar FeatureTree;


//Parser rules
featuremodel:
    ( featuretree | feature FreeLine )+
;
featuretree: ((feature NewLine (((subfeature) (FreeLine subfeature)*) | NewLine ((subfeature) (FreeLine subfeature)* (FreeLine orxor )))FreeLine)| (orxor FreeLine));

subfeature: featuretree | feature;

orxor: LO feature ((NewLine feature) (NewLine feature)+) ;

feature:  FEATURENAME (OPTIONAL)? NewLine ;

//lexer rules
WS: [\r\t] -> skip ;

FreeLine: NewLine NewLine;

NewLine: [\n];

LO: 'xor'
    | 'or'
;
FEATURENAME:
    [a-zA-Z_][a-zA-Z_0-9:]*;
OPTIONAL: '?';

//grfg
    //grdfstx
        //gGegeg
        //gGfdff
            //ggr
                //ggdegdg
    //gdfsv
    //or fefed
    //desf