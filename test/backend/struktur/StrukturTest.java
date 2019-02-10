package backend.struktur;

import java.util.*;
import org.junit.*;

public class StrukturTest
{
	@Test
	public void T1()
	{
		Struktur s1 = new Struktur();

		Assert.assertTrue(s1.loch);
	}

	@Test
	public void T2()
	{
		Struktur s1 = new Struktur(Mitte.BLOCKIERT, 0, 0);

		Assert.assertFalse(s1.loch);
	}

	@Test
	public void T3()
	{
		for(Mitte m1 : Mitte.values())
		{
			Struktur s1 = new Struktur(m1, 15, 0);
			System.out.println(m1.name());
			Arrays.stream(StrukturAlsText.strukturAlsText(s1)).forEach(System.out::println);
			System.out.println();
		}
	}
}