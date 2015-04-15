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
	private Vector2 tmp;
	
	
	public MovementSystem() {
		super(Family.getFor(TransformComponent.class, MovementComponent.class));
		
		tm = ComponentMapper.getFor(TransformComponent.class);
		mm = ComponentMapper.getFor(MovementComponent.class);
		
		tmp = new Vector2();
		
	}
	

	protected void processEntity(Entity entity, float deltaTime) {
		MovementComponent move = mm.get(entity);
		TransformComponent position = tm.get(entity);
			
		tmp.set(move.accel).scl(deltaTime);
		move.velocity.add(tmp);
		
		tmp.set(move.velocity).scl(deltaTime); 
		position.pos.add(tmp.x, tmp.y); 	
		
			
		System.out.println(move.velocity.x);
	}
}
