package el.natrium.Sideshooter;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
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
		
		float vFinalX;
		float vFinalY;

		for (Entity entity : movementQueue) {
			MovementComponent move = mm.get(entity);
			TransformComponent position = tm.get(entity);
			
			if (move.velocity.x < 0 && Math.abs(move.velocity.x) >= move.maxVelocity.x) {
				move.accel.x = 0;
			}
			
			if (move.velocity.x > 0 && move.velocity.x >= move.maxVelocity.x) {
				move.accel.x = 0; 
			}
			
			vFinalX = move.velocity.x + move.accel.x * deltaTime;
			vFinalY = move.velocity.y + move.accel.y * deltaTime;
			
			move.velocity.x = vFinalX;
			
			
			position.pos.x += move.velocity.x * deltaTime;
			position.pos.y += vFinalY * deltaTime;
			
			System.out.println(move.velocity.x);
		}
	}

	protected void processEntity(Entity entity, float deltaTime) {
		movementQueue.add(entity);
	}
}
