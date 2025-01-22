grammar GRaffe;

parse : declaration* EOF;

declaration
    // declarations of components
    : Name ('{' statement* '}' | EOL)
    // declarations of relationships
    | Name Arrow Name ('{' property* '}' | EOL)
    // imports
    | 'include' Name+ EOL
    ;

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