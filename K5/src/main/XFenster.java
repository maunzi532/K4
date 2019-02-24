package main;

import sprites.*;

public interface XFenster
{
	void handleInput(int input);

	SpriteList getSpriteList();

	void update();
}