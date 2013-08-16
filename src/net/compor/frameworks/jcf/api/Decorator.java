package net.compor.frameworks.jcf.api;


public class Decorator<T extends Component> extends Component {
	
	private T innerComponent;
	
	public Decorator(T innerComponent) {
		super(innerComponent.getName());
		this.innerComponent = innerComponent;
	}

	public T getInnerComponent() {
		return innerComponent;
	}
}
