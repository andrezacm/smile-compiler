package br.ufrn.smile.domain;

import br.ufrn.smile.domain.Dependency.DependencyPerspective;
import br.ufrn.smile.domain.Dependency.DependencyType;

public class DependencyFactory {
    public static DependencyPerspective getDependencyPerspective(DependencyType associationType) {
    	DependencyPerspective perspective = DependencyPerspective.REACHES;
    	
    	if(associationType == DependencyType.RESOURCE) {
    		perspective = DependencyPerspective.PROVIDES;
    	} 
    	else if (associationType == DependencyType.TASK) {
			perspective = DependencyPerspective.CARRIESOUT;
		}
    	
    	return perspective;
    }
}
