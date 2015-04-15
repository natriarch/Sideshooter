package el.natrium.Sideshooter;


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

import el.natrium.Sideshooter.Components.InputComponent;
import el.natrium.Sideshooter.Components.MovementComponent;
import el.natrium.Sideshooter.Components.SpriteComponent;
import el.natrium.Sideshooter.Components.TransformComponent;
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
		
		SpriteComponent sprite1 = new SpriteComponent();
		MovementComponent movement1 = new MovementComponent();
		TransformComponent position1 = new TransformComponent();	
		InputComponent input1 = new InputComponent();
		
		Texture t = new Texture("shooter.png");
		sprite1.sprite.set(new Sprite(t));
		
		position1.pos.set(400.0f, 300.0f);
		movement1.maxVelocity.set(300,0);
		movement1.accel.set(0,0);
		
		
		Entity entity1 = new Entity();
		entity1.add(sprite1);
		entity1.add(position1);
		entity1.add(movement1);
		entity1.add(input1);

		engine.addEntity(entity1);
	}
	
	public void update(float deltaTime) {
		engine.update(deltaTime);
		engine.getSystem(MovementSystem.class).setProcessing(true);
		engine.getSystem(InputSystem.class).setProcessing(true);
	}
	
	@Override
	public void render(float delta) {
		update(delta);
	}
	

}
