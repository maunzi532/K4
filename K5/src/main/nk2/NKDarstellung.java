package main.nk2;

import java.util.*;
import kampf.*;
import karteAnsicht.*;
import kartenset.*;
import sprites.*;

public class NKDarstellung
{
	private SpriteList spriteList;
	private NKampf2 nk2;
	private KarteBild3 kb3;
	private List<NKTeilnehmer> spieler;
	private List<NKTeilnehmer> gegner;
	private KarteSpriteList spieler0;
	private KarteSpriteList gegner0;

	public NKDarstellung(SpriteList spriteList, NKampf2 nk2)
	{
		this.spriteList = spriteList;
		this.nk2 = nk2;
		kb3 = new KarteBild3();
		spieler = new ArrayList<>();
		gegner = new ArrayList<>();
		spieler0 = new KarteSpriteList(spriteList, List.of(), kb3);
		gegner0 = new KarteSpriteList(spriteList, List.of(), kb3);
	}

	public void addCharakter()
	{
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