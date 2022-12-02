package com.contrato.util;

import com.contrato.models.Departamento;
import com.contrato.models.Empleado;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListarDptaEXCEL {

    private XSSFWorkbook libro;

    private XSSFSheet hoja;

    private List<Departamento> departamentoList;

    public ListarDptaEXCEL(List<Departamento> departamentoList) {
        this.departamentoList = departamentoList;
        libro = new XSSFWorkbook();
        hoja = libro.createSheet("Departamentos");
    }

    private void EscribirCabeceraDeLaTabla (){
        Row fila = hoja.createRow(0);

        CellStyle style = libro.createCellStyle();
        XSSFFont font = libro.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        Cell cell = fila.createCell(0);
        cell.setCellValue("ID");
        cell.setCellStyle(style);

        cell = fila.createCell(1);
        cell.setCellValue("NOMBRE");
        cell.setCellStyle(style);

        cell = fila.createCell(2);
        cell.setCellValue("APELLIDOS");
        cell.setCellStyle(style);
    }

    private void EscribirDatosDeLaTabla(){
        int numeroFilas = 1;

        CellStyle style = libro.createCellStyle();
        XSSFFont font = libro.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Departamento departamento : departamentoList){
            Row fila = hoja.createRow(numeroFilas++);

            Cell cell = fila.createCell(0);
            cell.setCellValue(departamento.getId());
            hoja.autoSizeColumn(0);
            cell.setCellStyle(style);

            cell = fila.createCell(1);
            cell.setCellValue(departamento.getNombre());
            hoja.autoSizeColumn(1);
            cell.setCellStyle(style);
        }
    }

    public void exportar(HttpServletResponse response) throws IOException {
        EscribirCabeceraDeLaTabla();
        EscribirDatosDeLaTabla();

        ServletOutputStream outputStream = response.getOutputStream();
        libro.write(outputStream);

        libro.close();
        outputStream.close();
    }
}
