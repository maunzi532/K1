package backend.struktur;

import java.util.*;
import org.junit.*;

public class ConnecterTest
{
	@Test
	public void T1()
	{
		Struktur s1 = new Struktur();
		Connecter c1 = new Connecter(null, s1);
		Arrays.stream(StrukturAlsText.strukturAlsText(s1)).forEach(System.out::println);

		Assert.assertEquals(1, c1.connectors.size());
	}

	@Test
	public void T2()
	{
		Struktur s1 = new Struktur(Mitte.BLOCKIERT, 14, 0);
		Connecter c1 = new Connecter(null, s1);
		Arrays.stream(StrukturAlsText.strukturAlsText(s1)).forEach(System.out::println);

		Assert.assertEquals(3, c1.connectors.size());
	}

	@Test
	public void T3()
	{
		Struktur s1 = new Struktur(Mitte.KURVE, 14, 0);
		Connecter c1 = new Connecter(null, s1);
		Arrays.stream(StrukturAlsText.strukturAlsText(s1)).forEach(System.out::println);

		Assert.assertEquals(3, c1.connectors.size());
	}
}