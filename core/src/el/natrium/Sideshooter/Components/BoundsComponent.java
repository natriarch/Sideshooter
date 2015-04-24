package el.natrium.Sideshooter.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;

public class BoundsComponent extends Component{
	public final Rectangle bounds = new Rectangle();
	public final boolean injuring = false;
	public final boolean injurable = false;
	public final boolean clipping = false;
}
