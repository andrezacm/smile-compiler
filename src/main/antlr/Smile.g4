grammar Smile;

actorStatement : actorDeclaration NL
                 (associationDeclaration)*
                 NL
                 externalRelationships
                 EOF;

actorDeclaration : actorType actorName (',')? ;
actorType : ACTOR | AGENT | POSITION | ROLE ;
actorName : ID ;

associationDeclaration : associationType '(' (actorDeclaration)+ ')' NL? ;
associationType : COVERS | ISA | INSTANCEOF | ISPARTOF | OCCUPIES | PLAYS ;

externalRelationships : 'ExternalRelationships' NL
					    (dependerPerspective)*
					    (dependeePerspective)* ;

dependerPerspective : dependencyDependerPerspective dependerDependencyDeclaration NL? ;
dependeePerspective : dependencyDependeePerspective dependeeDependencyDeclaration NL? ;

dependerDependencyDeclaration : '(' actorDeclaration ')' 'for' '(' dependencyType dependencyName ')' ;
dependeeDependencyDeclaration : '(' dependencyType dependencyName ')' 'to' '(' actorDeclaration ')';

dependencyDependerPerspective : DEPENDSON ;
dependencyDependeePerspective : CARRIESOUT | PROVIDES | REACHES ;
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

// Dependency perspective for depender and dependee
CARRIESOUT  : 'carriesOut' ;
DEPENDSON 	: 'dependsOn' ;
PROVIDES 	: 'provides' ;
REACHES 	: 'reaches' ;

ID : [a-zA-Z]+ ;
WS : [ \t]+ -> skip ;
NL : [\r\n];
COMMENT : '/*' .*? '*/' -> skip;