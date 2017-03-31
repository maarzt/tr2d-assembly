import org.scijava.plugin.Plugin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

/**
 * @author Matthias Arzt
 */
public class PrintSciJavaPluginJson {

	public static void main(String... args) throws IOException {
		try (Writer writer = openOutputFile()) {
			writeCollectedJson(writer);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static BufferedWriter openOutputFile() throws FileNotFoundException {
		return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Plugin.class.getName()), StandardCharsets.UTF_8));
	}

	private static void writeCollectedJson(Writer writer) throws IOException {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Enumeration<URL> urls = loader.getResources("META-INF/json/" + Plugin.class.getName());
		while(urls.hasMoreElements()) {
			URL url = urls.nextElement();
			writer.write(getStringFromInputStream(url.openStream()));
		}
	}

	private static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}

}
