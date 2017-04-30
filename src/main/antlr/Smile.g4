grammar Smile;

actorStatement : actorDeclaration NL
				 (actorAssociationDeclaration)*
				 EOF;

actorDeclaration : actorType actorName (',')? ;
actorType : ACTOR | AGENT | POSITION | ROLE ;
actorName : ID ;

actorAssociationDeclaration : associationType '(' (actorDeclaration)+ ')' NL? ;
associationType : COVERS | ISA | INSTANCEOF | ISPARTOF | OCCUPIES | PLAYS ;

dependencyDeclaration : '(' actorDeclaration ')' 'for' '(' dependencyType dependencyName ')' ;
dependencyType : GOAL | RESOURCE | SOFTGOAL | TASK ;
dependencyName : ID ;

// Actor types
ACTOR 	 : 'actor';
AGENT 	 : 'agent';
POSITION : 'position';
ROLE 	 : 'role';

// Association types
COVERS 	   : 'covers' ;
ISA 	   : 'isA' ;
INSTANCEOF : 'instanceOf' ;
ISPARTOF   : 'isPartOf' ;
OCCUPIES   : 'occupies' ;
PLAYS 	   : 'plays' ;

// Dependency types
GOAL	 : 'goal' ;
RESOURCE : 'resource' ;
SOFTGOAL : 'softgoal' ;
TASK	 : 'task' ;

ID : [a-zA-Z]+ ;
WS : [ \t]+ -> skip ;
NL : [\r\n];
COMMENT : '/*' .*? '*/' -> skip;