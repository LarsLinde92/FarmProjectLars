package org.example;

public class Crop extends Entity {

    // Variabler för Crop-klassens egenskaper
    private String cropType;
    private int quantity;

    // Statisk variabel för att hålla reda på nästa ID för varje nytt Crop-objekt
    private static int nextId = 1;

    // Konstruktor som konfigurerar en Crop med en specifik typ och kvantitet
    public Crop(String cropType, int quantity) {
        this.id = nextId++; // Tilldela nästa tillgängliga ID och öka nextId
        this.cropType = cropType; // Tilldelar värdet av den lokala variabeln 'cropType' till klassens instansvariabel 'cropType'
        this.quantity = quantity; // Tilldelar värdet av den lokala variabeln 'quantity' till klassens instansvariabel 'quantity'
    }

    // Getter-metod för quantity
    // Används för att hämta kvantiteten av crops
    public int getQuantity() {
        return quantity;
    }

    // Setter-metod för quantity
    // Används för att sätta eller uppdatera kvantiteten av crops
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Getter-metod för 'cropType'
    // Används för att hämta denna typ av crops
    public String getCropType() {
        return cropType;
    }

    // Överskriver getDescription-metoden från Entity-klassen
    // Returnerar en sträng som beskriver Crop-objektet, inklusive dess ID, typ och kvantitet
    @Override
    public String getDescription() {
        return "Crop ID: " + getId() + ", Crop Type: " + cropType + ", Quantity: " + quantity;
    }

    // Eventuella andra metoder eller getters/setters...
}