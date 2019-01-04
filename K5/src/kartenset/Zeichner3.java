package kartenset;

public interface Zeichner3
{
	int yw();
	int xw();

	default char[][] erstellen()
	{
		return new char[yw()][xw()];
	}

	default void rahmen(char[][] bild, Rahmen3 rahmen, int... zeilentrenner)
	{
		for(int iy = 0; iy < yw(); iy++)
		{
			int iyc;
			if(iy == 0)
				iyc = 0;
			else if(iy == yw() - 1)
				iyc = 2;
			else
				iyc = 1;
			for(int i = 0; i < zeilentrenner.length; i++)
			{
				if(zeilentrenner[i] == iy)
					iyc = 3;
			}
			for(int ix = 0; ix < xw(); ix++)
			{
				int ixc;
				if(ix == 0)
					ixc = 0;
				else if(ix == xw() - 1)
					ixc = 2;
				else
					ixc = 1;
				bild[iy][ix] = rahmen.c[iyc][ixc];
			}
		}
	}

	default void linieInnen(char[][] bild, Rahmen3 rahmen, int y0, int y1, int x)
	{
		bild[y0][x] = rahmen.c[0][3];
		for(int iy = y0 + 1; iy < y1; iy++)
		{
			bild[iy][x] = rahmen.c[1][3];
		}
		bild[y1][x] = rahmen.c[2][3];
	}

	default void rahmenInnen(char[][] bild, Rahmen3 rahmen, int y0, int y1, int x0, int x1)
	{
		for(int iy = y0; iy < y1; iy++)
		{
			int iyc;
			if(iy == y0)
				iyc = 0;
			else if(iy == y1 - 1)
				iyc = 2;
			else
				iyc = 1;
			for(int ix = x0; ix < x1; ix++)
			{
				int ixc;
				if(ix == x0)
					ixc = 0;
				else if(ix == x1 - 1)
					ixc = 2;
				else
					ixc = 1;
				bild[iy][ix] = rahmen.c[iyc][ixc];
			}
		}
	}

	default void textEinpassen(char[][] bild, String text, int y, int x0, int x1)
	{
		char[] chars = text.toCharArray();
		int shLen = x1 - x0 - 3;
		int start = chars.length > shLen ? x0 + 1 : x0 + 2;
		int end = Math.min(chars.length + start, x1 - 1);
		if(end - start >= 0)
			System.arraycopy(chars, 0, bild[y], start, end - start);
	}
}