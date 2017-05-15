package br.ufrn.smile.domain;

import br.ufrn.smile.domain.validators.AssociationValidator;

public class AssociationFactory {
    public static AssociationValidator getValidator(Association.AssociationType associationType) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String associationName = associationType.getDescription();
        String validatorName = associationName.substring(0, 1).toUpperCase() + associationName.substring(1) + "Validator";

        Class<?> klass = Class.forName("br.ufrn.smile.domain.validators." + validatorName);
        return (AssociationValidator) klass.newInstance();
    }
}
