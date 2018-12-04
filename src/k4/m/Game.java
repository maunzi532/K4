package k4.m;

import java.util.*;
import k4.plane.*;

public interface Game
{
	void init(PlaneRenderer screen);

	void fillLists(List<Plane> planes, List<PlaneFrame> frames);

	void handleInput(int input);
}