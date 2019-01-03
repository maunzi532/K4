package charakter;

import kampf.*;
import karten.*;
import kartenset.*;
import sprites.*;

public class NKTeilnehmer
{
	private NTeilnehmer nt;
	private KarteSprite charakter;
	private KarteSprite hauptwaffe;
	private KarteSprite nebenwaffe;
	private KarteSprite aktion;
	private Sprite reihenfolge;
	private Sprite pfeil;
	private Sprite pfeilInfo;
	public int posY, posX;

	public NKTeilnehmer(SpriteList spriteList, KarteBild3 kb3, NTeilnehmer nt, int posY, int posX)
	{
		this.nt = nt;
		this.posY = posY;
		this.posX = posX;
		int abstand = kb3.breite() + 2;
		charakter = new KarteSprite(posY, posX, 0, kb3, nt.getCharakterKarte());
		hauptwaffe = new KarteSprite(posY, posX + abstand, 0, kb3, nt.getWaffeKarte(W.HW));
		nebenwaffe = new KarteSprite(posY, posX + abstand * 2, 0, kb3, nt.getWaffeKarte(W.NW));
		aktion = new KarteSprite(posY - kb3.hoehe() / 2, posX, -1, kb3, nt.getAktionKarte());
		spriteList.addSprite(charakter);
		spriteList.addSprite(hauptwaffe);
		spriteList.addSprite(nebenwaffe);
		spriteList.addSprite(aktion);
	}
}