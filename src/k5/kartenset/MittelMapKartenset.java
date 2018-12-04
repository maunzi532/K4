package k5.kartenset;

import k5.dungeonmap.*;
import java.util.*;

public interface MittelMapKartenset
{
	MapKarte start();

	MapKarte ziel();

	List<MapKarte> hauptWeg(int anz);
}