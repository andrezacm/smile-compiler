package br.ufrn.smile.domain.validators;

import br.ufrn.smile.domain.Actor;
import br.ufrn.smile.domain.Association;

public class OccupiesValidator implements AssociationValidator {
    @Override
    public Boolean isValid(Actor mainActor, Association association) {
        if (mainActor.getType() != Actor.ActorType.AGENT) {
            return false;
        }

        return association.getActor().getType() == Actor.ActorType.POSITION;
    }
}
