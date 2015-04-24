package el.natrium.Sideshooter.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

import el.natrium.Sideshooter.Components.BoundsComponent;
import el.natrium.Sideshooter.Components.InputComponent;
import el.natrium.Sideshooter.Components.MovementComponent;
import el.natrium.Sideshooter.Components.SpriteComponent;
import el.natrium.Sideshooter.Components.TransformComponent;

public class InputSystem extends IteratingSystem{
	
	private ComponentMapper<MovementComponent> mm;
	private ComponentMapper<InputComponent> im;
	private ComponentMapper<TransformComponent> tm;
	private Array<Entity> inputQueue;
	private Engine engine;
	
	
	
	public InputSystem(Engine e) {
		super(Family.getFor(MovementComponent.class, InputComponent.class));
		
		mm = ComponentMapper.getFor(MovementComponent.class);
		im = ComponentMapper.getFor(InputComponent.class);
		tm = ComponentMapper.getFor(TransformComponent.class);

		engine = e; 
		
		inputQueue = new Array<Entity>();
	}

	
	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		MovementComponent move = mm.get(entity);
		TransformComponent pos = tm.get(entity);
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
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			Entity bullet = new Entity();
			SpriteComponent bulletSprite = new SpriteComponent();
			TransformComponent bulletPosition = new TransformComponent();
			MovementComponent bulletMovement = new MovementComponent();
			BoundsComponent bulletBounds = new BoundsComponent();
			
			Texture bulletTex = new Texture("bullet.png");
			Sprite bSprite = new Sprite(bulletTex); 
			bulletSprite.sprite.set(bSprite);
			
			bulletPosition.pos.set(pos.pos); 
			
			bulletMovement.accel.set(0,0);
			bulletMovement.velocity.set(200,0);
			
			bulletBounds.bounds.set(0,0, bulletSprite.sprite.getWidth(), bulletSprite.sprite.getHeight());
			
			bullet.add(bulletSprite);
			bullet.add(bulletPosition);
			bullet.add(bulletMovement);
			bullet.add(bulletBounds);
			
			engine.addEntity(bullet);
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}
}	
