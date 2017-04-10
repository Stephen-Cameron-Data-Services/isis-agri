package au.com.scds.agric.dom.demo;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import au.com.scds.agric.dom.demo.data.Result;
import au.com.scds.agric.dom.demo.data.Sample;
import au.com.scds.agric.dom.demo.data.TestSingle;

@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = Result.class)
public class ResultRepository {

	public Result createResult(Sample sample, TestSingle test, String testResult) {
		if (sample == null || test == null || testResult == null)
			return null;
		final Result result = new Result();
		result.setSample(sample);
		result.setTest(test);
		result.setTestResult(testResult);
		serviceRegistry.injectServicesInto(result);
		repositoryService.persistAndFlush(result);
		return result;
	}
	
	public List<Result> listAll() {
		return repositoryService.allInstances(Result.class);
	}
	
	public Result findById(final String id) {
		return repositoryService.firstMatch(new QueryDefault<>(Result.class, "findById", "id", id));
	}

	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;
}