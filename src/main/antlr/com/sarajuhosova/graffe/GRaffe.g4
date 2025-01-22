grammar GRaffe;

parse : line* EOF;

line : Space* (statement | linebreak);

statement : name Space* '{' Space* properties Space* '}';
  
name : AlphaNum+ ;

properties
    :
    | property
    | linebreak* (Space* property? linebreak*)
    ;

property : name Space* ':' Space* string;

string : '"' ( '\\"' | . )*? '"';

linebreak
    : '\r'? '\n'
    | '\r'
    ;

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
