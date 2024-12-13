package komponen;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;


import java.io.FileReader;
import java.io.FileWriter;

public class Dfilm {

    public static String cariFilm(String judul) {
        int id = 1; 
        for(Film x: menus.DaftarFilm){
            if(x.getJudul().equals(judul)) return id+""; 
            id++; 
        }
        return id+""; 
    }
    

    public static void print() {
        try {
            int fileCount = 1;
            String filePath = "Films/film_" + fileCount + ".txt"; // Initial file path

            while (true) {
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                String line;
                String[] temp = new String[6]; // Array to hold film information
                int index = 0; // Index to track array position

                while ((line = reader.readLine()) != null) {
                    if (line.isBlank()) continue;
                    temp[index] = line;

                    if (index == 5) {
                        // Process the film information in the temp array
                        try {
                            menus.DaftarFilm.add(new Film(temp[0], Film.genreFilm.valueOf(temp[1]), temp[2], temp[3], temp[4], temp[5]));
                        } catch (TahunTooOldException e) {
                            System.out.println(e.getMessage());
                        }
                        index = 0;
                        temp = new String[6];
                    } else {
                        index++;
                    }
                }

                reader.close();

                fileCount++;
                filePath = "Films/film_" + fileCount + ".txt"; // Update file path for the next file
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    public static void write() {
        try {
            int fileCount = 1;
            for (Film film : menus.DaftarFilm) {
                String filePath = "Films/film_" + fileCount + ".txt";
    
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
    
                writer.write(film.getJudul());
                writer.newLine();
                writer.write(film.getGenre());
                writer.newLine();
                writer.write(film.getTahun());
                writer.newLine();
                writer.write(film.getKategori());
                writer.newLine();
                writer.write(film.getSinopsis());
                writer.newLine();
                writer.write(film.getBatasUmur());
                writer.newLine();
    
                writer.close();
    
                fileCount++;
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

}
