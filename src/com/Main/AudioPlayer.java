package com.Main;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.*;
public class AudioPlayer {
	
	public static Map<String,Sound> soundMap=new HashMap<String,Sound>();
	public static Map<String,Music> musicMap=new HashMap<String,Music>();
	
	public static void load()
	{
		try {
			soundMap.put("click", new Sound("res/Click_update.ogg"));
			musicMap.put("music", new Music("res/Game.wav"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public static Music getmusic(String key) {
		return musicMap.get(key);
	}
	public static Sound getsound(String key) {
		return soundMap.get(key);
	}
}
