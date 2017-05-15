package br.ufrn.smile.domain.validators;

import br.ufrn.smile.domain.Actor;
import br.ufrn.smile.domain.Association;

public class IsPartOfValidator implements AssociationValidator {
    @Override
    public Boolean isValid(Actor mainActor, Association association) {
        return association.getActor().getType() == mainActor.getType();
    }
}
