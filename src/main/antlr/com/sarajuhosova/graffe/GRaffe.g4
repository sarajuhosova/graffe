grammar GRaffe;

parse : line* EOF;

line : Space* (statement | linebreak);

statement : Name Space* '{' Space* properties Space* '}';

properties
    :
    | property
    | linebreak* (Space* property? linebreak*)
    ;

property : Name Space* ':' Space* value;

value : stringValue ;

stringValue : String ;

linebreak
    : '\r'? '\n'
    | '\r'
    ;

String : '"' ( '\\"' | . )*? '"';

Name : AlphaNum+ ;

Comment : '/*' .*? '*/' -> skip;
InlineComment : '//' .*? ('\r'? '\n' | '\n' | EOF) -> skip;

Space
    :  ' '
    |  '\t'
    |  '\f'
    ;

AlphaNum
    :  'a'..'z'
    |  'A'..'Z'
    |  '0'..'9'
    ;
