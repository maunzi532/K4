package charakter;

import java.util.*;
import m.*;
import plane.*;
import dungeonmap.*;
import sets.*;

public class MTest2 implements Game
{
	private Hauptklasse hauptklasse;
	private MapBild mapBild;
	private PlaneFrame planeFrame;

	@Override
	public void init(PlaneRenderer screen)
	{
		planeFrame = new PlaneFrame(0, 0, screen.height, screen.width);
		hauptklasse = new Hauptklasse(new Einstellungen(), new SetV2MittelMapKarten(), new SetV2MapKarten(),
				new SetV2Klassen(), new SetV2Gegner(), new SetV2Waffen(), new SetV2Aktionen());
		//m.klassenAuswahl();
		hauptklasse.klassenAuswahl("K", "B");
		mapBild = new MapBild(hauptklasse.dungeonMap);
		//System.out.println(mapBild.erstelleTextBild(hauptklasse.figuren, 0));
		//hauptklasse.zielAngeben(mapBild);
	}

	@Override
	public void fillLists(List<Plane> planes, List<PlaneFrame> frames)
	{
		planes.add(new TextPlane(0, 7, mapBild.erstelleTextBild1(hauptklasse.figuren, 0)));
		frames.add(planeFrame);
	}

	@Override
	public void handleInput(int input)
	{

	}
}