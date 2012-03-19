package patentretrieval;

import java.io.File;
import java.io.IOException;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

public class Indexer {

	private File indexDir;
	StandardAnalyzer analyzer;
	IndexWriterConfig config;
	Directory d;
	IndexWriter w;

	Indexer(File dirname) {
		indexDir = dirname;

		analyzer = new StandardAnalyzer(Version.LUCENE_35);
		config = new IndexWriterConfig(Version.LUCENE_35, analyzer);

		try {
			d = new SimpleFSDirectory(indexDir);
			w = new IndexWriter(d, config);
		} catch (CorruptIndexException ex) {
			System.err.println("CorruptIndexException");
		} catch (LockObtainFailedException ex) {
			System.err.println("LockObtainFailedException");
		} catch (IOException ex) {
			System.err.println("IOException");
		}
	}
	
	public void addDoc(PatentDocument pd){
		try{
			Document doc = new Document();
			
			if (pd.getDOCNO() != null){
				doc.add(new Field("DOCNO",pd.getDOCNO().toString(),Field.Store.YES,Field.Index.ANALYZED));
			} 
					
			if (pd.getAPPNO() != null){
				doc.add(new Field("APPNO",pd.getAPPNO().toString(),Field.Store.YES,Field.Index.ANALYZED));
			}
			if (pd.getAPPDATE() != null){
				doc.add(new Field("APPDATE",pd.getAPPDATE().toString(),Field.Store.YES,Field.Index.ANALYZED));
			}
			if (pd.getPUBNO() != null){	
				doc.add(new Field("PUBNO",pd.getPUBNO().toString(),Field.Store.YES,Field.Index.ANALYZED));
			}
			if (pd.getPUBTYPE() != null){
				doc.add(new Field("PUBTYPE",pd.getPUBTYPE().toString(),Field.Store.YES,Field.Index.ANALYZED));
			}
			if (pd.getPUBDATE() != null){
				doc.add(new Field("PUBDATE",pd.getPUBDATE().toString(),Field.Store.YES,Field.Index.ANALYZED));
			}
			if (pd.getPATNO() != null){
				doc.add(new Field("PATNO",pd.getPATNO().toString(),Field.Store.YES,Field.Index.ANALYZED));
			}
			if (pd.getPATTYPE() != null){
				doc.add(new Field("PATTYPE",pd.getPATTYPE().toString(),Field.Store.YES,Field.Index.ANALYZED));
			}
			if (pd.getPRIIPC() != null){
				doc.add(new Field("PRIIPC",pd.getPRIIPC().toString(),Field.Store.YES,Field.Index.ANALYZED));
			}
			if (pd.getIPCVER() != null){
				doc.add(new Field("IPCVER",pd.getIPCVER().toString(),Field.Store.YES,Field.Index.ANALYZED));
			}
			if (pd.getPRIUSPC() != null){
				doc.add(new Field("PRIUSPC",pd.getPRIUSPC().toString(),Field.Store.YES,Field.Index.ANALYZED));
			}
			if (pd.getPRIORITY() != null){
				doc.add(new Field("PRIORITY",pd.getPRIORITY().toString(),Field.Store.YES,Field.Index.ANALYZED));
			}
			if (pd.getCITATION() != null){
				doc.add(new Field("CITATION",pd.getCITATION().toString(),Field.Store.YES,Field.Index.ANALYZED));
			}
			if (pd.getINVENTOR() != null){
				doc.add(new Field("INVENTOR",pd.getINVENTOR().toString(),Field.Store.YES,Field.Index.ANALYZED));
			}
			if (pd.getASSIGNEE() != null){
				doc.add(new Field("ASSIGNEE",pd.getASSIGNEE().toString(),Field.Store.YES,Field.Index.ANALYZED));
			}
			if (pd.getTITLE() != null){
				doc.add(new Field("TITLE",pd.getTITLE().toString(),Field.Store.YES,Field.Index.ANALYZED));
			}
			if (pd.getABST() != null){
				doc.add(new Field("ABST",pd.getABST().toString(),Field.Store.YES,Field.Index.ANALYZED));
			}
			if (pd.getSPEC() != null){
				doc.add(new Field("SPEC",pd.getSPEC().toString(),Field.Store.YES,Field.Index.ANALYZED));
			}
			if (pd.getCLAIM() != null){
				doc.add(new Field("CLAIM",pd.getCLAIM().toString(),Field.Store.YES,Field.Index.ANALYZED));
			}	
			
			w.addDocument(doc);
		}catch(IOException ex){
			System.err.println("IOException");
		}
	}
	
	void search(String queryString, String tag) throws IOException, ParseException {
		Query q = new QueryParser(Version.LUCENE_35, tag, analyzer).parse(queryString);

		int hitsPerPage = 10;
		
		IndexReader reader = IndexReader.open(d);
		try (IndexSearcher searcher = new IndexSearcher(reader)) {
			TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
			searcher.search(q, collector);
			ScoreDoc[] hits = collector.topDocs().scoreDocs;

			System.out.println("Found " + hits.length + " hits.");
			for (int i = 0; i < hits.length; ++i) {
				int docId = hits[i].doc;
				Document d1 = searcher.doc(docId);
				System.out.println((i + 1) + ". " + d1.get("TITLE"));
			}
		}
	}
	
	private static void addDoc(IndexWriter w, String value) throws IOException {
		Document doc = new Document();
		doc.add(new Field("title", value, Field.Store.YES, Field.Index.ANALYZED));
		w.addDocument(doc);
	}
	
	public void IndexerTute() {

		try {
			addDoc(w, "Lucene in Action");
			addDoc(w, "Lucene for Dummies");
			addDoc(w, "Managing Gigabytes");
			addDoc(w, "The Art of Computer Science");
			w.close();

		} catch (IOException ex) {
			System.err.println("IOException");
		}
		
		
//		String querystr = "Managing";
//
//		Query q = new QueryParser(Version.LUCENE_35, "title", analyzer).parse(querystr);
//
//		int hitsPerPage = 10;
//		IndexReader reader = IndexReader.open(d);
//		IndexSearcher searcher = new IndexSearcher(reader);
//		TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
//		searcher.search(q, collector);
//		ScoreDoc[] hits = collector.topDocs().scoreDocs;
//
//		System.out.println("Found " + hits.length + " hits.");
//		for (int i = 0; i < hits.length; ++i) {
//			int docId = hits[i].doc;
//			Document d1 = searcher.doc(docId);
//			System.out.println((i + 1) + ". " + d1.get("title"));
//		}
//
//		searcher.close();
	}

	

	
}
