package net.compor.frameworks.jcf.api;

import net.compor.frameworks.jcf.FunctionalComponent;
import net.compor.frameworks.jcf.ProvidedService;
import net.compor.frameworks.jcf.RequiredService;
import net.compor.frameworks.jcf.ServiceRequest;
import net.compor.frameworks.jcf.ServiceResponse;

public class Component extends FunctionalComponent {
	
	public static final String USE_METHOD_NAME = "USE_METHOD_NAME";
	public static final String DO_NOT_REQUIRE_SERVICES = "DO_NOT_REQUIRE_SERVICES";

	public Component(String name) {
		super(name);
	}

	public void publishService(ProvidedService providedService) {
		super.addProvidedService(providedService);
	}
	
	protected Object requestService(String service, Object... arguments) {
		try {
			ServiceRequest request = new ServiceRequest(service, arguments);
			ServiceResponse response = super.doIt(request);
	
			if (response.hasException()) {
				throw new RuntimeException(response.getException());
			} else {
				return response.getData();
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void requireService(RequiredService requiredService) {
		super.addRequiredService(requiredService);
	}

}
