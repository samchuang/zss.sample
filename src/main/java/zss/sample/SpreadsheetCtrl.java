package zss.sample;

import java.io.ByteArrayOutputStream;

import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zss.model.Book;
import org.zkoss.zss.model.Exporter;
import org.zkoss.zss.model.Exporters;
import org.zkoss.zss.ui.Spreadsheet;
import org.zkoss.zul.Filedownload;

public class SpreadsheetCtrl extends SelectorComposer<Component> {

	@Wire
	Spreadsheet spreadsheet;
	
	@Listen("onClick=#exportExcel")
	public void exportToExcel() {
		Book wb = spreadsheet.getBook();
		Exporter c = Exporters.getExporter("excel");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		c.export(wb, baos);
		
		Filedownload.save(baos.toByteArray(), "application/file",
				wb.getBookName());
	}
	
	@Listen("onClick=#exportPDF")
	public void exportToPDF() {
	    Book wb = spreadsheet.getBook();
	    Exporter c = Exporters.getExporter("pdf");
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    c.export(wb, baos);
	    
	    AMedia amedia = new AMedia("generatedReport.pdf", "pdf", "application/pdf", baos.toByteArray());
	    Filedownload.save(amedia);
	}
	
	@Listen("onClick=#exportHTML")
	public void exportToHTML() {
	    Book wb = spreadsheet.getBook();
	    Exporter c = Exporters.getExporter("html");
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    c.export(wb, baos);
	    
	    AMedia amedia = new AMedia("generatedReport.html", "html", "text/html", baos.toByteArray());
		Filedownload.save(amedia);
	}
}
