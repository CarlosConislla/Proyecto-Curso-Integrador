package com.contrato.util;

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

public class ListarEmpleadoEXCEL {

    private XSSFWorkbook libro;
    private XSSFSheet hoja;

    private List<Empleado> empleadoList;

    public ListarEmpleadoEXCEL(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
        libro = new XSSFWorkbook();
        hoja = libro.createSheet("Empleados");
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

        cell = fila.createCell(3);
        cell.setCellValue("DNI");
        cell.setCellStyle(style);

        cell = fila.createCell(4);
        cell.setCellValue("EDAD");
        cell.setCellStyle(style);

        cell = fila.createCell(5);
        cell.setCellValue("I.CONTRATO");
        cell.setCellStyle(style);

        cell = fila.createCell(6);
        cell.setCellValue("F.CONTRATO");
        cell.setCellStyle(style);

        cell = fila.createCell(7);
        cell.setCellValue("DEPARTAMENTO");
        cell.setCellStyle(style);
    }

    private void EscribirDatosDeLaTabla(){
        int numeroFilas = 1;

        CellStyle style = libro.createCellStyle();
        XSSFFont font = libro.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Empleado empleado : empleadoList){
            Row fila = hoja.createRow(numeroFilas++);

            Cell cell = fila.createCell(0);
            cell.setCellValue(empleado.getId());
            hoja.autoSizeColumn(0);
            cell.setCellStyle(style);

            cell = fila.createCell(1);
            cell.setCellValue(empleado.getNombre());
            hoja.autoSizeColumn(1);
            cell.setCellStyle(style);

            cell = fila.createCell(2);
            cell.setCellValue(empleado.getApellido());
            hoja.autoSizeColumn(2);
            cell.setCellStyle(style);

            cell = fila.createCell(3);
            cell.setCellValue(empleado.getDni());
            hoja.autoSizeColumn(3);
            cell.setCellStyle(style);

            cell = fila.createCell(4);
            cell.setCellValue(empleado.getEdad());
            hoja.autoSizeColumn(4);
            cell.setCellStyle(style);

            cell = fila.createCell(5);
            cell.setCellValue(empleado.getIcontrato().toString());
            hoja.autoSizeColumn(5);
            cell.setCellStyle(style);

            cell = fila.createCell(6);
            cell.setCellValue(empleado.getFcontrato().toString());
            hoja.autoSizeColumn(6);
            cell.setCellStyle(style);

            cell = fila.createCell(7);
            cell.setCellValue(empleado.getDepartamento().getNombre());
            hoja.autoSizeColumn(7);
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
