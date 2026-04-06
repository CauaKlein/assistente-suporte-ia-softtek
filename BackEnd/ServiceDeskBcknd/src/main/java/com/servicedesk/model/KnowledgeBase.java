package com.servicedesk.model;

import java.util.ArrayList;
import java.util.List;

public class KnowledgeBase {
    private int id;
    private String description;

    // Lista estática para armazenar na memória itens da base de dados
    private static List<KnowledgeBase> knowledgeBaseList = new ArrayList<>();

    // Construtor para inicializar os atributos da classe
    public KnowledgeBase(int id, String description) {
        this.setId(id);
        this.setDescription(description);
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Método estático para adicionar um item à base de dados
    public static void addKnowledgeItem(KnowledgeBase item) {
        knowledgeBaseList.add(item);
    }

    // Método estático para carregar dados a partir da análise realizada pelo AnalysisService
    public static void loadFromAnalysis(List<String> analysisData) {
        int idCounter = 1; // Iniciando o contador de ID
        for (String data : analysisData) {
            KnowledgeBase item = new KnowledgeBase(idCounter++, data);
            addKnowledgeItem(item);
        }
    }

    // Método para retornar todos os itens da base de dados
    public static List<KnowledgeBase> getAllKnowledgeItems() {
        List<KnowledgeBase> items = new ArrayList<>();
        for (KnowledgeBase item : knowledgeBaseList) {
            items.add(new KnowledgeBase(item.getId(), item.getDescription()));
        }
        return items;
    }
}
