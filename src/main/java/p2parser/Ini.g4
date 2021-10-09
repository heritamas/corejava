grammar Ini;

file: section* EOF
	;

section: header definition*
	;

header : '[' ID ']' NEWLINE
	;

definition :
	ID '=' VALUE NEWLINE    # validDefinition
	| NEWLINE               # emptyDefinition
	;

ID : [\p{L}]+ ;
VALUE : [\p{L}\p{N}\p{S}\p{Pd}\p{Po}]+ ;
DOUBLE : '"' .*? '"'   -> type(VALUE) ;
SINGLE : '\'' .*? '\'' -> type(VALUE) ;
NEWLINE : [\n\r] ;
WS : [\p{White_Space}] -> skip ;

