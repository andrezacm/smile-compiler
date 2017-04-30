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
public class OccupiesValidatorTest {
	@Mock Actor actor;
	@Mock Association association;
	
	@Test public void isValidTest() {
		List<Actor> actors = Arrays.asList(new Actor("ActorOne", "position"), new Actor("ActorTwo", "position"));
		
		when(actor.getType()).thenReturn(Actor.ActorType.AGENT);
		when(association.getActors()).thenReturn(actors);
		
		assertTrue(new OccupiesValidator().isValid(actor, association));
	}
	
	@Test public void isNotValidTest() {
		List<Actor> actors = Arrays.asList(new Actor("ActorOne", "position"), new Actor("ActorTwo", "role"));
		
		when(actor.getType()).thenReturn(Actor.ActorType.AGENT);
		when(association.getActors()).thenReturn(actors);
		
		assertFalse(new OccupiesValidator().isValid(actor, association));
	}
}