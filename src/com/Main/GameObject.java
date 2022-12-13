package com.Main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	protected float x,y;
	protected ID id;
	protected float velx,vely;
	protected float health;
	public GameObject(float x,float y ,ID id)
	{
		this.id=id;
		this.x=x;
		this.y=y;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getbounds();
	
	public void setX(float x) {
		this.x=x;
	}
	public void setY(float y) {
		this.y=y;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public void setID(ID id) {
		this.id=id;
	}
	public ID getID() {
		return id;
	}
	public void setvelx(float velx) {
		this.velx=velx;
	}
	public float getvelx() {
		return velx;
	}
	public void setvely(float vely) {
		this.vely=vely;
	}
	public float getvely() {
		return vely;
	}
	public void sethealth(float health) {
		this.health=health;
	}
	public float gethealth() {
		return health;
	}
}
