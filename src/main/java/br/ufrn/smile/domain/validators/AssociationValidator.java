package br.ufrn.smile.domain.validators;

import br.ufrn.smile.domain.Actor;
import br.ufrn.smile.domain.Association;

public interface AssociationValidator {
    Boolean isValid(Actor actor, Association association);
}
