package br.ufrn.smile.domain.validators;

import br.ufrn.smile.domain.Actor;
import br.ufrn.smile.domain.Association;

public class CoversValidator implements AssociationValidator {
    @Override
    public Boolean isValid(Actor mainActor, Association association) {
        if (mainActor.getType() != Actor.ActorType.POSITION) {
            return false;
        }

        return association.getActors().stream().allMatch(actor -> actor.getType() == Actor.ActorType.ROLE);
    }
}
