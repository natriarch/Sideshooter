package el.natrium.Sideshooter;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class MovementSystem extends IteratingSystem {
	
	private ComponentMapper<TransformComponent> tm;
	private ComponentMapper<MovementComponent> mm;
	private Array<Entity> movementQueue;
	
	public MovementSystem() {
		super(Family.getFor(TransformComponent.class, MovementComponent.class));
		
		tm = ComponentMapper.getFor(TransformComponent.class);
		mm = ComponentMapper.getFor(MovementComponent.class);
		
		movementQueue = new Array<Entity>(); 
	}
	
	public void update(float deltaTime) {
		super.update(deltaTime);

		for (Entity entity : movementQueue) {
			MovementComponent move = mm.get(entity);
			TransformComponent position = tm.get(entity);
			
			
			position.pos.x += move.velocity.x * deltaTime;
			
			Vector2 vFinal;
			vFinal = move.accel.scl(deltaTime);
			
			move.velocity.set(move.velocity.add(vFinal));
			
			System.out.println(move.velocity.x);
		}
	}

	protected void processEntity(Entity entity, float deltaTime) {
		movementQueue.add(entity);
	}
}
