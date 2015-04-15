package el.natrium.Sideshooter.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import el.natrium.Sideshooter.Components.SpriteComponent;
import el.natrium.Sideshooter.Components.TransformComponent;

public class RenderSystem extends IteratingSystem{
	
	private SpriteBatch batch;
	private OrthographicCamera cam; 
	private ComponentMapper<SpriteComponent> sm;
	private ComponentMapper<TransformComponent> tm; 
	private Array<Entity> renderQueue;
	
	public RenderSystem(SpriteBatch batch) {
		super(Family.getFor(SpriteComponent.class, TransformComponent.class));
		
		sm = ComponentMapper.getFor(SpriteComponent.class);
		tm = ComponentMapper.getFor(TransformComponent.class);
		
		renderQueue = new Array<Entity>();
		
		this.batch = batch;
		cam = new OrthographicCamera(800,600);
		cam.position.set(400,300,0);
	}
	
	@Override
	public void update(float deltaTime){
		super.update(deltaTime);
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		
		for(Entity entity : renderQueue){
			SpriteComponent sprite = sm.get(entity);
			TransformComponent position = tm.get(entity);
			
			batch.draw(sprite.sprite, position.pos.x, position.pos.y);
		}
		
		batch.end();
		renderQueue.clear();
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		renderQueue.add(entity);
	}
}
