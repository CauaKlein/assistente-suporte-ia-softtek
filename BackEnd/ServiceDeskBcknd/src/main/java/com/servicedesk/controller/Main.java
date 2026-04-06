package com.servicedesk.controller;

import com.servicedesk.service.AnalysisService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AnalysisService service = new AnalysisService();
        
        while (true) {
            System.out.println("Bem-vindo à Bel!");
            System.out.println("Selecione o tipo de análise que deseja:");
            System.out.println("(1) Análise de alta prioridade");
            System.out.println("(2) Análise Colunas no relatório");
            System.out.println("(3) Análise de categorias");
            System.out.println("(4) Análise de locais");
            System.out.println("(0) Sair");
            System.out.print("Digite o número da opção desejada: ");
            int option = scanner.nextInt();

            try {
                switch (option) {
                    case 1:
                        System.out.println("Iniciando análise de alta prioridade...");
                        service.printHighPriorityCalls();
                        break;
                    case 2:
                        System.out.println("Iniciando, será printando todas as colunas do relatório...");
                        service.printFirstRow();
                        break;
                    case 3:
                        System.out.println("Iniciando análise de categorias...");
                        service.analyzeCategories();
                        break;
                    case 4:
                        System.out.println("Iniciando análise de locais...");
                        service.analyzeLocations();
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
