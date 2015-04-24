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

public class CollisionSystem extends IteratingSystem{
	
	private ComponentMapper<BoundsComponent> bm;
	private ComponentMapper<TransformComponent> tm;
	private ComponentMapper<MovementComponent> mm;
	
	private Array<Entity> collisionQueue;
	
	public CollisionSystem() {
		
		//if it has a bounds component, it is collidable!
		super(Family.getFor(BoundsComponent.class));
		
		collisionQueue = new Array<Entity>();
		
		bm = ComponentMapper.getFor(BoundsComponent.class);
		tm = ComponentMapper.getFor(TransformComponent.class);
	}

	protected void processEntity(Entity entity, float deltaTime) {
		BoundsComponent bounds = bm.get(entity);
		collisionQueue.add(entity);
		
		for (int i = 0; i < collisionQueue.size; i ++) {
			if (entity.getId() != collisionQueue.get(i).getId()) {
				BoundsComponent boundsToCompare = bm.get(collisionQueue.get(i));
				if (bounds.bounds.overlaps(boundsToCompare.bounds)) {
					System.out.println("~collision detected~");
				}
			}
		}
	}
}
