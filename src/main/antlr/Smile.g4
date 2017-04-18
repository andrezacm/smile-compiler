grammar Smile;

actorDeclaration : actorType actorName EOF ;
actorType : ACTOR | AGENT | POSITION | ROLE ;
actorName : ID ;

// Actor types
ACTOR :	'actor';
AGENT : 'agent';
POSITION : 'position';
ROLE : 'role';

ID : [a-zA-Z]+ ;
WS : [ \t\r\n]+ -> skip ;
COMMENT : '/*' .*? '*/' -> skip;