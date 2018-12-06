package kartenset;

import dungeonmap.*;
import java.util.*;

public interface MittelMapKartenset
{
	MapKarte start();

	MapKarte ziel();

	List<MapKarte> hauptWeg(int anz);
}