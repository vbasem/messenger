package extra;

import javax.inject.Inject;

public class SpaceShip {

	
	@Inject
	Engine engine;
	
	public void start() {
		engine.ignite();
		System.out.println("ok");
	}
}
