package backend.struktur;

import java.util.*;
import java.util.stream.*;

public class StrukturAlsText
{
	public static String[] strukturAlsText(Struktur s1)
	{
		char[][] text = new char[7][7];
		for(int iy = 0; iy < 7; iy++)
			for(int ix = 0; ix < 7; ix++)
				text[iy][ix] = 'W';
		if(s1.loch)
		{
			for(int iy = 2; iy < 5; iy++)
				for(int ix = 0; ix < 7; ix++)
					text[iy][ix] = ' ';
			for(int iy = 1; iy < 6; iy++)
				for(int ix = 1; ix < 6; ix++)
					text[iy][ix] = ' ';
			for(int iy = 0; iy < 7; iy++)
				for(int ix = 2; ix < 5; ix++)
					text[iy][ix] = ' ';
		}
		else
		{
			if((s1.pfadCode & 0b0001) != 0)
			{
				for(int n = 2; n <= 4; n++)
					text[n][6] = ' ';
				if((s1.mitte.connectCode & 0b0001) != 0)
					for(int n = 2; n <= 4; n++)
						text[n][5] = ' ';
				else
					for(int n = 2; n <= 4; n++)
						text[n][5] = '|';
			}
			if((s1.pfadCode & 0b0010) != 0)
			{
				for(int n = 2; n <= 4; n++)
					text[0][n] = ' ';
				if((s1.mitte.connectCode & 0b0010) != 0)
					for(int n = 2; n <= 4; n++)
						text[1][n] = ' ';
				else
					for(int n = 2; n <= 4; n++)
						text[1][n] = '-';
			}
			if((s1.pfadCode & 0b0100) != 0)
			{
				for(int n = 2; n <= 4; n++)
					text[n][0] = ' ';
				if((s1.mitte.connectCode & 0b0100) != 0)
					for(int n = 2; n <= 4; n++)
						text[n][1] = ' ';
				else
					for(int n = 2; n <= 4; n++)
						text[n][1] = '|';
			}
			if((s1.pfadCode & 0b1000) != 0)
			{
				for(int n = 2; n <= 4; n++)
					text[6][n] = ' ';
				if((s1.mitte.connectCode & 0b1000) != 0)
					for(int n = 2; n <= 4; n++)
						text[5][n] = ' ';
				else
					for(int n = 2; n <= 4; n++)
						text[5][n] = '-';
			}
			String k1;
			switch(s1.mitte)
			{
				case LEER:
					k1 = "         ";
					break;
				case BLOCKIERT:
					k1 = "WWWWWWWWW";
					break;
				case KURVE:
					k1 = "\\  W\\ WW\\";
					break;
				case DOPPELKURVE:
					k1 = "\\   \\   \\";
					break;
				case GERADE:
					k1 = "| || || |";
					break;
				case T:
					k1 = "|  |  |  ";
					break;
				default:
					throw new RuntimeException();
			}
			for(int iy = 0; iy < 3; iy++)
				for(int ix = 0; ix < 3; ix++)
					text[iy + 2][ix + 2] = k1.charAt(iy * 3 + ix);
		}
		return Arrays.stream(text).map(String::new).collect(Collectors.toList()).toArray(new String[7]);
	}
}