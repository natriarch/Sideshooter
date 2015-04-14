package el.natrium.Sideshooter;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Guy {
	
	private boolean moveLeft;
	private boolean moveRight;
	private Vector2 position;
	private Sprite sprite; 
	
	public Guy(Texture t) {
		sprite = new Sprite(t);
		moveLeft = false;
		moveRight = false;
		position = new Vector2(Gdx.graphics.getWidth()/2, 0);
		sprite.setPosition(position.x, position.y);
	}
	
	public void updateMovement() {
		if (moveLeft) {
			position.x -= 120 * Gdx.graphics.getDeltaTime();
		}
		
		if (moveRight) {
			position.x += 120 * Gdx.graphics.getDeltaTime();
		}
		
		sprite.setX(position.x);
	}
	
	public void setLeftMove(boolean b) {
		if (moveRight && b) moveRight = false;
		moveLeft = b; 
		
	}
	
	public void setRightMove(boolean b) {
		if (moveLeft && b) moveLeft = false;
		moveRight = b;
		
	}
	
	public void drawSprite(SpriteBatch b) {
		sprite.draw(b);
	}
	
	
}
