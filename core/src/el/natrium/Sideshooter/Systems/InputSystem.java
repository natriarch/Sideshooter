package el.natrium.Sideshooter.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Array;

import el.natrium.Sideshooter.Components.InputComponent;
import el.natrium.Sideshooter.Components.MovementComponent;

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

	
	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		MovementComponent move = mm.get(entity);
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			move.accel.x = -2000.0f;
			if (move.velocity.x < 0 && Math.abs(move.velocity.x) > move.maxVelocity.x) {
				move.accel.x = 0f;
			}
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			move.accel.x = 2000.0f;
			if (move.velocity.x > move.maxVelocity.x) {
				move.accel.x = 0f;
			}
		}
		else {
			move.velocity.x = 0;
		}
	}
}	
