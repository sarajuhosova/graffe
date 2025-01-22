grammar GRaffe;

parse : declaration* EOF;

declaration
    : componentDecl
    | relationshipDecl
    | includeDecl
    ;

componentDecl : Name ('{' statement* '}' | EOL);
relationshipDecl : Name Arrow Name ('{' property* '}' | EOL);
includeDecl : 'include' Name+ EOL;

statement
    : property
    | declaration
    ;

property : Name ':' value EOL;

value : stringValue;
stringValue : String ;

EOL : ';';

String : '"' ( '\\"' | . )*? '"';

Arrow
    : '->'
    | '<-'
    | '<->'
    | '--'
    ;

Name : AlphaNum+ ;

AlphaNum
    :  'a'..'z'
    |  'A'..'Z'
    |  Digit
    ;

Digit : '0'..'9';

Comment : '/*' .*? '*/' -> skip;
InlineComment : '//' .*? ('\r'? '\n' | '\n' | EOF) -> skip;

Space :  [ \t\f]+ -> skip;
LineBreak: [\r\n] -> skip;