package org.example;

public class Animal extends Entity {

    // Statisk variabel för att hålla reda på nästa ID för varje nytt Animal-objekt
    private static int nextId = 1;

    // Variabel för att lagra species till Animal
    private String species;

    // Konstruktor som konfigurerar ett Animal-objekt med en specifik species
    public Animal(String species) {
        this.id = nextId++; // Tilldelar nästa tillgängliga ID och ökar med nextId
        this.species = species; // Ställer in species för detta animal
    }

    // Getter-metod för species
    // Används för att hämta species för detta animal
    public String getSpecies() {
        return species;
    }

    // Överskriver getDescription-metoden från Entity-klassen
    // Returnerar en sträng som beskriver Animal-objektet, inklusive dess ID och species
    @Override
    public String getDescription() {
        return "Animal ID: " + getId() + ", Species: " + species;
    }

    // Metod för att mata djuret med en viss crop
    // Skriver ut information om vilket animal som matas och med vilken crop
    public void Feed(Crop crop) {
        System.out.println(this.getSpecies() + " with ID " + this.getId() + " has been fed with " + crop.getDescription());
    }
}