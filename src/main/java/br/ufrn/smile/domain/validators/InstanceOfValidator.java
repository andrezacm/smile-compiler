package br.ufrn.smile.domain.validators;

import br.ufrn.smile.domain.Actor;
import br.ufrn.smile.domain.Association;

public class InstanceOfValidator implements AssociationValidator {
    @Override
    public Boolean isValid(Actor mainActor, Association association) {
        if (mainActor.getType() != Actor.ActorType.AGENT) {
            return false;
        }

        return association.getActors().stream().allMatch(actor -> actor.getType() == Actor.ActorType.AGENT);
    }
}
