package bootcamp.proyectoFinal.Documentation;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import bootcamp.proyectoFinal.models.Ticket;
import bootcamp.proyectoFinal.models.TicketDetails;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class TicketPdfReport {

    private List<Ticket> tickets;

    public TicketPdfReport(List<Ticket> tickets)
    {
        this.tickets = tickets;
    }

    public void HeaderTable(PdfPTable table)
    {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.cyan);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("TICKET ID"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("CLIENT DNI"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("PRODUCT CODE"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("PRODUCT AMMOUNT"));
        table.addCell(cell);
    }
    public void BodyTable(PdfPTable table)
    {   
        for(Ticket t: tickets)
            for (TicketDetails td : t.getTicketDetails()) {
                {
                    table.addCell(String.valueOf(t.getTicketId()));
                    table.addCell(String.valueOf(t.getClient().getDni()));
                    table.addCell(String.valueOf(td.getProduct().getCode()));
                    table.addCell(String.valueOf(td.getAmmountProducts()));
                }
        }
    }
    public void export(HttpServletResponse hsr) throws DocumentException, IOException
    {
    Document doc = new Document(PageSize.A4);
    PdfWriter.getInstance(doc, hsr.getOutputStream());
    doc.open();
    Paragraph paragraph = new Paragraph("Ticket List");
    paragraph.setSpacingAfter(5);
    paragraph.setAlignment(Element.ALIGN_CENTER);
    doc.add(paragraph);
    PdfPTable pdf = new PdfPTable(4);
    HeaderTable(pdf);
    BodyTable(pdf);
    doc.add(pdf);
    doc.close();

    }
}


