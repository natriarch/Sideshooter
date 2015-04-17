package el.natrium.Sideshooter.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Array;

import el.natrium.Sideshooter.Components.BoundsComponent;
import el.natrium.Sideshooter.Components.MovementComponent;
import el.natrium.Sideshooter.Components.TransformComponent;

public class CollisionSystem extends EntitySystem{
	
	private ComponentMapper<BoundsComponent> bm;
	private ComponentMapper<TransformComponent> tm;
	private ComponentMapper<MovementComponent> mm; 
	
	private Array<Entity> collisionQueue;
	
	public CollisionSystem(){
		
		collisionQueue = new Array<Entity>();
		
		bm = ComponentMapper.getFor(BoundsComponent.class);
		tm = ComponentMapper.getFor(TransformComponent.class);
	}
	
	public void update(float deltaTime) {
		if (collisionQueue.size > 1) {
			for (Entity a : collisionQueue) {
				BoundsComponent boundsA = bm.get(a);
				for (Entity b : collisionQueue) {
					if (a.getId() != b.getId()) {
						BoundsComponent boundsB = bm.get(b);
						if (boundsA.bounds.overlaps(boundsB.bounds)) {
							System.out.println("~Collision detected~");
						}
					}
					
				}
			}
		}
	}

	protected void processEntity(Entity entity, float deltaTime) {
		collisionQueue.add(entity);
	}
}
