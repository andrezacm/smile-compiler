package br.ufrn.smile.domain.validators;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.when;

import br.ufrn.smile.domain.Actor;
import br.ufrn.smile.domain.Association;

@RunWith(MockitoJUnitRunner.class)
public class InstanceOfValidatorTest {
	@Mock Actor actor;
	@Mock Association association;
	
	@Test public void isValidTest() {
		when(actor.getType()).thenReturn(Actor.ActorType.AGENT);
		when(association.getActor()).thenReturn(new Actor(1, "ActorOne", "agent"));
		
		assertTrue(new InstanceOfValidator().isValid(actor, association));
	}
	
	@Test public void isNotValidTest() {
		when(actor.getType()).thenReturn(Actor.ActorType.AGENT);
		when(association.getActor()).thenReturn(new Actor(1, "ActorOne", "role"));
		
		assertFalse(new InstanceOfValidator().isValid(actor, association));
	}
}