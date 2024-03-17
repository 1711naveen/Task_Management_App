package com.sam.demo.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sam.demo.entity.Task;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class PdfGenerator {
	public boolean exportPdf(HttpServletResponse response,List<Task> tasks, File file) throws Exception {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();
		
		Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTitle.setSize(20);
		fontTitle.setColor(255, 159, 23);
		
		Paragraph p = new Paragraph("Task List",fontTitle);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(p);
		
		PdfPTable table = new PdfPTable(5);
		table.setSpacingBefore(10);
		
		table.addCell("S.No");
		table.addCell("Task");
		table.addCell("Description");
		table.addCell("Task Type");
		table.addCell("Due Date");
		Integer i=1;
		for(Task task : tasks) {
			table.addCell(i+"");
			table.addCell(task.getTitle());
			table.addCell(task.getDescription());
			table.addCell(task.getType());
			table.addCell(task.getDueDate()+"");
			i++;
		}
		
		document.add(table);
		document.close();
		
		return true;
	}
}
