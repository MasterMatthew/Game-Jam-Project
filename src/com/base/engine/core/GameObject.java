package com.base.engine.core;

import com.base.engine.components.GameComponent;
import com.base.engine.physics.PhysicsEngine;
import com.base.engine.rendering.RenderingEngine;
import com.base.engine.rendering.Shader;

import java.util.ArrayList;

public class GameObject
{
	private ArrayList<GameObject> children;
	private ArrayList<GameComponent> components;
	private Transform transform;
	private CoreEngine engine;

	
	public GameObject()
	{
		children = new ArrayList<GameObject>();
		components = new ArrayList<GameComponent>();
		transform = new Transform();
		engine = null;
	}

	public void addChild(GameObject child)
	{
		children.add(child);
		child.setEngine(engine);
		child.getTransform().setParent(transform);
	}

	public GameObject addComponent(GameComponent component)
	{
		components.add(component);
		component.setParent(this);

		return this;
	}

	public void inputAll(float delta)
	{
		input(delta);

		for(GameObject child : children)
			child.inputAll(delta);
	}

	public void updateAll(float delta, PhysicsEngine physicsEngine)
	{
		update(delta, physicsEngine);

		for(GameObject child : children)
			child.updateAll(delta, physicsEngine);
	}

	public void renderAll(Shader shader, RenderingEngine renderingEngine)
	{
		render(shader, renderingEngine);

		for(GameObject child : children)
			child.renderAll(shader, renderingEngine);
	}
	
	public void input(float delta)
	{
		transform.update();

		for(GameComponent component : components)
			component.input(delta);
	}

	public void update(float delta, PhysicsEngine physicsEngine)
	{
		for(GameComponent component : components)
			component.update(delta, physicsEngine);
	}

	public void render(Shader shader, RenderingEngine renderingEngine)
	{
		for(GameComponent component : components)
			component.render(shader, renderingEngine);
	}
	
	
	public ArrayList<GameObject> getAllAttached()
	{
		ArrayList<GameObject> result = new ArrayList<GameObject>();
		
		for(GameObject child : children)
		{
			result.addAll(child.getAllAttached());
		}
		result.add(this);
		return result;
	}

	public Transform getTransform()
	{
		return transform;
	}
	
	public Vector3f getPosition()
	{
		return transform.getPos();
	}
	
	public void setEngine(CoreEngine engine)
	{
		if(this.engine != engine)
		{
			this.engine = engine;
			
			for(GameComponent component : components)
				component.addToEngine(engine);

			for(GameObject child : children)
				child.setEngine(engine);
		}
	}
}
