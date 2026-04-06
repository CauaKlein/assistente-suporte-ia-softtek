package com.servicedesk.test;

import com.servicedesk.model.KnowledgeBase;
import com.servicedesk.model.Resolution;
import com.servicedesk.service.AnalysisService;

public class TestMain {
    public static void main(String[] args) {
        // Instanciando o serviço de análise
        AnalysisService analysisService = new AnalysisService();

        // Teste da função de análise de alta prioridade
        System.out.println("Teste da função de análise de alta prioridade:");
        try {
            analysisService.printHighPriorityCalls();
        } catch (Exception e) {
            System.out.println("Erro ao realizar análise de alta prioridade: " + e.getMessage());
        }
        
        // Teste da função de impressão das duas primeiras linhas
        System.out.println("\nTeste da função de impressão das duas primeiras linhas:");
        try {
            analysisService.printFirstRow();
        } catch (Exception e) {
            System.out.println("Erro ao imprimir as duas primeiras linhas: " + e.getMessage());
        }
        
        // Teste da função de análise de categorias
        System.out.println("\nTeste da função de análise de categorias:");
        try {
            analysisService.analyzeCategories();
        } catch (Exception e) {
            System.out.println("Erro ao realizar análise de categorias: " + e.getMessage());
        }
        
        // Teste da função de análise de locais
        System.out.println("\nTeste da função de análise de locais:");
        try {
            analysisService.analyzeLocations();
        } catch (Exception e) {
            System.out.println("Erro ao realizar análise de locais: " + e.getMessage());
        }
        
        // Teste dos métodos das classes KnowledgeBase e Resolution
        System.out.println("\nTeste dos métodos das classes KnowledgeBase e Resolution:");
        
        // Adicionando itens à base de conhecimento
        KnowledgeBase.addKnowledgeItem(new KnowledgeBase(1, "Descrição do Item 1"));
        KnowledgeBase.addKnowledgeItem(new KnowledgeBase(2, "Descrição do Item 2"));
        
        // Exibindo todos os itens da base de conhecimento
        System.out.println("Itens na base de conhecimento:");
        KnowledgeBase.getAllKnowledgeItems().forEach(item ->
            System.out.println("ID: " + item.getId() + ", Descrição: " + item.getDescription())
        );
        
        // Adicionando resoluções
        Resolution.getAllResolutions().forEach(resolution ->
            System.out.println("ID: " + resolution.getId() + ", Resolução: " + resolution.getResolution())
        );
    }
}
