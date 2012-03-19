package patentretrieval;

public class PatentDocument {

	private StringBuilder DOCNO;
	private StringBuilder APPNO;
	private StringBuilder APPDATE;
	private StringBuilder PUBNO;
	private StringBuilder PUBTYPE;
	private StringBuilder PUBDATE;
	private StringBuilder PATNO;
	private StringBuilder PATTYPE;
	private StringBuilder PRIIPC;
	private StringBuilder IPCVER;
	private StringBuilder PRIUSPC;
	private StringBuilder PRIORITY;
	private StringBuilder CITATION;
	private StringBuilder INVENTOR;
	private StringBuilder ASSIGNEE;
	private StringBuilder TITLE;
	private StringBuilder ABST;
	private StringBuilder SPEC;
	private StringBuilder CLAIM;
	
	
	public void setABST(StringBuilder ABST) {
		this.ABST = ABST;
	}

	public void setAPPDATE(StringBuilder APPDATE) {
		this.APPDATE = APPDATE;
	}

	public void setAPPNO(StringBuilder APPNO) {
		this.APPNO = APPNO;
	}

	public void setASSIGNEE(StringBuilder ASSIGNEE) {
		this.ASSIGNEE = ASSIGNEE;
	}

	public void setCITATION(StringBuilder CITATION) {
		this.CITATION = CITATION;
	}

	public void setCLAIM(StringBuilder CLAIM) {
		this.CLAIM = CLAIM;
	}

	public void setDOCNO(StringBuilder DOCNO) {
		this.DOCNO = DOCNO;
	}

	public void setINVENTOR(StringBuilder INVENTOR) {
		this.INVENTOR = INVENTOR;
	}

	public void setIPCVER(StringBuilder IPCVER) {
		this.IPCVER = IPCVER;
	}

	public void setPATNO(StringBuilder PATNO) {
		this.PATNO = PATNO;
	}

	public void setPATTYPE(StringBuilder PATTYPE) {
		this.PATTYPE = PATTYPE;
	}

	public void setPRIIPC(StringBuilder PRIIPC) {
		this.PRIIPC = PRIIPC;
	}

	public void setPRIORITY(StringBuilder PRIORITY) {
		this.PRIORITY = PRIORITY;
	}

	public void setPRIUSPC(StringBuilder PRIUSPC) {
		this.PRIUSPC = PRIUSPC;
	}

	public void setPUBDATE(StringBuilder PUBDATE) {
		this.PUBDATE = PUBDATE;
	}

	public void setPUBNO(StringBuilder PUBNO) {
		this.PUBNO = PUBNO;
	}

	public void setPUBTYPE(StringBuilder PUBTYPE) {
		this.PUBTYPE = PUBTYPE;
	}

	public void setSPEC(StringBuilder SPEC) {
		this.SPEC = SPEC;
	}

	public void setTITLE(StringBuilder TITLE) {
		this.TITLE = TITLE;
	}

	public StringBuilder getABST() {
		return ABST;
	}

	public StringBuilder getAPPDATE() {
		return APPDATE;
	}

	public StringBuilder getAPPNO() {
		return APPNO;
	}

	public StringBuilder getASSIGNEE() {
		return ASSIGNEE;
	}

	public StringBuilder getCITATION() {
		return CITATION;
	}

	public StringBuilder getCLAIM() {
		return CLAIM;
	}

	public StringBuilder getDOCNO() {
		return DOCNO;
	}

	public StringBuilder getINVENTOR() {
		return INVENTOR;
	}

	public StringBuilder getIPCVER() {
		return IPCVER;
	}

	public StringBuilder getPATNO() {
		return PATNO;
	}

	public StringBuilder getPATTYPE() {
		return PATTYPE;
	}

	public StringBuilder getPRIIPC() {
		return PRIIPC;
	}

	public StringBuilder getPRIORITY() {
		return PRIORITY;
	}

	public StringBuilder getPRIUSPC() {
		return PRIUSPC;
	}

	public StringBuilder getPUBDATE() {
		return PUBDATE;
	}

	public StringBuilder getPUBNO() {
		return PUBNO;
	}

	public StringBuilder getPUBTYPE() {
		return PUBTYPE;
	}

	public StringBuilder getSPEC() {
		return SPEC;
	}

	public StringBuilder getTITLE() {
		return TITLE;
	}
}