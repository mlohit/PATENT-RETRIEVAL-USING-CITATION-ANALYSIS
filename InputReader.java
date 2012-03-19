package patentretrieval;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class InputReader extends DefaultHandler {

	File dirname;
	StringBuilder textBuffer;
	DocTags tagType;
	PatentDocument pd;
	Indexer idx;

	InputReader(File dirname) {
		textBuffer = new StringBuilder();
		tagType = DocTags.NOVAL;
		pd = new PatentDocument();
		this.dirname = dirname;
	}

	public Indexer readInputFile(String filename) {

		try {
			idx = new Indexer(this.dirname);
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(filename, this);
			
			idx.w.close();
			
			return idx;
		} catch (ParserConfigurationException | SAXException ex) {
			System.err.println(ex);
			return null;
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
			return null;
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
		switch (qName) {
			case "DOCNO":
				tagType = DocTags.DOCNO;
				break;
			case "APP-NO":
				tagType = DocTags.APPNO;
				break;
			case "APP-DATE":
				tagType = DocTags.APPDATE;
				break;
			case "PUB-NO":
				tagType = DocTags.PUBNO;
				break;
			case "PUB-TYPE":
				tagType = DocTags.PUBTYPE;
				break;
			case "PUB-DATE":
				tagType = DocTags.PUBDATE;
				break;
			case "PAT-NO":
				tagType = DocTags.PATNO;
				break;
			case "PAT-TYPE":
				tagType = DocTags.PATTYPE;
				break;
			case "PRI-IPC":
				tagType = DocTags.PRIIPC;
				break;
			case "IPC-VER":
				tagType = DocTags.IPCVER;
				break;
			case "PRIORITY":
				tagType = DocTags.PRIORITY;
				break;
			case "PRI-USPC":
				tagType = DocTags.PRIUSPC;
				break;
			case "CITATION":
				tagType = DocTags.CITATION;
				break;
			case "INVENTOR":
				tagType = DocTags.INVENTOR;
				break;
			case "ASSIGNEE":
				tagType = DocTags.ASSIGNEE;
				break;
			case "TITLE":
				tagType = DocTags.TITLE;
				break;
			case "ABST":
				tagType = DocTags.ABST;
				break;
			case "SPEC":
				tagType = DocTags.SPEC;
				break;
			case "CLAIM":
				tagType = DocTags.CLAIM;
				break;
			case "DOC":
				pd = new PatentDocument();
			default:
				tagType = DocTags.NOVAL;
				break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) {
		switch (tagType) {
			case DOCNO:
				pd.setDOCNO(textBuffer);
				break;
			case APPNO:
				pd.setAPPNO(textBuffer);
				break;
			case APPDATE:
				pd.setAPPDATE(textBuffer);
				break;
			case PUBNO:
				pd.setPUBNO(textBuffer);
				break;
			case PUBTYPE:
				pd.setPUBTYPE(textBuffer);
				break;
			case PUBDATE:
				pd.setPUBDATE(textBuffer);
				break;
			case PATNO:
				pd.setPATNO(textBuffer);
				break;
			case PATTYPE:
				pd.setPATTYPE(textBuffer);
				break;
			case PRIIPC:
				pd.setPRIIPC(textBuffer);
				break;
			case IPCVER:
				pd.setIPCVER(textBuffer);
				break;
			case PRIORITY:
				pd.setPRIORITY(textBuffer);
				break;
			case PRIUSPC:
				pd.setPRIUSPC(textBuffer);
				break;
			case CITATION:
				pd.setCITATION(textBuffer);
				break;
			case INVENTOR:
				pd.setINVENTOR(textBuffer);
				break;
			case ASSIGNEE:
				pd.setASSIGNEE(textBuffer);
				break;
			case TITLE:
				pd.setTITLE(textBuffer);
				break;
			case ABST:
				pd.setABST(textBuffer);
				break;
			case SPEC:
				pd.setSPEC(textBuffer);
				break;
			case CLAIM:
				pd.setCLAIM(textBuffer);
				break;
			default:
				break;
		}

		textBuffer = null;

		if (qName.equals("DOC")) {
			// call the indexer's function to create the doc
			idx.addDoc(pd);
		}
	}

	@Override
	public void characters(char buf[], int offset, int len) {
		String s = new String(buf, offset, len);

		if (textBuffer == null) {
			textBuffer = new StringBuilder(s);
		} else {
			textBuffer.append(s);
		}
	}

	@Override
	public void warning(SAXParseException exception) {
		System.err.println("WARNING: line " + exception.getLineNumber() + ": "
			+ exception.getMessage());
	}

	@Override
	public void error(SAXParseException exception) {
		System.err.println("ERROR: line " + exception.getLineNumber() + ": "
			+ exception.getMessage());
	}

	@Override
	public void fatalError(SAXParseException exception) throws SAXException {
		System.err.println("FATAL: line " + exception.getLineNumber() + ": "
			+ exception.getMessage());
		throw (exception);
	}
}
