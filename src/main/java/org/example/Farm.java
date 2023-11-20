package org.example;

import java.io.*;
import java.util.*;
import java.util.Scanner;

public class Farm {
    // Privata listor för att lagra Animal och crops på gården
    private List<Animal> animals;
    private List<Crop> crops;

    // Konstruktor för Farm-klassen
    public Farm() {
        // Initialiserar listorna för djur och grödor
        animals = new ArrayList<>();
        crops = new ArrayList<>();

        // Skapar ett File-objekt som representerar en mapp med namnet dataOnFarm
        File dataFolder = new File("dataOnFarm");
        // Kontrollerar om mappen finns, om inte, skapar den
        if (!dataFolder.exists()) {
            dataFolder.mkdir(); // Skapar mappen "dataOnFarm"
        }

        // Försöker ladda djur från en CSV-fil
        if (!loadAnimalsFromCSV()) {
            // Om ingen data laddas, lägger vi till standard-animal
            animals.add(new Animal("Cow")); // Lägger till en ko
            animals.add(new Animal("Sheep")); // Lägger till ett får
            System.out.println("Animals added successfully."); // Skriver ut ett meddelande om att djuren har lagts till
        }

        // Försöker ladda grödor från en CSV-fil
        if (!loadCropsFromCSV()) {
            // Om ingen data laddas, lägger till standard-crops
            crops.add(new Crop("Wheat", 10)); // Lägger till vete
            crops.add(new Crop("Carrot", 15)); // Lägger till morötter
            System.out.println("Crops added successfully."); // Skriver ut ett meddelande om att grödorna har lagts till
        }
    }

    // Metod för att visa huvudmenyn och hantera användarens val
    public void mainMenu() {
        // Skapar en Scanner för att läsa användarinput
        Scanner scanner = new Scanner(System.in);

        // En oändlig loop som gör att menyn upprepas tills programmet avslutas
        while (true) {
            // Skriver ut välkomstmeddelande och menyalternativ
            System.out.println(" ");
            System.out.println("<<<<>>>> Welcome to the Farm! <<<<>>>>");
            System.out.println(" ");
            System.out.println("            1. View Crops");
            System.out.println("            2. Add Crop");
            System.out.println("            3. Remove Crop");
            System.out.println(" ");
            System.out.println("            4. View Animals");
            System.out.println("            5. Add Animal");
            System.out.println("            6. Feed Animal");
            System.out.println("            7. Remove Animal");
            System.out.println(" ");
            System.out.println("            8. Save");
            System.out.println("            9. Exit");
            System.out.println("---------------------------------------");

            // Ber användaren att göra ett val baserat på menyalternativen
            int choice = getIntInput(scanner);

            // En switch-sats för att hantera användarens val
            switch (choice) {
                case 1:
                    ViewCrops(); // Visar crops
                    break;
                case 2:
                    AddCrop(); // Lägger till en crop
                    break;
                case 3:
                    RemoveCrop(); // Tar bort en crop
                    break;
                case 4:
                    ViewAnimals(); // Visar animals
                    break;
                case 5:
                    AddAnimal(); // Lägger till ett animal
                    break;
                case 6:
                    FeedAnimal(); // Matar ett animal
                    break;
                case 7:
                    RemoveAnimal(); // Tar bort ett animal
                    break;
                case 8:
                    Save(); // Sparar nuvarande tillstånd
                    break;
                case 9:
                    System.exit(0); // Avslutar programmet
                    break;
                default:
                    // Hanterar ogiltiga val
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }
    // Metod för att läsa in ett heltal från användaren.
    private int getIntInput(Scanner scanner) {
        // En oändlig loop som körs tills ett giltigt heltal matas in.
        while (true) {
            try {
                // Försöker läsa in nästa heltal från användaren.
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                // Fångar undantaget om inmatningen inte är ett giltigt heltal.
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Rensar bufferten för att undvika en oändlig loop.
            }
        }
    }

    // Variabel för att lagra typen av ny crop.
    private String newCropType;

    // Metod för att visa befintliga crops.
    private void ViewCrops() {
        for (Crop crop : crops) { // går igenom alla crops i listan
            System.out.println(crop.getDescription());// Skriver ut beskrivningen av varje crop.
        }
    }

    // Metod för att ta bort en crop.
    private void RemoveCrop() {
        Scanner scanner = new Scanner(System.in); // Skapar en Scanner för att läsa inmatning från användaren.
        System.out.print("Enter Crop ID to remove: "); // Ber användaren mata in ett ID för den crop som ska ta bort.
        int id = getIntInput(scanner); // Läser in ID:t med getIntInput-metoden.
        crops.removeIf(crop -> crop.getId() == id); // Tar bort crop med det angivna ID:t från listan crops.
        System.out.println("Crop removed successfully."); // Meddelar att grödan tagits bort framgångsrikt.
    }

    // Metod för att lägga till en ny gröda.
    private void AddCrop() {
        Scanner scanner = new Scanner(System.in); // Skapar en ny Scanner för att läsa inmatningen från användaren via standardinput (System.in).
        System.out.print("Enter Crop Type: "); // Skriver ut en instruktion till användaren att mata in typ av crop.
        newCropType = scanner.next(); // Sparar användarens inmatning i instansvariabeln 'newCropType'
        System.out.print("Enter Crop Quantity: "); // Skriver ut en instruktion till användaren att mata in mängden av crop.
        int quantity = getIntInput(scanner); // Använder getIntInput-metoden för att läsa in en int från användaren,mängden av crops.
        crops.add(new Crop(newCropType, quantity));  // Dynamisk ID-generering
        System.out.println("Crop added successfully."); // Skriver ut ett meddelande för att bekräfta att crop har lagts till framgångsrikt.
    }
    // Deklarerar en variabel för att lagra den nya animal-species
    private String newAnimalSpecies;

    // Metod för att visa alla animals på gården.
    private void ViewAnimals() { // Itererar över listan av animal.
        for (Animal animal : animals) { // Skriver ut beskrivningen av varje animal.
            System.out.println(animal.getDescription()); // Skriver ut beskrivningen av varje animal.
        }
    }

    // Metod för att ta bort ett animal från gården
    private void RemoveAnimal() {
        Scanner scanner = new Scanner(System.in); // Skapar en Scanner för inmatning
        System.out.print("Enter Animal ID to remove: "); // Ber användaren mata in ett animal-ID
        int id = getIntInput(scanner); // Läser in ID:t från användaren

        // Tar bort animal med det angivna ID:t från listan av animals
        animals.removeIf(animal -> animal.getId() == id);
        System.out.println("Animal removed successfully."); // Bekräftar borttagningen
    }

    // Metod för att lägga till ett nytt animal på gården
    private void AddAnimal() {
        Scanner scanner = new Scanner(System.in); // Skapar en Scanner för inmatning
        System.out.print("Enter Animal Species: "); // Ber användaren mata in animal species
        newAnimalSpecies = scanner.next(); // Läser in djurarten från användaren
        animals.add(new Animal(newAnimalSpecies)); // Lägger till det nya djuret i listan
        System.out.println("Animal added successfully."); // Bekräftar tillägget
    }

    // Metod för att mata ett djur på gården
    private void FeedAnimal() {
        Scanner scanner = new Scanner(System.in); // Skapar en Scanner för inmatning
        System.out.print("Enter Crop ID to feed: "); // Ber användaren mata in ett gröd-ID
        int cropId = getIntInput(scanner); // Läser in gröd-ID:t från användaren

        // Letar efter den valda crop baserat på ID
        Crop selectedCrop = null;
        for (Crop crop : crops) {
            if (crop.getId() == cropId) {
                selectedCrop = crop;
                break;
            }
        }

        // Kontrollerar om crop finns och hanterar om den inte finns
        if (selectedCrop == null) {
            System.out.println("Crop with ID " + cropId + " not found.");
            return;
        }

        System.out.print("Enter Animal ID to feed: "); // Ber användaren mata in ett animal-ID
        int animalId = getIntInput(scanner); // Läser in djur-ID:t från användaren

        // Letar efter det valda animal baserat på ID
        Animal selectedAnimal = null;
        for (Animal animal : animals) {
            if (animal.getId() == animalId) {
                selectedAnimal = animal;
                break;
            }
        }

        // Kontrollerar om animal finns och hanterar om det inte finns
        if (selectedAnimal == null) {
            System.out.println("Animal with ID " + animalId + " not found.");
            return;
        }

        // Utför matningen om det finns tillräckligt med crop
        if (selectedCrop.getQuantity() > 0) {
            selectedAnimal.Feed(selectedCrop); // Matning av animal
            selectedCrop.setQuantity(selectedCrop.getQuantity() - 1); // Minskar mängden av crop
            System.out.println("Animal fed successfully."); // Bekräftar framgångsrik matning
        } else {
            // Om det inte finns tillräckligt med crop
            System.out.println("No more of this crop to feed to the animal.");
        }
    }

    // Metod för att spara animals och crops till textfiler.
    private void Save() {
        // Sparar animal-information i en textfil "animals.txt".
        saveAnimalsToTextFile("dataOnFarm/animals.txt");

        // Sparar crop-information i en textfil "crops.txt".
        saveCropsToTextFile("dataOnFarm/crops.txt");
    }

    // Metod för att ladda animal-information från en CSV-fil.
    private boolean loadAnimalsFromCSV() {
        // Skapar en File-objekt för att representera sökvägen till CSV-filen "animals.txt".
        File file = new File("dataOnFarm/animals.txt");

        // Kontrollerar om filen inte finns eller om filens storlek är noll (tom).
        if (!file.exists() || file.length() == 0) {
            // Om något av dessa villkor är uppfyllda, returnera 'false'
            // för att indikera att ingen laddning av djurdata har utförts.
            return false;
        }

        // Öppna en BufferedReader för att läsa från filen.
        // Läs varje rad från filen.
        // Kontrollera om raden är tom och hoppa över den i så fall.
        // Dela upp raden i data med kommatecken som avgränsare och kontrollera om datan är korrekt formaterad.
        // Extrahera ID och namn från datan.
        // Skapa ett nytt Animal-objekt baserat på arten (species) och lägg till det i listan animals.
        // Returnera true om laddningen av djurdata lyckades.
        // Hantera eventuella undantag (fel) som kan uppstå vid läsning från filen och visa ett felmeddelande om det behövs. Returnera false om något går fel.
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] data = line.split(",");
                if (data.length < 2) {
                    continue;
                }
                int id = Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                String species = data[1].trim();
                animals.add(new Animal(species));
            }
            return true;
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading Animals data from file: " + e.getMessage());
            return false;
        }
    }

    // Metod för att ladda crops-information från en CSV-fil.
    private boolean loadCropsFromCSV() {
        File file = new File("dataOnFarm/crops.txt"); // Skapar ett File-objekt för att representera sökvägen till CSV-filen "crops.txt".
        if (!file.exists() || file.length() == 0) { // Kontrollerar om filen inte finns eller om filens storlek är noll (tom).
            return false;  // Om något av dessa villkor är uppfyllda, returnera 'false'
                           // för att indikera att ingen laddning av crops-data har utförts.
        }

        // Öppna en BufferedReader för att läsa från filen.
        // Läs varje rad från filen.
        // Kontrollera om raden är tom och hoppa över den i så fall.
        // Dela upp raden i data med kommatecken som avgränsare och kontrollera om datan är korrekt formaterad.
        // Extrahera ID, typ och mängd från datan.
        // Skapa ett nytt Crop-objekt baserat på typen och mängden och lägg till det i listan crops.
        // Returnera true om laddningen av crops-data var framgångsrik.
        // Hantera eventuella undantag (fel) som kan uppstå vid läsning från filen och visa ett felmeddelande. Returnera false om något går fel.
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; // Skip empty lines
                }
                String[] data = line.split(",");
                if (data.length < 3) {
                    continue; // Skip improperly formatted lines
                }
                int id = Integer.parseInt(data[0].trim());
                String type = data[1].trim();
                int quantity = Integer.parseInt(data[2].trim());
                crops.add(new Crop(type, quantity));
            }
            return true;
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading Crops data from file: " + e.getMessage());
            return false;
        }

    }

        // Öppnar en BufferedWriter för att skriva till filen med det angivna filnamnet.
        // Itererar över djur i listan 'animals'.
        // Skriver varje animals ID och art till filen med ett kommatecken som avgränsare och en ny rad (\n) för varje animal.
        // Visar ett meddelande om att animal-informationen har sparats framgångsrikt om allt går bra.
        // Hanterar eventuella fel som kan uppstå vid sparande och visar ett felmeddelande om något går fel.
    public void saveAnimalsToTextFile(String filename) {
        try (BufferedWriter animalWriter = new BufferedWriter(new FileWriter(filename))) {
            for (Animal animal : animals) {
                animalWriter.write(animal.getId() + "," + animal.getSpecies() + "\n");
            }
            System.out.println("Animals saved to text file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save Animals to text file.");
        }
    }

        // Öppnar en BufferedWriter för att skriva till filen med det angivna filnamnet.
        // Itererar över crop i listan 'crops'.
        // Skriver varje crops ID, typ och mängd till filen med kommatecken som avgränsare och en ny rad (\n) för varje crop.
        // Visar ett meddelande om att crop-informationen har sparats framgångsrikt om allt går bra.
        // Hanterar eventuella fel som kan uppstå vid sparande och visar ett felmeddelande om något går fel.
    public void saveCropsToTextFile(String filename) {
        try (BufferedWriter cropWriter = new BufferedWriter(new FileWriter(filename))) {
            for (Crop crop : crops) {
                cropWriter.write(crop.getId() + "," + crop.getCropType() + "," + crop.getQuantity() + "\n");
            }
            System.out.println("Crops saved to text file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save Crops to text file.");
        }
    }
}
