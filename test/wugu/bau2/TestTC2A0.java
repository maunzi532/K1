package wugu.bau2;

import com.jme3.system.*;
import org.junit.*;

public class TestTC2A0
{
	@Test
	public void argh()
	{
		TestTC2A t = new TestTC2A();
		t.setShowSettings(false);
		AppSettings settings0 = new AppSettings(true);
		settings0.put("Width", 1200);
		settings0.put("Height", 675);
		t.setSettings(settings0);
		t.start();

		try
		{
			Thread.sleep(10000);
		}catch(InterruptedException e)
		{
			Assert.fail();
		}
	}
}