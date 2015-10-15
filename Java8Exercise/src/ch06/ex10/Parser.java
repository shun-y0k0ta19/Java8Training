package ch06.ex10;

import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML.Attribute;
import javax.swing.text.html.HTML.Tag;
import javax.swing.text.html.HTMLEditorKit.ParserCallback;
import javax.swing.text.html.parser.ParserDelegator;

public class Parser extends ParserCallback {
	List<URL> linkList = new ArrayList<URL>();

	private List<URL> getLinkList() {
		return linkList;
	}
	
	@Override
	public void handleStartTag(Tag tag, MutableAttributeSet attr, int pos) {
		if(tag.equals(Tag.A)) {
			try {
				linkList.add(new URL((String) attr.getAttribute(Attribute.HREF)));
			} catch (MalformedURLException e) {
				//throw new RuntimeException("URL is not generated.", e);
			}
		}
	}

	public static List<URL> getLinks(String page) {
		Parser parser = new Parser();
		ParserDelegator pd = new ParserDelegator();
		try {
			pd.parse(new StringReader(page), parser, true);
		} catch (IOException e) {
			throw new RuntimeException("HTML is not parsed", e);
		}
		return parser.getLinkList();
	}
}
