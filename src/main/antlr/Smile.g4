grammar Smile;

actorDeclaration : 'actor' actorName EOF ;
actorName : ID ;

ID : [a-zA-Z]+ ;
WS : [ \t\r\n]+ -> skip ;
COMMENT : '/*' .*? '*/' -> skip;