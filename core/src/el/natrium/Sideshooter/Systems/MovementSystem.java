package el.natrium.Sideshooter.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import el.natrium.Sideshooter.Components.BoundsComponent;
import el.natrium.Sideshooter.Components.GravityComponent;
import el.natrium.Sideshooter.Components.MovementComponent;
import el.natrium.Sideshooter.Components.TransformComponent;

public class MovementSystem extends IteratingSystem {
	
	private ComponentMapper<TransformComponent> tm;
	private ComponentMapper<MovementComponent> mm;
	private ComponentMapper<BoundsComponent> bm;
	private ComponentMapper<GravityComponent> gm;
	private Vector2 tmp;
	
	
	public MovementSystem() {
		super(Family.getFor(TransformComponent.class, MovementComponent.class));
		
		tm = ComponentMapper.getFor(TransformComponent.class);
		mm = ComponentMapper.getFor(MovementComponent.class);
		bm = ComponentMapper.getFor(BoundsComponent.class);
		gm = ComponentMapper.getFor(GravityComponent.class);
		
		tmp = new Vector2();
		
	}
	

	protected void processEntity(Entity entity, float deltaTime) {
		MovementComponent move = mm.get(entity);
		TransformComponent position = tm.get(entity);
		BoundsComponent bounds = bm.get(entity);
		GravityComponent grav = gm.get(entity);
			
		tmp.set(move.accel).scl(deltaTime);
		
		if (grav != null)
			tmp.add(grav.gravity);
		
		move.velocity.add(tmp);
		
		tmp.set(move.velocity).scl(deltaTime); 
		position.pos.add(tmp.x, tmp.y);
		
		if (bounds != null) {
			bounds.bounds.setX(position.pos.x);
			bounds.bounds.setY(position.pos.y);
		}
		
		
			
//		System.out.println(move.velocity.x);
	}
}
