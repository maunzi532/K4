package effektkarten.karten;

import effektkarten.ansichtkarte.*;

public interface Gegner extends EffektKarte
{
	int minExp();

	int maxExp();
}