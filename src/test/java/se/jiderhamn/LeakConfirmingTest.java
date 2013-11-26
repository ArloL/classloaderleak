package se.jiderhamn;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitClassloaderRunner.class)
public class LeakConfirmingTest {

	@Test
	@Leaks(value = false, haltBeforeError = false)
	public void triggerLeak() throws Exception {
		InputStream is = null;
		try {
			is = new FileInputStream("./app.truststore");
			KeyStore keyStore = KeyStore.getInstance("JKS");
			keyStore.load(is, "changeit".toCharArray());
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (is != null) {
				is.close();
			}
			is = null;
		}
	}

}
