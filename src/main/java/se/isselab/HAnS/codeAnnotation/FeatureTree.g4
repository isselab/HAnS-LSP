grammar FeatureTree;


//Parser rules
featuretree: feature+;

feature: (WS* (LO WS)? FEATURENAME (WS OPTIONAL)? WS* Newline) | (WS* Newline);



//lexer rules
WS: [ \t];
Newline:[\r\n] | [\n];
LO: 'xor'
    | 'or'
;
FEATURENAME:
    [a-zA-Z_][a-zA-Z_0-9:]*;
OPTIONAL: '?';
/*
Test1
    test2

    Test3

Testa ?

Testb

Test4
    Test5
        Test6

        Test7
            Test8

            Test9

            Testf

    Testc

Testd

Teste
*/

//grvffr
    //gdf ?

    //fgd

//fdfg
    //or fvd
    //gfdv
    //fdg

    //ghefrw
//dfsv

