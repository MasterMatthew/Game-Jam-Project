package game.interfaces;

import com.base.engine.core.Input;
import com.base.engine.core.Vector3f;
import com.base.engine.physics.AABB;
import com.base.engine.physics.Collider;

import static org.lwjgl.glfw.GLFW.*;

import game.Collides;
import game.GObject;
import game.mob.Worker;
import game.objects.Backpack;
import game.objects.ServingTable;
import game.objects.SpecialChair;
import game.objects.SpecialTable;
import game.objects.food.PizzaSlice;
import game.world.MainGame;

public class InterfaceTrigger extends GObject implements Collides
{
	public Collider collider1;
	public Collider collider2;
	public boolean density;
	public E e;
	public Vector3f base;
	private GObject parent;
	float width = 1f;
	float height = 1f;
	
	
	public InterfaceTrigger(float x, float y, float z, E e, GObject object, Collider c1, Collider c2)
	{	
		this.e = e;
		base = e.getPos();
		parent = object;
		
		collider1 = c1;
		collider2 = c2;
		density = false;
		
		getChildren().add(new PizzaSlice(0,0,0));
	}
	
	public InterfaceTrigger(float x, float y, float z, E e, GObject object, float w, float h)
	{	
		this.e = e;
		base = e.getPos();
		parent = object;
		
		width = w;
		height = h;
		
		collider1 = new AABB(new Vector3f(x - width/2, y - height/2, z - width/2), new Vector3f(x + width/2, y + height/2, z + width/2));
		collider2 = new AABB(new Vector3f(x - width/2, y - height/2, z - width/2), new Vector3f(x + width/2, y + height/2, z + width/2));
		density = false;
		
		getChildren().add(new PizzaSlice(0,0,0));
	}
	
	public InterfaceTrigger(float x, float y, float z, E e, GObject object)
	{	
		this.e = e;
		base = e.getPos();
		parent = object;
		
		width = 1f;
		height = 1f;
		
		collider1 = new AABB(new Vector3f(x - width/2, y - height/2, z - width/2), new Vector3f(x + width/2, y + height/2, z + width/2));
		collider2 = new AABB(new Vector3f(x - width/2, y - height/2, z - width/2), new Vector3f(x + width/2, y + height/2, z + width/2));
		density = false;
		
		getChildren().add(new PizzaSlice(0,0,0));
	}
	
	public float tr = 0;
	public void trigger(float delta)
	{
		e.o.getTransform().setPos(base);
		tr-=delta;
		if(tr < 0 && Input.getKey(GLFW_KEY_E))
		{
			tr+=.3f;
			//System.out.println("We did it reddit!");
			if(parent instanceof ServingTable)
			{
				MainGame.mainPlayer.get(0);
			}
			if(parent instanceof SpecialTable)
			{
				MainGame.mainPlayer.place(0);
			}
			if(parent instanceof SpecialChair)
			{
				MainGame.mainPlayer.eat(0);
			}
			if(parent instanceof Backpack)
			{
				MainGame.mainPlayer.bag(0);
			}
		}
		if(parent instanceof Worker)
		{
			System.out.println("works");
			MainGame.mainPlayer.gameOver();
		}
	}
	
	public void detrigger()
	{
		e.o.getTransform().setPos(new Vector3f(0, 3, 0));
	}
	
	
	@Override
	public Collider getCollider1() {
		// TODO Auto-generated method stub
		return collider1;
	}
	@Override
	public Collider getCollider2() {
		// TODO Auto-generated method stub
		return collider2;
	}

	@Override
	public boolean getDensity() {
		// TODO Auto-generated method stub
		return density;
	}

	@Override
	public void setCollider(Collider collider) {
		this.collider1 = collider;
		this.collider2 = new Collider(collider);
	}
	

	@Override
	public void setDensity(boolean density) {
		this.density = density;
		
	}

}
