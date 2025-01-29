grammar GRaffe;

program : declaration* EOF;

declaration
    : componentDecl
    | relationshipDecl
    | includeDecl
    | restrictionDecl
    ;

componentDecl : Name ('{' statement* '}' | EOL);
relationshipDecl : Name Arrow Name ('{' property* '}' | EOL);
includeDecl : 'include' qname+ EOL;
restrictionDecl : '#' Restriction '!';

qname : Name ('.' Name)*;

statement
    : property
    | declaration
    ;

property : Name ':' value EOL;

value : stringValue;
stringValue : String ;

EOL : ';';

String : '"' ( '\\"' | . )*? '"';

Restriction : 'tree' ;

Arrow
    : '->'
    | '<-'
    | '<>'
    | '--'
    ;

Name : (AlphaNum | '_')+ ;

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
