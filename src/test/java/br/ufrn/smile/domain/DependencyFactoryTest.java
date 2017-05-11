package br.ufrn.smile.domain;

import org.junit.Test;
import static org.junit.Assert.*;

import br.ufrn.smile.domain.Dependency.DependencyPerspective;
import br.ufrn.smile.domain.Dependency.DependencyType;

public class DependencyFactoryTest {
	@Test public void getProvidesDependencyPerspective() {
		DependencyPerspective expected = DependencyPerspective.PROVIDES;
		DependencyPerspective actual = DependencyFactory.getDependencyPerspective(DependencyType.RESOURCE);
		
		assertEquals(expected, actual);		
	}
	
	@Test public void getCarriesOutDependencyPerspective() {
		DependencyPerspective expected = DependencyPerspective.CARRIESOUT;
		DependencyPerspective actual = DependencyFactory.getDependencyPerspective(DependencyType.TASK);
		
		assertEquals(expected, actual);		
	}
	
	@Test public void getReachesDependencyPerspectiveFromGoal() {
		DependencyPerspective expected = DependencyPerspective.REACHES;
		DependencyPerspective actual = DependencyFactory.getDependencyPerspective(DependencyType.GOAL);
		
		assertEquals(expected, actual);		
	}
	
	@Test public void getReachesDependencyPerspectiveFromSoftgoal() {
		DependencyPerspective expected = DependencyPerspective.REACHES;
		DependencyPerspective actual = DependencyFactory.getDependencyPerspective(DependencyType.SOFTGOAL);
		
		assertEquals(expected, actual);		
	}
}
