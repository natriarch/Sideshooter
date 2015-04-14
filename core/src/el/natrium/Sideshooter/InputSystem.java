package el.natrium.Sideshooter;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class InputSystem extends IteratingSystem{
	
	private ComponentMapper<MovementComponent> mm;
	private ComponentMapper<InputComponent> im;
	private Array<Entity> inputQueue;
	
	public InputSystem() {
		super(Family.getFor(MovementComponent.class, InputComponent.class));
		
		mm = ComponentMapper.getFor(MovementComponent.class);
		im = ComponentMapper.getFor(InputComponent.class);

		inputQueue = new Array<Entity>();
	}
	
	public void update(float deltaTime){
		super.update(deltaTime);
		
		for (Entity entity : inputQueue) {
			
			MovementComponent move = mm.get(entity);
			if (Gdx.input.isKeyPressed(Input.Keys.A)) {
				move.accel.x = -0.75f;
			}
			
			if (Gdx.input.isKeyPressed(Input.Keys.D)) {
				move.accel.x = 0.75f;
			}
			
		}
	}

	
	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		inputQueue.add(entity);
	}
}	
