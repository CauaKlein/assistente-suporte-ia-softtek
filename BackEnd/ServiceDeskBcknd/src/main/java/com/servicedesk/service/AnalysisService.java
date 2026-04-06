package com.servicedesk.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.text.DecimalFormat;

import com.servicedesk.model.KnowledgeBase;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class AnalysisService {

    private static final String EXCEL_FILE_PATH = "/Base_Chamados_V1.xlsx";

    // Método para ler e retornar o valor da célula como String
    private String getCellValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }

 // Método para imprimir números com prioridade '1 - Alto'
    public void printHighPriorityCalls() throws IOException {
        try (InputStream file = getClass().getResourceAsStream(EXCEL_FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheet("Base"); 
            if (sheet == null) {
                System.out.println("Planilha 'Base' não encontrada.");
                return;
            }

            boolean found = false;
            for (Row row : sheet) {
                Cell priorityCell = row.getCell(12); 
                if (priorityCell != null && priorityCell.getCellType() == CellType.STRING) {
                    String priority = priorityCell.getStringCellValue();
                    if ("1 - Alto".equals(priority)) {
                        Cell numberCell = row.getCell(0); 
                        if (numberCell != null && numberCell.getCellType() == CellType.STRING) {
                            if (!found) {
                                System.out.println("Número(s) com prioridade '1 - Alto':");
                                found = true;
                            }
                            System.out.println(numberCell.getStringCellValue());
                        }
                    }
                }
            }
            if (!found) {
                System.out.println("Nenhum número com prioridade '1 - Alto' encontrado.");
            }
        }
    }

 // Método para imprimir a primeira linha da base de chamados
    public void printFirstRow() throws IOException {
        try (InputStream file = getClass().getResourceAsStream(EXCEL_FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheet("Base");
            if (sheet == null) {
                System.out.println("Planilha 'Base' não encontrada.");
                return;
            }

            for (int rowIndex = 0; rowIndex < 1; rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row != null) {
                    for (int colIndex = 0; colIndex <= 17; colIndex++) {
                        Cell cell = row.getCell(colIndex);
                        System.out.print(getCellValue(cell) + "\t");
                    }
                    System.out.println();
                }
            }
        }
    }

 // Método para realizar a analise das categorias e printar em ordem descrescente
    public void analyzeCategories() throws IOException {
        try (InputStream file = getClass().getResourceAsStream(EXCEL_FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheet("Base");
            if (sheet == null) {
                System.out.println("Planilha 'Base' não encontrada.");
                return;
            }

            Map<String, Integer> categoryCounts = new HashMap<>();

            for (Row row : sheet) {
                Cell categoryCell = row.getCell(5); 
                String category = getCellValue(categoryCell);
                categoryCounts.put(category, categoryCounts.getOrDefault(category, 0) + 1);
            }

            List<String> analysisData = categoryCounts.entrySet()
                    .stream()
                    .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                    .map(entry -> entry.getKey() + ": " + entry.getValue())
                    .collect(Collectors.toList());

            KnowledgeBase.loadFromAnalysis(analysisData);

            for (String result : analysisData) {
                System.out.println(result);
            }
        }
    }

 // Método para realizar a analise de locais e printar em ordem descrescente
    public void analyzeLocations() throws IOException {
        try (InputStream file = getClass().getResourceAsStream(EXCEL_FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheet("Base");
            if (sheet == null) {
                System.out.println("Planilha 'Base' não encontrada.");
                return;
            }

            Map<String, Integer> locationCounts = new HashMap<>();
            int totalRows = 0;

            for (Row row : sheet) {
                Cell locationCell = row.getCell(15); 
                if (locationCell != null && locationCell.getCellType() == CellType.STRING) {
                    String location = locationCell.getStringCellValue();
                    locationCounts.put(location, locationCounts.getOrDefault(location, 0) + 1);
                    totalRows++;
                }
            }

            final int totalRowsFinal = totalRows;

            DecimalFormat df = new DecimalFormat("0.00%");

            List<String> analysisData = locationCounts.entrySet()
                    .stream()
                    .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                    .map(entry -> entry.getKey() + ": " + df.format(entry.getValue() * 1.0 / totalRowsFinal))
                    .collect(Collectors.toList());

            KnowledgeBase.loadFromAnalysis(analysisData);

            for (String result : analysisData) {
                System.out.println(result);
            }

            System.out.println("Esses foram os locais que mais abriram chamados, talvez um investimento em treinamentos ou materiais adicionais para esses locais diminua suas aberturas de chamados!");
        }
    }
}
