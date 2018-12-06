package gameK4;

import plane.*;
import sprites.*;

public class XChar
{
	public static final int LAG = 8;
	public static final int MAX = 3;

	private XInputBuffer inputBuffer;
	public XSprite sprite;
	public int direction;
	public int walkingSpeed;
	public int ySpeed;

	public XChar()
	{
		inputBuffer = new XInputBuffer(MAX);
		SubPixelPlane cPlane = new SubPixelPlane().init("Char6_N", "Char6_AC");
		sprite = new XSprite(100, 100, cPlane.getSubYSize(), cPlane.getSubXSize() / 2, 1, cPlane);
		direction = 1;
	}

	public void update(int input)
	{
		inputBuffer.addInput(input);
		do
		{
			int inputC = inputBuffer.getInput();
			if(inputC == -2)
				break;
			inputBuffer.newLag(handleInput(inputC));
		}while(inputBuffer.zeroLag());
		sprite.x += direction * walkingSpeed * 4;
		sprite.y -= ySpeed;
		if(ySpeed > -1)
			ySpeed--;
	}

	public int handleInput(int input)
	{
		switch(input)
		{
			case 'w':
				ySpeed = 6;
				return LAG;
			case 's':
				walkingSpeed = 0;
				return LAG;
			case 'a':
				if(direction < 0 && walkingSpeed > 0)
				{
					if(walkingSpeed >= 2)
						return 0;
					walkingSpeed = 2;
				}
				else
				{
					direction = -1;
					walkingSpeed = 1;
				}
				sprite.plane.setFlippedX(true);
				return LAG;
			case 'd':
				if(direction > 0 && walkingSpeed > 0)
				{
					if(walkingSpeed >= 2)
						return 0;
					walkingSpeed = 2;
				}
				else
				{
					direction = 1;
					walkingSpeed = 1;
				}
				sprite.plane.setFlippedX(false);
				return LAG;
			default:
				return 0;
		}
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