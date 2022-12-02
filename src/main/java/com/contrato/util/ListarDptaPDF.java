package com.contrato.util;

import com.contrato.models.Departamento;
import com.contrato.models.Empleado;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ListarDptaPDF {

    private List<Departamento> departamentoList;

    public ListarDptaPDF(List<Departamento> departamentoList) {
        this.departamentoList = departamentoList;
    }

    private void EscribirCabeceraDeLaTabla(PdfPTable table){
        PdfPCell cell = new PdfPCell();

        cell.setBackgroundColor(Color.blue);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("NOMBRE", font));
        table.addCell(cell);
    }

    private void EscribirDatosDeLaTabla(PdfPTable table){
        for (Departamento departamento : departamentoList){
            table.addCell(String.valueOf(departamento.getId()));
            table.addCell(departamento.getNombre());
        }
    }

    public void exportar(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.blue);
        font.setSize(18);

        Paragraph titulo = new Paragraph("Lista De Departamentos");
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(titulo);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        table.setWidths(new float[] {1f, 2.3f});
        table.setWidthPercentage(110);

        EscribirCabeceraDeLaTabla(table);
        EscribirDatosDeLaTabla(table);

        document.add(table);
        document.close();
    }
}
