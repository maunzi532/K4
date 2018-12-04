package k5.dungeonmap;

import java.util.*;
import org.junit.*;
import k5.sets.*;

public class DungeonMapTest
{
	private DungeonMap dungeonMap;
	private SetV2MittelMapKarten mittelMapKarten;

	@Before
	public void before()
	{
		dungeonMap = new DungeonMap(5, 3, List.of(2));
		mittelMapKarten = new SetV2MittelMapKarten();
	}

	@Test
	public void test()
	{
		for(int i = 7; i < 35; i++)
		{
			dungeonMap.setFeld(i / 7, i % 7,
					new MapFeld(new MapKarte("\nXX XX\nXX XX\n     \nXX XX\nXX XX\n00000\n00000\n00000\n00000\n00000"), false));
		}
		dungeonMap.setFeld(4, 3,
				new MapFeld(new MapKarte("\nXX XX\nXX XX\n     \nXG HX\nXXSXX\n00000\n00000\n00000\n00000\n00000"), false));
		dungeonMap.setFeld(0, 3,
				new MapFeld(new MapKarte("\nXXZXX\nX   X\nXBBBX\nX   X\nXX XX\n00000\n00000\n00000\n00000\n00000"), false));
		System.out.println(dungeonMap.toString());
	}

	@Test
	public void mittelMapTest()
	{
		dungeonMap.erstelleMittelWeg(mittelMapKarten);
		System.out.println(dungeonMap.toString());
	}
}