package webComm;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebValue {

	public String getWebValue(final String url, final String selector) {
		try {
			// Seite laden
			final Document doc = Jsoup.connect(url).get();

			// Alle Listenelemente über den entsprechenden Selektor markieren
			// Ein Leerzeichen initiert ein Kindelement des Elternelementes
			// (links)
			// div#hauptseite-ergeignisse => Der DIV mit der ID
			// hauptseite-ereignisse (# => id)
			// div.inhalt => Der DIV mit der Klasse inhalt (. => class)
			final Elements ereignisse = doc.select(selector);

			// Selektierte Elemente ausgeben ohne HTML-Tags
			for (final Element e : ereignisse)
				return e.text();
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return " 1013.6 ";	
	}
}