package bootcamp.proyectoFinal.controller;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import bootcamp.proyectoFinal.email.Email;
import bootcamp.proyectoFinal.email.EmailService;
import bootcamp.proyectoFinal.models.Requests.TicketRequest;
import bootcamp.proyectoFinal.models.Ticket;
import bootcamp.proyectoFinal.services.TicketService;
import com.lowagie.text.DocumentException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bootcamp.proyectoFinal.Documentation.TicketExcelReport;
import bootcamp.proyectoFinal.Documentation.TicketPdfReport;
import bootcamp.proyectoFinal.repositories.ProductRepository;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    TicketService tS;
    @Autowired
    ProductRepository pS;
    @Autowired
    EmailService eS;
    
    @GetMapping
    public ResponseEntity<Object> getAll()
    {
        try
        {
            List<Ticket> tickets = tS.getAllTicket();
            if(tickets.size()==0){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Esta vacio, ves? no hay tickets aqui. Buena, ahora si hay uno pero no voy a asustarte. No es mi zona :)");
            }
            return ResponseEntity.status(HttpStatus.OK).body(tickets);
        }
        catch (Exception e)
        {
            return   ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error.");
        }

    }

    @GetMapping("/byClient/{dni}")
    public ResponseEntity<Object> getTicketsByDni(@PathVariable("dni") String dni)
    {
        try {
            List<Ticket> tickets = tS.getByClient(dni);
        if(tickets.size()!=0)
        {
            return ResponseEntity.status(HttpStatus.OK).body(tickets);
        }
        else {
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).body("Empty");
        }
        } catch (Exception e) {
            return   ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error.");
        }
    }
    
    @GetMapping("/byId/{id}")
    public ResponseEntity<Object> getTicketsById(@PathVariable("id") long id)
    {   
        try {
            Optional<Ticket> ticket = tS.getById(id);
            if(ticket.isPresent())
            {
                return ResponseEntity.status(HttpStatus.OK).body(ticket.get());
            }
            else {
                return  ResponseEntity.status(HttpStatus.NO_CONTENT).body("Not Found");
            }
        } catch (Exception e) {
            return   ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }
    
    @PostMapping("/saveCls")
    public ResponseEntity<Object> save(@RequestBody TicketRequest ticket)
    {
        try
        {
           Ticket t = tS.saveTicket(new Ticket(ticket));
            eS.sendEmail(new Email("email@email.com", "Your ticket Number: " + t.getTicketId(), " Thanks!"));
            return ResponseEntity.status(HttpStatus.CREATED).body("Entity Created");
        }
        catch (Exception e)
        {
            return   ResponseEntity.status(500).body("Error.");
        }
    
    }
    @DeleteMapping("/deleteProd/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") long id)
    {
        try {
            boolean f = tS.deleteTicket(id);
            if(f)
            {
                return ResponseEntity.status(HttpStatus.OK).body("Success.");
            }
            else
            {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Ticket Not found.");
            }
            
        } catch (Exception e) {
            return   ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }
    


    




     @GetMapping("/pdf/all")
    public void getAllPDF(HttpServletResponse response) throws DocumentException, IOException
    {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment;filename=Ticket-ListPDF.pdf");

        List<Ticket> tickets = tS.getAllTicket();
        TicketPdfReport pdfclass = new TicketPdfReport(tickets);
        pdfclass.export(response);
    }

   @GetMapping("/pdf/{dni}")
    public void getAllPDF(@PathVariable("dni") String dni, HttpServletResponse response) throws DocumentException, IOException
    {

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment;filename=Ticket-ListPDF.pdf");

        List<Ticket> tickets = tS.getByClient(dni);
        TicketPdfReport pdfclass = new TicketPdfReport(tickets);
        pdfclass.export(response);
    }

    @GetMapping("/excel/all")
    public void getAllExcel(HttpServletResponse response) throws DocumentException, IOException
    {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=TICKET-ListExcel.xlsx");
        List<Ticket> tickets = tS.getAllTicket();
        TicketExcelReport sER = new TicketExcelReport(tickets);
        sER.export(response);
    }

    @GetMapping("/excel/{dni}")
    public void getAllExcelByClient(@PathVariable("dni") String dni, HttpServletResponse response) throws DocumentException, IOException
    {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=TICKET-ListExcel.xlsx");
        List<Ticket> tickets = tS.getByClient(dni);
        TicketExcelReport sER = new TicketExcelReport(tickets);
        sER.export(response);
    }

}
