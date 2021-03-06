package game.objects;

import com.base.engine.core.Vector3f;
import com.base.engine.physics.AABB;
import com.base.engine.physics.Collider;

import game.Collides;
import game.GObject;

public class Wall extends Furnishing
{
	public Wall(float x, float y, float z) {
		super(x, y, z);
		
		setScale(0.004f);
		setTexture("bricks2.jpg");
		setMesh("wall100_100.obj");
		
		//collider = new AABB(new Vector3f(x - 2.0f, y - 2.0f, z - .4f), new Vector3f(x + 2.0f, y + 2.0f, z + .3f));
		
		setCollider(new AABB(new Vector3f(x - 0.5f, y - 2.0f, z - 0.1f), new Vector3f(x + 0.5f, y + 2.0f, z + 0.1f)));
		
		//
		setDensity(true);
	}
}
