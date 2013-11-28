package se.jiderhamn;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitClassloaderRunner.class)
public class LeakConfirmingTest {

	@Test
	@Leaks(value = false, haltBeforeError = false)
	public void triggerLeak() {
		InputStream is = null;
		try {
			is = new FileInputStream("./app.truststore");
			KeyStore keyStore = KeyStore.getInstance("JKS");
			keyStore.load(is, "changeit".toCharArray());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
