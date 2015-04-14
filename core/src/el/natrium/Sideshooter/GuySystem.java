package el.natrium.Sideshooter;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;


public class GuySystem extends IteratingSystem {
	
	private static final Family family = Family.getFor(GuyComponent.class, TransformComponent.class, MovementComponent.class);

	private ComponentMapper<GuyComponent> gm;
	private ComponentMapper<TransformComponent> tm;
	private ComponentMapper<MovementComponent> mm;
	
	private float accelX = 0.0f; 
	
	public GuySystem() {
		super(family);
		gm = ComponentMapper.getFor(GuyComponent.class);
		tm = ComponentMapper.getFor(TransformComponent.class);
		mm = ComponentMapper.getFor(MovementComponent.class);
	}
	
	public void setAccelX(float accelX) {
		this.accelX = accelX; 
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		accelX = 0.0f; 
	}
	
	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		TransformComponent t = tm.get(entity);
		MovementComponent mov = mm.get(entity);
		GuyComponent guy = gm.get(entity);
		
		if (t.pos.x < 0) {
			t.pos.x = Gdx.graphics.getWidth(); 
		}
		
		if (t.pos.x > Gdx.graphics.getWidth()) {
			t.pos.x = 0;
		}
		
		mov.velocity.x = -accelX / 10.0f * GuyComponent.MOVE_VELOCITY;
		
	}
	
	
		
}


