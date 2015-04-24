package el.natrium.Sideshooter;


import java.awt.Rectangle;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

import el.natrium.Sideshooter.Components.BoundsComponent;
import el.natrium.Sideshooter.Components.GravityComponent;
import el.natrium.Sideshooter.Components.InputComponent;
import el.natrium.Sideshooter.Components.MovementComponent;
import el.natrium.Sideshooter.Components.SpriteComponent;
import el.natrium.Sideshooter.Components.TransformComponent;
import el.natrium.Sideshooter.Systems.CollisionSystem;
import el.natrium.Sideshooter.Systems.InputSystem;
import el.natrium.Sideshooter.Systems.MovementSystem;
import el.natrium.Sideshooter.Systems.RenderSystem;

public class GameScreen extends ScreenAdapter{
	
	Sideshooter game;
	
	OrthographicCamera guiCam;
	
	Engine engine;
	
	public GameScreen(Sideshooter game) {
		this.game = game;
		guiCam = new OrthographicCamera(1024, 768);
		guiCam.position.set(400, 300, 0);
		
		engine = new Engine();
		engine.addSystem(new RenderSystem(game.batch));
		engine.addSystem(new MovementSystem());
		engine.addSystem(new InputSystem());
		engine.addSystem(new CollisionSystem());
		
		BoundsComponent windowBounds = new BoundsComponent();
		windowBounds.bounds.set(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Entity window = new Entity();
		window.add(windowBounds); 
		
		SpriteComponent sprite1 = new SpriteComponent();
		MovementComponent movement1 = new MovementComponent();
		TransformComponent position1 = new TransformComponent();	
		InputComponent input1 = new InputComponent();
		BoundsComponent bounds1 = new BoundsComponent();
		GravityComponent grav1 = new GravityComponent(); 
		
		SpriteComponent sprite2 = new SpriteComponent();
		TransformComponent position2 = new TransformComponent();	
		BoundsComponent bounds2 = new BoundsComponent();
	
		Texture t1 = new Texture("shooter.png");
		sprite1.sprite.set(new Sprite(t1));
		
		Texture t2 = new Texture("shooter2.png");
		sprite2.sprite.set(new Sprite(t2));
		
		position1.pos.set(400.0f, 300.0f);
		movement1.maxVelocity.set(300.0f, 0.0f);
		movement1.accel.set(0.0f, 0.0f);
		bounds1.bounds.set(position1.pos.x, position1.pos.y, sprite1.sprite.getWidth(), sprite1.sprite.getHeight());
		grav1.gravity.set(0.0f, -9.8f); 
		
		position2.pos.set(600.0f, 300.0f);
		bounds2.bounds.set(position2.pos.x, position2.pos.y, sprite2.sprite.getWidth(), sprite2.sprite.getHeight());
		
		Entity entity1 = new Entity();
		entity1.add(sprite1);
		entity1.add(position1);
		entity1.add(movement1);
		entity1.add(input1);
		entity1.add(bounds1);
		entity1.add(grav1);
		
		Entity entity2 = new Entity();
		entity2.add(sprite2);
		entity2.add(position2);
		entity2.add(bounds2);

		engine.addEntity(entity1);
		engine.addEntity(entity2);
		engine.addEntity(window);
	}
	
	public void update(float deltaTime) {
		engine.update(deltaTime);
		engine.getSystem(MovementSystem.class).setProcessing(true);
		engine.getSystem(InputSystem.class).setProcessing(true);
		engine.getSystem(CollisionSystem.class).setProcessing(true);
	}
	
	@Override
	public void render(float delta) {
		update(delta);
	}
	

}
