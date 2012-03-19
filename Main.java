package patentretrieval;

import java.io.File;
import java.io.IOException;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.store.LockObtainFailedException;



public class Main {

    public static void main(String[] args) throws IOException, CorruptIndexException, LockObtainFailedException, ParseException {
        File input = new File("/media/1268B2AE68B28FC9/DC++ Downloads/patent retrieval/1994.txt");
	
	File index_dir = new File("/media/1268B2AE68B28FC9/DC++ Downloads/patent retrieval/indexdir");
	
	InputReader ir = new InputReader(index_dir);
	ir.readInputFile(input.toString());
	
	//Indexer idx = new Indexer(index_dir);	
	//idx.search("Takata","ASSIGNEE");
	//idx.search("Fastener means","TITLE");
	//idx.search("PATENT-US-GRT-1994-05274888","DOCNO");    
    }
}
