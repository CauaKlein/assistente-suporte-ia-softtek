package com.servicedesk.model;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Resolution {

    private int id;
    private String resolution;

    // Construtor
    public Resolution(int id, String resolution) {
        this.setId(id);
        this.setResolution(resolution);
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    // Método para buscar todas as soluções
    public static List<Resolution> getAllResolutions() {
        // Converte a lista de KnowledgeBase para uma lista de Resolution
        return KnowledgeBase.getAllKnowledgeItems()
                .stream()
                .map(item -> new Resolution(item.getId(), item.getDescription()))
                .collect(Collectors.toList());
    }

    // Método para buscar uma solução por ID utilizando getters
    public static Optional<Resolution> getResolutionById(int id) {
        // Busca o item correspondente no KnowledgeBase e converte para Resolution
        return KnowledgeBase.getAllKnowledgeItems()
                .stream()
                .filter(item -> item.getId() == id)
                .map(item -> new Resolution(item.getId(), item.getDescription()))
                .findFirst();
    }
}
