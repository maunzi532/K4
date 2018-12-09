package kartenset;

import effekt.*;
import java.util.*;
import org.junit.*;
import sets.*;

public class KarteBild3Test
{
	@Test
	public void test0()
	{
		KarteBild3 kb3 = new KarteBild3(new Laengen3(), new Rahmen3(), new Ersetzen3());
		System.out.println(Arrays.stream(kb3.karteBild("Wugu", null, null, null, null, null))
				.map(String::new).reduce((e, f) -> e + "\n" + f).orElseThrow());
		System.out.println(Arrays.stream(kb3.karteBild("Fortgeschrittener Eisstab", 0b11010100,
				List.of("A", "10", "B", "2"), List.of("C", "-4", "D", "4"), null, null))
				.map(String::new).reduce((e, f) -> e + "\n" + f).orElseThrow());
		System.out.println(Arrays.stream(kb3.karteBild("Fortgeschrittener Magischer Stab", 0b11111111,
				List.of("A", "10", "Blablah", "2"), List.of("C", "-4", "D", "12868884"), List.of("12000"),
				List.of(new KartenEffekt("# ", "Wugu Wuguwugu A_+_1 V_-_1", 0))))
				.map(String::new).reduce((e, f) -> e + "\n" + f).orElseThrow());
		System.out.println(Arrays.stream(kb3.karteBild(new SetV2Gegner().gibKarte("Koba")))
				.map(String::new).reduce((e, f) -> e + "\n" + f).orElseThrow());
	}
}