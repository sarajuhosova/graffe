grammar GRaffe;

parse : declaration* EOF;

declaration : Name '{' statement* '}';

statement
    : property
    | declaration
    ;

property : Name ':' value ';';

value : stringValue ;

stringValue : String ;

String : '"' ( '\\"' | . )*? '"';

Name : AlphaNum+ ;

Comment : '/*' .*? '*/' -> skip;
InlineComment : '//' .*? ('\r'? '\n' | '\n' | EOF) -> skip;

Space :  [ \t\f]+ -> skip;
LineBreak: [\r\n] -> skip;

AlphaNum
    :  'a'..'z'
    |  'A'..'Z'
    |  '0'..'9'
    ;
