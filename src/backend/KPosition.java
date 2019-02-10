package backend;

import com.jme3.export.*;
import java.io.*;

public class KPosition implements Savable
{
	public KPositionTyp t;
	public int x;
	public int z;
	public int y;
	public boolean verdeckt;
	public int drehung;

	public KPosition(int x, int z)
	{
		t = KPositionTyp.SPIELFELD;
		this.x = x;
		this.z = z;
	}

	public KPosition(KPositionTyp t, int x, int z, int y, boolean verdeckt, int drehung)
	{
		this.t = t;
		this.x = x;
		this.z = z;
		this.y = y;
		this.verdeckt = verdeckt;
		this.drehung = drehung;
	}

	@Override
	public String toString()
	{
		return "KPosition{" +
				"t=" + t +
				", x=" + x +
				", z=" + z +
				", y=" + y +
				", verdeckt=" + verdeckt +
				", drehung=" + drehung +
				'}';
	}

	@Override
	public void write(JmeExporter ex) throws IOException
	{

	}

	@Override
	public void read(JmeImporter im) throws IOException
	{

	}
}