package org.example;

public class Entity {

    // 'protected' gör att variabeln är tillgänglig för denna klass samt dess underklasser
    protected int id;

    // 'private' innebär att denna variabel endast är tillgänglig inom denna klass
    private String name;

    // Konstruktor för Entity-klassen. Lämnar den tom eftersom ID:t hanteras av underklasser
    public Entity() {
    }

    // Getter-metod för id. Tillåter andra klasser att få id-värdet
    public int getId() {
        return id;
    }

    // Getter-metod för name. Tillåter andra klasser att få name-värdet
    public String getName() {
        return name;
    }

    // Metod som returnerar en beskrivning av entiteten
    // Överlagrar denna metod i underklasser för att ge mer specifik information
    public String getDescription() {
        return "Entity ID: " + id + ", Name: " + name;
    }
}