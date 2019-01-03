package charakter;

import java.util.*;
import kampf.*;
import kartenset.*;
import sprites.*;

public class NKDarstellung
{
	private SpriteList spriteList;
	private NKampf2 nk2;
	private KarteBild3 kb3;
	private List<NKTeilnehmer> spieler;
	private List<NKTeilnehmer> gegner;

	public NKDarstellung(SpriteList spriteList, NKampf2 nk2)
	{
		this.spriteList = spriteList;
		this.nk2 = nk2;
		kb3 = new KarteBild3();
		spieler = new ArrayList<>();
		gegner = new ArrayList<>();
		for(NTeilnehmer nSpieler : nk2.nKampf.getAlleSpieler())
		{
			spieler.add(new NKTeilnehmer(spriteList, kb3, nSpieler, kb3.hoehe() * 2, 0));
		}
		for(NTeilnehmer nGegner : nk2.nKampf.getAlleGegner())
		{
			gegner.add(new NKTeilnehmer(spriteList, kb3, nGegner, 0, 0));
		}
	}
}