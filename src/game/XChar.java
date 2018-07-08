package game;

import plane.*;
import sprites.*;

public class XChar
{
	public XSprite sprite;
	public int direction;
	public int walkingSpeed;
	public int ySpeed;

	public XChar()
	{
		SubPixelPlane cPlane = new SubPixelPlane().init("Char6_N", "Char6_AC");
		sprite = new XSprite(100, 100, cPlane.getSubYSize(), cPlane.getSubXSize() / 2, 1, cPlane);
		direction = 1;
	}

	public void handleInput(int input)
	{
		switch(input)
		{
			case 'w':
				//ys--;
				ySpeed = 6;
				break;
			case 's':
				//ys++;
				walkingSpeed = 0;
				break;
			case 'a':
				//xs--;
				if(direction < 0 && walkingSpeed > 0)
				{
					walkingSpeed = 2;
				}
				else
				{
					direction = -1;
					walkingSpeed = 1;
				}
				sprite.plane.setFlippedX(true);
				break;
			case 'd':
				//xs++;
				if(direction > 0 && walkingSpeed > 0)
				{
					walkingSpeed = 2;
				}
				else
				{
					direction = 1;
					walkingSpeed = 1;
				}
				sprite.plane.setFlippedX(false);
				break;
		}
		sprite.x += direction * walkingSpeed * 4;
		sprite.y -= ySpeed;
		if(ySpeed > -1)
			ySpeed--;
	}

	public int getY()
	{
		return sprite.y;
	}

	public int getX()
	{
		return sprite.x;
	}
}