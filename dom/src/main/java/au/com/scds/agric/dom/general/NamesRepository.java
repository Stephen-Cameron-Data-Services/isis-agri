package au.com.scds.agric.dom.general;

import java.util.List;

import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.repository.RepositoryService;

public class NamesRepository {

	public Colour createColour(String name) {
		Colour colour = findColourByName(name);
		if (colour == null) {
			colour = new Colour(name);
			repositoryService.persistAndFlush(colour);
		}
		return colour;
	}

	public List<Colour> listAllColours() {
		return repositoryService.allInstances(Colour.class);
	}

	public Colour findColourByName(String name) {
		return repositoryService.firstMatch(new QueryDefault<>(Colour.class, "findByName", "name", name));
	}

	@javax.inject.Inject
	RepositoryService repositoryService;
}
