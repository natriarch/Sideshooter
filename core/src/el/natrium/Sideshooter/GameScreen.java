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

public class GameScreen extends ScreenAdapter{
	
	Sideshooter game;
	
	OrthographicCamera guiCam;
	
	Engine engine;
	
	public GameScreen(Sideshooter game) {
		this.game = game;
		guiCam = new OrthographicCamera(800, 600);
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
		movement1.accel.x = 0f; 
		movement1.maxVelocity.x = 1f;
		movement1.maxVelocity.y = 0f;
		
		
		Entity entity1 = new Entity();
		entity1.add(sprite1);
		entity1.add(position1);
		entity1.add(movement1);
		entity1.add(input1);

		engine.addEntity(entity1);
	}
	
	public void update(float deltaTime) {
		if (deltaTime > 0.1f) deltaTime = 0.1f;
		engine.update(deltaTime);
	}
	
	@Override
	public void render(float delta) {
		update(delta);
	}
	

}
