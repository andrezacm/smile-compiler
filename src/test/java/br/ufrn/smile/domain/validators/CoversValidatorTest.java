package br.ufrn.smile.domain.validators;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.when;

import java.util.Arrays;
import java.util.List;

import br.ufrn.smile.domain.Actor;
import br.ufrn.smile.domain.Association;

@RunWith(MockitoJUnitRunner.class)
public class CoversValidatorTest {
	@Mock Actor actor;
	@Mock Association association;
	
	@Test public void isValidTest() {
		List<Actor> actors = Arrays.asList(new Actor("ActorOne", "role"), new Actor("ActorTwo", "role"));
		
		when(actor.getType()).thenReturn(Actor.ActorType.POSITION);
		when(association.getActors()).thenReturn(actors);
		
		assertTrue(new CoversValidator().isValid(actor, association));
	}
	
	@Test public void isNotValidTest() {
		List<Actor> actors = Arrays.asList(new Actor("ActorOne", "actor"), new Actor("ActorTwo", "role"));
		
		when(actor.getType()).thenReturn(Actor.ActorType.POSITION);
		when(association.getActors()).thenReturn(actors);
		
		assertFalse(new CoversValidator().isValid(actor, association));
	}
}