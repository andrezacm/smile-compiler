package br.ufrn.smile.domain;
import org.junit.Test;

import br.ufrn.smile.domain.validators.*;

import static org.junit.Assert.*;

public class AssociationFactoryTest {
    @Test public void testGetCoversValidator() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    	AssociationValidator validator = AssociationFactory.getValidator(Association.AssociationType.COVERS);
    	AssociationValidator expectedValidator = new CoversValidator();
    	
    	assertEquals(validator.getClass(), expectedValidator.getClass());
    }
    
    @Test public void testGetInstanceOfValidator() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    	AssociationValidator validator = AssociationFactory.getValidator(Association.AssociationType.INSTANCEOF);
    	AssociationValidator expectedValidator = new InstanceOfValidator();
    	
    	assertEquals(validator.getClass(), expectedValidator.getClass());
    }
    
    @Test public void testGetIsAValidator() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    	AssociationValidator validator = AssociationFactory.getValidator(Association.AssociationType.ISA);
    	AssociationValidator expectedValidator = new IsAValidator();
    	
    	assertEquals(validator.getClass(), expectedValidator.getClass());
    }
    
    @Test public void testGetIsPartOfValidator() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    	AssociationValidator validator = AssociationFactory.getValidator(Association.AssociationType.ISPARTOF);
    	AssociationValidator expectedValidator = new IsPartOfValidator();
    	
    	assertEquals(validator.getClass(), expectedValidator.getClass());
    }
    
    @Test public void testGetOccupiesValidator() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    	AssociationValidator validator = AssociationFactory.getValidator(Association.AssociationType.OCCUPIES);
    	AssociationValidator expectedValidator = new OccupiesValidator();
    	
    	assertEquals(validator.getClass(), expectedValidator.getClass());
    }
    
    @Test public void testGetPlaysValidator() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    	AssociationValidator validator = AssociationFactory.getValidator(Association.AssociationType.PLAYS);
    	AssociationValidator expectedValidator = new PlaysValidator();
    	
    	assertEquals(validator.getClass(), expectedValidator.getClass());
    }
}
