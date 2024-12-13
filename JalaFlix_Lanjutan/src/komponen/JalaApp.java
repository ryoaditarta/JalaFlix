package komponen;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import komponen.Film.genreFilm;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;


public class JalaApp extends JFrame{

    //data yang berhasil 
    public static String username; 
    public static String password; 
    public static int id; 
    
    //login register
    public static String logorreg;
    public static String berhasilMasuk; 
    
    //menutama
    public static String pilMenu; 

    // daftar kategori
    public static String pilKategori; 
    public static String konfirmasikat;

    // nonton film
    public static String pilNonton; 

    // displayfilm


    // logout
    public static String pillogout;

    // profil
    public static String pilupdate; 

    // history 
    public static String pilHapus;

    //film baru
    public static String sinopsisBaru;
    public static String judulBaru; 
    public static String genreBaru; 
    public static String umurBaru; 
    public static String kategoriBaru;  
    public static String tahunBaru; 

    public static void reset(){
        logorreg= null; 
        username = null; 
        password = null; 
    }

    public static void modifFile(String pengganti, int i){
        File folder = new File("Users");
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            for (File file : files) {
                if (file.isFile() && file.getName().equals(menus.getPelanggan(id).getNama()+".txt")) {
                    try {
                        // Membaca file menggunakan BufferedReader
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        String line;
                        StringBuilder content = new StringBuilder();

                        // Membaca baris-baris file dan menyimpannya dalam StringBuilder
                        int lineNumber = 1;
                        while ((line = reader.readLine()) != null) {
                            if (lineNumber == i) {
                                // Mengganti baris ke-4 dengan nilai baru
                                line = pengganti;
                            }
                            content.append(line).append(System.lineSeparator());
                            lineNumber++;
                        }

                        reader.close();

                        // Menulis kembali isi file dengan nilai yang sudah diganti
                        FileWriter writer = new FileWriter(file);
                        writer.write(content.toString());
                        writer.close();
                    } catch (IOException e) {
                        System.out.println("Terjadi kesalahan saat membaca atau menulis file: " + e.getMessage());
                    }
                }
            }
        } else {
            System.out.println("Folder path tidak valid");
        }
    }

    public String getLogOrReg(){
        return logorreg; 
    }
    
    public JalaApp(){
        JFrame frame = new JFrame("JalaFlix 1.0");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(login(frame));
        frame.setVisible(true); 
    }
    
    private static JPanel daftarKategori(JFrame frame) {
        JPanel contentPane = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
    
        if (menus.getPelanggan(id).getKategori().equals("User Biasa")) {
            JPanel pilih = new JPanel();
            pilih.setLayout(new BoxLayout(pilih, BoxLayout.Y_AXIS)); // Set layout manager with vertical orientation
    
            JLabel pilkat = new JLabel("Pilih Kategori!");
            pilkat.setFont(new Font("Arial", Font.BOLD, 20));
            pilih.add(pilkat, constraints);
    
            JButton regularButton = new JButton("Regular");
            JButton goldButton = new JButton("Gold");
            JButton platinumButton = new JButton("Platinum");
    
            pilih.add(regularButton);
            pilih.add(goldButton);
            pilih.add(platinumButton);
    
            constraints.gridy = 1;
            contentPane.add(pilih, constraints); // Add the pilih panel
    
            regularButton.addActionListener(e -> {
                int result = JOptionPane.showConfirmDialog(frame, "Purchase Regular? (15000)", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    JalaApp.pilKategori ="1"; 
                    JalaApp.konfirmasikat = "1"; 
                    JalaApp.pilMenu = null; 

                    String temp = menus.getPelanggan(id).getKodePelanggan();
                    temp = "REG-"+temp.substring(temp.indexOf('-') + 1);
                    System.out.println(temp.substring(temp.indexOf('-')+1));
                    modifFile(temp, 1);
                    modifFile("Regular", 4); 

                    Pelanggan pel = menus.getPelanggan(id); 

                    menus.getPelanggan(id).setKodePelanggan(temp);
                    menus.getPelanggan(id).setNama(pel.getNama());
                    menus.getPelanggan(id).setKategori("Regular");
                    menus.getPelanggan(id).setNoTelp(pel.getNoTelp());
                    menus.getPelanggan(id).setPassword(pel.getUmur());
                    menus.getPelanggan(id).setStatus(pel.getStatus());

                    frame.setContentPane(menuUtama(frame));
                    frame.setVisible(true);
                }else{
                    JalaApp.konfirmasikat = "0";
                    frame.setContentPane(daftarKategori(frame));
                    frame.setVisible(true);
                }
                
            });
            goldButton.addActionListener(e -> {
                int result = JOptionPane.showConfirmDialog(frame, "Purchase Gold? (50000)", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    JalaApp.pilKategori = "2";
                    JalaApp.konfirmasikat = "1"; 
                    JalaApp.pilMenu = null; 
                    

                    String temp = menus.getPelanggan(id).getKodePelanggan();
                    temp = "GOLD-"+temp.substring(temp.indexOf('-') + 1);
                    modifFile(temp, 1);
                    modifFile("Gold", 4); 

                    Pelanggan pel = menus.getPelanggan(id); 

                    menus.getPelanggan(id).setKodePelanggan(temp);
                    menus.getPelanggan(id).setNama(pel.getNama());
                    menus.getPelanggan(id).setKategori("Gold");
                    menus.getPelanggan(id).setNoTelp(pel.getNoTelp());
                    menus.getPelanggan(id).setPassword(pel.getUmur());
                    menus.getPelanggan(id).setStatus(pel.getStatus());


                    frame.setContentPane(menuUtama(frame));
                    frame.setVisible(true);
                }else{
                    JalaApp.konfirmasikat = "0";
                    frame.setContentPane(daftarKategori(frame));
                    frame.setVisible(true);
                }
                JalaApp.pilMenu = null; 
            });
            platinumButton.addActionListener(e -> {
                int result = JOptionPane.showConfirmDialog(frame, "Purchase Platinum? (100000)", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    JalaApp.pilKategori = "3";
                    JalaApp.konfirmasikat = "1"; 
                    
                    String temp = menus.getPelanggan(id).getKodePelanggan();
                    temp = "PLT-"+temp.substring(temp.indexOf('-') + 1);
                    modifFile(temp, 1);
                    modifFile("Platinum", 4); 

                    Pelanggan pel = menus.getPelanggan(id); 

                    menus.getPelanggan(id).setKodePelanggan(temp);
                    menus.getPelanggan(id).setNama(pel.getNama());
                    menus.getPelanggan(id).setKategori("Platinum");
                    menus.getPelanggan(id).setNoTelp(pel.getNoTelp());
                    menus.getPelanggan(id).setPassword(pel.getUmur());
                    menus.getPelanggan(id).setStatus(pel.getStatus());

                    frame.setContentPane(menuUtama(frame));
                    frame.setVisible(true);
                }else{
                    JalaApp.konfirmasikat = "0";
                    frame.setContentPane(daftarKategori(frame));
                    frame.setVisible(true);
                }
                JalaApp.pilMenu = null; 
            });
        } else {
            JLabel sudah = new JLabel("Anda sudah terdaftar kategori "+ menus.getPelanggan(JalaApp.id).getKategori()+"!");
            sudah.setFont(new Font("Arial", Font.BOLD, 12));
            JButton ok = new JButton("OK");
            ok.addActionListener(e -> {
                frame.setContentPane(menuUtama(frame));
                frame.setVisible(true);
            });
    
            constraints.gridy = 1;
            contentPane.add(sudah, constraints);
            constraints.gridy = 2;
            contentPane.add(ok, constraints);
        }
    

        return contentPane; 
    }

    private static JPanel menuUtama(JFrame frame) {
        JPanel contentPane = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
    
        // Create buttons for each menu option
        JButton daftarKategoriButton = new JButton("Daftar Kategori");
        JButton nontonFilmButton = new JButton("Nonton Film");
        JButton gantiKategoriButton = new JButton("Mengganti Kategori");
        JButton lihatDaftarFilmButton = new JButton("Lihat Daftar Film");
        JButton logoutButton = new JButton("Logout");
        JButton profilAkunButton = new JButton("Profil Akun dan Lengkapi Profil");
        JButton historyButton = new JButton("History");
        JButton tambahFilmButton = new JButton("Menambahkan Film");
    
        // Add action listeners to the buttons
        daftarKategoriButton.addActionListener(e -> {
            frame.setContentPane(daftarKategori(frame));
            frame.setVisible(true);
            JalaApp.pilMenu = "1"; 
        });
        nontonFilmButton.addActionListener(e -> {
            if(menus.getPelanggan(id).getUmur().equals("-")){
                JOptionPane.showMessageDialog(null, "Umur belum diset!", "Age Information", JOptionPane.INFORMATION_MESSAGE);
            }else{
                frame.setContentPane(nontonFilm(frame));
                frame.setVisible(true);
                JalaApp.pilMenu = "2";
            }
        });
        gantiKategoriButton.addActionListener(e -> {
            frame.setContentPane(gantiKategori(frame));
            frame.setVisible(true);

            JalaApp.pilMenu = "3";
        });
        lihatDaftarFilmButton.addActionListener(e -> {
            frame.setContentPane(lihatDaftarFilm(frame));
            frame.setVisible(true);
            JalaApp.pilMenu = "4";
        });
        logoutButton.addActionListener(e -> {
            logout(frame);
            if(pillogout.equals("1")) frame.setContentPane(login(frame));
            frame.setVisible(true);
            JalaApp.pilMenu = "5";
        });
        profilAkunButton.addActionListener(e -> {
            frame.setContentPane(profil(frame));
            frame.setVisible(true); 
            JalaApp.pilMenu = "6"; 
        });
        historyButton.addActionListener(e -> {
            frame.setContentPane(histori(frame));
            frame.setVisible(true);
            JalaApp.pilMenu = "7"; 
        });
        tambahFilmButton.addActionListener(e -> {
            frame.setContentPane(nambahFilm(frame)); 
            frame.setVisible(true);
            JalaApp.pilMenu = "8"; 
        });

        JPanel jdul = new JPanel();
        JLabel judul = new JLabel("JalaFlix! Menu");
        judul.setFont(new Font("Arial", Font.BOLD, 50));
        jdul.add(judul);
    
        // Add buttons to the content pane
        contentPane.add(jdul, constraints);
        constraints.gridy = 1; 
        contentPane.add(daftarKategoriButton, constraints);
        constraints.gridy = 2;
        contentPane.add(nontonFilmButton, constraints);
        constraints.gridy = 3;
        contentPane.add(gantiKategoriButton, constraints);
        constraints.gridy = 4;
        contentPane.add(lihatDaftarFilmButton, constraints);
        constraints.gridy = 5;
        contentPane.add(logoutButton, constraints);
        constraints.gridy = 6;
        contentPane.add(profilAkunButton, constraints);
        constraints.gridy = 7;
        contentPane.add(historyButton, constraints);
        constraints.gridy = 8;


        if(menus.getPelanggan(id).getNama().equals("admin") && menus.getPelanggan(id).getPassword().equals("user123")){
            contentPane.add(tambahFilmButton, constraints);
        }
    
        // Set content pane of the frame
    
        // Set frame properties
        frame.setTitle("Menu Utama");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800, 600); 
        frame.setVisible(true);
        
        return contentPane; 

    }

    private static JPanel login(JFrame frame) {

        JPanel contentPane = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);

        // Panel untuk judul JalaFlix
        JPanel jdul = new JPanel();
        JLabel judul = new JLabel("JalaFlix!");
        judul.setFont(new Font("Arial", Font.BOLD, 50));
        jdul.add(judul);

        // Menambahkan panel judul ke contentPane
        contentPane.add(jdul, constraints);

        // username
        constraints.gridy = 1;
        JLabel usernameLabel = new JLabel("Username:");
        contentPane.add(usernameLabel, constraints);

        constraints.gridy = 2;
        JTextField usernameField = new JTextField(20);
        contentPane.add(usernameField, constraints);

        // password
        constraints.gridy = 3;
        JLabel passwordLabel = new JLabel("Password:");
        contentPane.add(passwordLabel, constraints);
        constraints.gridy = 4;
        JPasswordField passwordField = new JPasswordField(20);
        contentPane.add(passwordField, constraints);

        // tombol relog dan register
        JPanel reglog = new JPanel(); 
        constraints.gridy = 5;  
        JButton loginButton = new JButton("Login");
        reglog.add(loginButton);
        JButton registerButton = new JButton("Register");
        reglog.add(registerButton); 

        contentPane.add(reglog, constraints); 

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);
                JalaApp.logorreg = "1"; 
                JalaApp.password = password; 
                JalaApp.username = username; 
                while (berhasilMasuk == null) {
                    try {
                        Thread.sleep(100); // Wait for 100 milliseconds before checking again
                    } catch (InterruptedException em) {
                        em.printStackTrace();
                    }
                }
                if (berhasilMasuk.equals("true")) {
                    berhasilMasuk = null; 
                    
                    frame.setContentPane(menuUtama(frame));
                    frame.setVisible(true);
                } else {
                    berhasilMasuk = null; 
                    JOptionPane.showMessageDialog(frame, "Invalid username or password. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    frame.setContentPane(login(frame));  
                    frame.setVisible(true);
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);
                JalaApp.logorreg = "2";
                JalaApp.password = password; 
                JalaApp.username = username;
            }            
        });
        return contentPane; 
    }

    private static void logout(JFrame frame) {
        int choice = JOptionPane.showConfirmDialog(frame, "Are you sure you want to logout?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
        
        if (choice == JOptionPane.YES_OPTION) {
            pillogout = "1"; 
            logorreg = null; 
            berhasilMasuk = null; 
            pilMenu = null; 
            konfirmasikat = null; 
            pilNonton = null; 
            

        }else{
            pillogout = "2"; 
        }
    }

    private static JPanel profil(JFrame frame) {
        JPanel contentPane = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
    
        JLabel titleLabel = new JLabel("Data Akun:");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD));
        constraints.gridwidth = 2;
        contentPane.add(titleLabel, constraints);
    
        JLabel namaLabel = new JLabel("Nama:");
        constraints.gridy++;
        constraints.gridwidth = 1;
        contentPane.add(namaLabel, constraints);
    
        JLabel namaValue = new JLabel(menus.getPelanggan(id).getNama());
        constraints.gridx++;
        contentPane.add(namaValue, constraints);
    
        JLabel kodePelangganLabel = new JLabel("Kode Pelanggan:");
        constraints.gridx = 0;
        constraints.gridy++;
        contentPane.add(kodePelangganLabel, constraints);
    
        JLabel kodePelangganValue = new JLabel(menus.getPelanggan(id).getKodePelanggan());
        constraints.gridx++;
        contentPane.add(kodePelangganValue, constraints);
    
        JLabel umurLabel = new JLabel("Umur:");
        constraints.gridx = 0;
        constraints.gridy++;
        contentPane.add(umurLabel, constraints);
        
        JLabel umurValue = new JLabel(menus.getPelanggan(id).getUmur());
        constraints.gridx++;
        contentPane.add(umurValue, constraints);
    
        JLabel noTelpLabel = new JLabel("No. Telepon:");
        constraints.gridx = 0;
        constraints.gridy++;
        contentPane.add(noTelpLabel, constraints);
    
        JLabel noTelpValue = new JLabel(menus.getPelanggan(id).getNoTelp());
        constraints.gridx++;
        contentPane.add(noTelpValue, constraints);
    
        JLabel kategoriLabel = new JLabel("Kategori:");
        constraints.gridx = 0;
        constraints.gridy++;
        contentPane.add(kategoriLabel, constraints);
    
        JLabel kategoriValue = new JLabel(menus.getPelanggan(id).getKategori());
        constraints.gridx++;
        contentPane.add(kategoriValue, constraints);
    
        JLabel statusKeanggotaanLabel = new JLabel("Status Keanggotaan:");
        constraints.gridx = 0;
        constraints.gridy++;
        contentPane.add(statusKeanggotaanLabel, constraints);
    
        JLabel statusKeanggotaanValue = new JLabel(menus.getPelanggan(id).getStatus());
        constraints.gridx++;
        contentPane.add(statusKeanggotaanValue, constraints);
    
        // Panel untuk tombol Kembali dan Update
        JPanel buttonPanel = new JPanel();
        JButton kembaliButton = new JButton("Kembali");
        JButton updateButton = new JButton("Update");
        constraints.gridy++;
        buttonPanel.add(kembaliButton, constraints);
        constraints.gridx++;
        buttonPanel.add(updateButton);
    
        // Konfigurasi constraints untuk panel tombol
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        contentPane.add(buttonPanel, constraints);
    
        kembaliButton.addActionListener(e -> {
            pilupdate = "2"; 
            frame.setContentPane(menuUtama(frame));
            frame.setVisible(true);
        });
        
        
        updateButton.addActionListener(e -> {
            String newUmur = JOptionPane.showInputDialog(frame, "Input new age:");
            String newNoTelp = JOptionPane.showInputDialog(frame, "Input new phone number:");
            if (newUmur != null && newNoTelp != null ) {
                try{
                    Integer.parseInt(newUmur); 
                    if(Integer.parseInt(newUmur)>=0){
                        modifFile(newUmur, 8);
                        modifFile(newNoTelp, 3);
                        
                        menus.getPelanggan(id).setUmur(newUmur);
                        menus.getPelanggan(id).setNoTelp(newNoTelp);
                        
                        // Update the labels with new values
                        umurValue.setText(newUmur);
                        noTelpValue.setText(newNoTelp);
                    }
                    frame.setContentPane(menuUtama(frame));
                    frame.setVisible(true);
                }catch(Exception t){
                    umurValue.setText("-");
                }                
            }else{
                frame.setContentPane(menuUtama(frame));
                frame.setVisible(true);
            }

            pilupdate = "1"; 

        });
    
        return contentPane;
    }

    private static JScrollPane lihatDaftarFilm(JFrame frame) {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JPanel header = new JPanel(new GridLayout(0, 1)); 
        JButton kembali = new JButton("kembali");
        JLabel atas = new JLabel("Daftar Film"); 
        header.add(kembali, 0); 
        header.add(atas, 1); 

        panel.add(header); 

        // Iterate over the films in the DaftarFilm data structure
        for (Film film : menus.DaftarFilm) {
            // Create a JPanel to display film information
            JPanel filmInfoPanel = new JPanel(new GridLayout(6, 1));
            filmInfoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            

            // Add labels with film information to the film info panel
            JLabel titleLabel = new JLabel(film.judul);
            JLabel genreLabel = new JLabel("Genre: " + film.genre);
            JLabel yearLabel = new JLabel("Tahun: " + film.tahun);
            JLabel categoryLabel = new JLabel("Kategori: " + film.kategori);
            JLabel ageLabel = new JLabel("Batas Umur: " + film.batasUmur);
            JLabel synopsisLabel = new JLabel("Sinopsis: " + film.sinopsis);

            // Set font size for film information labels
            Font labelFont = titleLabel.getFont();
            titleLabel.setFont(labelFont.deriveFont(Font.BOLD, 16f)); // Adjust the font size as needed
            genreLabel.setFont(labelFont.deriveFont(Font.PLAIN, 14f)); // Adjust the font size as needed
            yearLabel.setFont(labelFont.deriveFont(Font.PLAIN, 14f)); // Adjust the font size as needed
            categoryLabel.setFont(labelFont.deriveFont(Font.PLAIN, 14f)); // Adjust the font size as needed
            ageLabel.setFont(labelFont.deriveFont(Font.PLAIN, 14f)); // Adjust the font size as needed
            synopsisLabel.setFont(labelFont.deriveFont(Font.PLAIN, 14f)); // Adjust the font size as needed

            filmInfoPanel.add(titleLabel);
            filmInfoPanel.add(genreLabel);
            filmInfoPanel.add(yearLabel);
            filmInfoPanel.add(categoryLabel);
            filmInfoPanel.add(ageLabel);
            filmInfoPanel.add(synopsisLabel);

            // Add the film info panel to the main panel
            panel.add(filmInfoPanel);
        }

        // Create a JScrollPane to scroll through the film list if necessary
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        kembali.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                JalaApp.pilMenu = null;
                frame.setContentPane(menuUtama(frame));
            }            
        });

        return scrollPane;
    }
    
    private static JScrollPane nontonFilm(JFrame frame) {

        JPanel panel = new JPanel(new GridLayout(0, 1));
        JPanel header = new JPanel(new GridLayout(0, 1)); 
        JButton kembali = new JButton("kembali");
        JLabel atas = new JLabel("Daftar Film"); 
        header.add(kembali, 0); 
        header.add(atas, 1); 

        panel.add(header); 

        // Iterate over the films in the DaftarFilm data structure
        for (Film film : menus.DaftarFilm) {
            // Create a JPanel to display film information
            JPanel filmInfoPanel = new JPanel(new GridLayout(7, 1));
            filmInfoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            

            // Add labels with film information to the film info panel
            JLabel titleLabel = new JLabel(film.judul);
            JLabel genreLabel = new JLabel("Genre: " + film.genre);
            JLabel yearLabel = new JLabel("Tahun: " + film.tahun);
            JLabel categoryLabel = new JLabel("Kategori: " + film.kategori);
            JLabel ageLabel = new JLabel("Batas Umur: " + film.batasUmur);
            JLabel synopsisLabel = new JLabel("Sinopsis: " + film.sinopsis);

            // Set font size for film information labels
            Font labelFont = titleLabel.getFont();
            titleLabel.setFont(labelFont.deriveFont(Font.BOLD, 16f)); // Adjust the font size as needed
            genreLabel.setFont(labelFont.deriveFont(Font.PLAIN, 14f)); // Adjust the font size as needed
            yearLabel.setFont(labelFont.deriveFont(Font.PLAIN, 14f)); // Adjust the font size as needed
            categoryLabel.setFont(labelFont.deriveFont(Font.PLAIN, 14f)); // Adjust the font size as needed
            ageLabel.setFont(labelFont.deriveFont(Font.PLAIN, 14f)); // Adjust the font size as needed
            synopsisLabel.setFont(labelFont.deriveFont(Font.PLAIN, 14f)); // Adjust the font size as needed

            JButton nonton = new JButton("nonton"); 
            filmInfoPanel.add(titleLabel);
            filmInfoPanel.add(genreLabel);
            filmInfoPanel.add(yearLabel);
            filmInfoPanel.add(categoryLabel);
            filmInfoPanel.add(ageLabel);
            filmInfoPanel.add(synopsisLabel);
            filmInfoPanel.add(nonton);


            nonton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int userAge = Integer.parseInt(menus.getPelanggan(id).getUmur());
                    int filmAgeLimit = Integer.parseInt(film.getBatasUmur());
                    JalaApp.pilNonton = Dfilm.cariFilm(film.getJudul()); 
                    JFrame cinema = new JFrame();
                    if (userAge >= filmAgeLimit) {
                        boolean bsnonton = false; 
                        String cat = menus.getPelanggan(id).getKategori(); 
                        if((film.getKategori().equals("Regular"))&&(cat.equals("Regular")||cat.equals("Gold")||cat.equals("Platinum"))) bsnonton = true; 
                        else if((film.getKategori().equals("New"))&&(cat.equals("Gold")||cat.equals("Platinum"))) bsnonton = true; 
                        else if(cat.equals("Platinum")) bsnonton = true; 
                            if(bsnonton){
                                GridBagConstraints constraints = new GridBagConstraints();
                                constraints.gridx = 0;
                                constraints.gridy = 0;
                                constraints.insets = new Insets(10, 10, 10, 10);
                                JPanel content = new JPanel(new GridBagLayout());
                                
                                JLabel watch = new JLabel("Watching Film");
                                JLabel judul = new JLabel(film.getJudul());
                                
                                constraints.fill = GridBagConstraints.BOTH;
                                constraints.gridwidth = GridBagConstraints.REMAINDER;
                                constraints.anchor = GridBagConstraints.CENTER;
                                content.add(watch, constraints);
                                
                                constraints.gridy++;
                                content.add(judul, constraints);
                                
                                cinema.setContentPane(content);
                                cinema.setSize(800, 600);
                                cinema.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                cinema.setLocationRelativeTo(null);
                                cinema.setVisible(true);
                                
                            }else{
                                JOptionPane.showMessageDialog(frame, "Upgrade untuk menonton film ini!", "Upgrade!", JOptionPane.ERROR_MESSAGE);
                            }
                    } else {
                        JOptionPane.showMessageDialog(frame, "You are not old enough to watch this film.", "Age Restriction", JOptionPane.ERROR_MESSAGE);
                    }
                    frame.setContentPane(menuUtama(frame));
                    frame.setVisible(true);
                    cinema.setVisible(true);
                    cinema.toFront();
                }
            });
            // Add the film info panel to the main panel
            panel.add(filmInfoPanel);

        }

        // Create a JScrollPane to scroll through the film list if necessary
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        kembali.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                JalaApp.pilNonton = "-1"; 
                JalaApp.pilMenu = null;
                frame.setContentPane(menuUtama(frame));
            }            
        });

        return scrollPane;
    }

    private static JPanel histori(JFrame frame) {
        JPanel contentPane = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
    
        ArrayList<String> tempHistori = new ArrayList<String>();
        if(menus.getPelanggan(id).dapatHistori()==null) tempHistori = menus.getPelanggan(id).dapatHistori(); 
        else{
            for(String s: menus.getPelanggan(id).dapatHistori()){
                if(!s.isBlank()) tempHistori.add(s); 
            }
        }
        JLabel historiS = new JLabel("History Pengguna!");
        historiS.setFont(new Font("Arial", Font.BOLD, 20)); 

        contentPane.add(historiS, constraints); 

        if(tempHistori==null||tempHistori.isEmpty()){
            JLabel tdkada = new JLabel("Tidak ada histori!");
            constraints.gridy = 1;
            contentPane.add(tdkada, constraints); 
            JButton ok = new JButton("OK"); 
            constraints.gridy = 2;
            contentPane.add(ok, constraints); 

            ok.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    frame.setContentPane(menuUtama(frame)); 
                    frame.setVisible(true);
                }            
            });

            return contentPane;  
        }
    
        // Iterate over the film history and create JLabels and buttons
        for (int i = 1; i <=Math.min(tempHistori.size(), 10); i++) {
            String film = tempHistori.get(i-1);
            // Create a JLabel for the film
            constraints.gridx = 0; 
            JLabel filmLabel = new JLabel(film);
            constraints.gridy = i;
            contentPane.add(filmLabel, constraints);

            JButton button = new JButton("Hapus");
            constraints.gridx = 1; 
            contentPane.add(button, constraints);
    
            int filmIndex = i-1; 

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    int result = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        menus.getPelanggan(id).hapusFilmHistori(filmIndex);
                        String s = ""; 
                        ArrayList<String> ttemp = menus.getPelanggan(id).dapatHistori(); 
                        for(int i=0; i<ttemp.size(); i++){
                            s+=ttemp.get(i) + "#"; 
                        }
                        JalaApp.modifFile(s, 7);
                        JalaApp.pilHapus = Integer.toString(filmIndex); 
                    } else if (result == JOptionPane.NO_OPTION) {
                        JalaApp.pilHapus = "0"; 
                    }
                    frame.setContentPane(menuUtama(frame));
                    frame.setVisible(true);
                }            
            });
            
        }

        constraints.gridy++; 
        JButton kembali = new JButton("kembali"); 
        contentPane.add(kembali, constraints); 

        kembali.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                JalaApp.pilHapus = "0"; 
                frame.setContentPane(menuUtama(frame));
                frame.setVisible(true);
            }            
        });

        
    
        return contentPane;
    }
    
    private static JPanel gantiKategori(JFrame frame){
        JPanel contentPane = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
    
        JPanel pilih = new JPanel();
        pilih.setLayout(new BoxLayout(pilih, BoxLayout.Y_AXIS)); // Set layout manager with vertical orientation

        JLabel pilkat = new JLabel("Pilih Kategori!");
        pilkat.setFont(new Font("Arial", Font.BOLD, 20));
        pilih.add(pilkat, constraints);

        JButton regularButton = new JButton("Regular");
        JButton goldButton = new JButton("Gold");
        JButton platinumButton = new JButton("Platinum");
        JButton unsubs = new JButton("Unsubscribe");

        pilih.add(regularButton);
        pilih.add(goldButton);
        pilih.add(platinumButton);
        pilih.add(unsubs);

        constraints.gridy = 1;
        contentPane.add(pilih, constraints); // Add the pilih panel

        regularButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(frame, "Ganti Regular? (Rp.10.000)", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                JalaApp.pilKategori ="1"; 
                JalaApp.konfirmasikat = "1"; 
                JalaApp.pilMenu = null; 

                String temp = menus.getPelanggan(id).getKodePelanggan();
                temp = "REG-"+temp.substring(temp.indexOf('-')+1);
                modifFile(temp, 1);
                modifFile("Regular", 4); 

                Pelanggan pel = menus.getPelanggan(id); 

                menus.getPelanggan(id).setKodePelanggan(temp);
                menus.getPelanggan(id).setNama(pel.getNama());
                menus.getPelanggan(id).setKategori("Regular");
                menus.getPelanggan(id).setNoTelp(pel.getNoTelp());
                menus.getPelanggan(id).setPassword(pel.getPassword());
                menus.getPelanggan(id).setUmur(pel.getUmur());
                menus.getPelanggan(id).setHistori(pel.dapatHistori());
                menus.getPelanggan(id).setStatus(pel.getStatus());

                frame.setContentPane(menuUtama(frame));
                frame.setVisible(true);
            }else{
                JalaApp.pilKategori = "0"; 
                frame.setContentPane(menuUtama(frame));
                frame.setVisible(true);
            }
            
        });
        goldButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(frame, "Ganti Gold? (Rp.50000)", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                JalaApp.pilKategori = "2";
                JalaApp.konfirmasikat = "1"; 
                JalaApp.pilMenu = null; 

                String temp = menus.getPelanggan(id).getKodePelanggan();
                temp = "GOLD-"+temp.substring(temp.indexOf('-') + 1);
                modifFile(temp, 1);
                modifFile("Gold", 4); 

                Pelanggan pel = menus.getPelanggan(id); 

                menus.getPelanggan(id).setKodePelanggan(temp);
                menus.getPelanggan(id).setNama(pel.getNama());
                menus.getPelanggan(id).setKategori("Gold");
                menus.getPelanggan(id).setNoTelp(pel.getNoTelp());
                menus.getPelanggan(id).setPassword(pel.getPassword());
                menus.getPelanggan(id).setUmur(pel.getUmur());
                menus.getPelanggan(id).setHistori(pel.dapatHistori());
                menus.getPelanggan(id).setStatus(pel.getStatus());

                frame.setContentPane(menuUtama(frame));
                frame.setVisible(true);
            }else{
                JalaApp.pilKategori = "0"; 
                frame.setContentPane(menuUtama(frame));
                frame.setVisible(true);
            }
            JalaApp.pilMenu = null; 
        });
        platinumButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(frame, "Ganti Platinum? (Rp.75000)", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                JalaApp.pilKategori = "3";
                JalaApp.konfirmasikat = "1"; 

                String temp = menus.getPelanggan(id).getKodePelanggan();
                temp = "PLT-"+temp.substring(temp.indexOf('-') + 1);
                modifFile(temp, 1);
                modifFile("Platinum", 4); 

                Pelanggan pel = menus.getPelanggan(id); 

                menus.getPelanggan(id).setKodePelanggan(temp);
                menus.getPelanggan(id).setNama(pel.getNama());
                menus.getPelanggan(id).setKategori("Platinum");
                menus.getPelanggan(id).setNoTelp(pel.getNoTelp());
                menus.getPelanggan(id).setPassword(pel.getPassword());
                menus.getPelanggan(id).setUmur(pel.getUmur());
                menus.getPelanggan(id).setHistori(pel.dapatHistori());
                menus.getPelanggan(id).setStatus(pel.getStatus());

                frame.setContentPane(menuUtama(frame));
                frame.setVisible(true);
            }else{
                JalaApp.pilKategori = "0"; 
                frame.setContentPane(menuUtama(frame));
                frame.setVisible(true);
            }
            JalaApp.pilMenu = null; 
        });

        unsubs.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(frame, "Purchase Unsubscribe? (Rp.0)", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                JalaApp.pilKategori = "4";
                JalaApp.konfirmasikat = "1"; 

                String temp = menus.getPelanggan(id).getKodePelanggan();
                temp = "User-"+temp.substring(temp.indexOf('-') + 1);
                modifFile(temp, 1);
                modifFile("User Biasa", 4); 

                Pelanggan pel = menus.getPelanggan(id); 

                menus.getPelanggan(id).setKodePelanggan(temp);
                menus.getPelanggan(id).setNama(pel.getNama());
                menus.getPelanggan(id).setKategori("User Biasa");
                menus.getPelanggan(id).setNoTelp(pel.getNoTelp());
                menus.getPelanggan(id).setPassword(pel.getPassword());
                menus.getPelanggan(id).setUmur(pel.getUmur());
                menus.getPelanggan(id).setHistori(pel.dapatHistori());
                menus.getPelanggan(id).setStatus(pel.getStatus());


                frame.setContentPane(menuUtama(frame));
                frame.setVisible(true);
            }else{
                JalaApp.pilKategori = "0"; 
                frame.setContentPane(menuUtama(frame));
                frame.setVisible(true);
            }
            JalaApp.pilMenu = null; 
        });
    
        // Set content pane of the frame
    
        // Set frame properties
        frame.setTitle("Daftar Kategori");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800, 600);
        frame.setVisible(true);

        return contentPane;
    }

    private static JPanel nambahFilm(JFrame frame) {
        JPanel contentPane = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
    
        // Judul
        JLabel judulLabel = new JLabel("Judul:");
        JTextField judulField = new JTextField(20);
        contentPane.add(judulLabel, constraints);
        constraints.gridy++;
        contentPane.add(judulField, constraints);
    
        // Genre
        JLabel genreLabel = new JLabel("Genre:");
        JTextField genreField = new JTextField(20);
        constraints.gridy++;
        contentPane.add(genreLabel, constraints);
        constraints.gridy++;
        contentPane.add(genreField, constraints);
    
        // Tahun
        JLabel tahunLabel = new JLabel("Tahun:");
        JTextField tahunField = new JTextField(20);
        constraints.gridy++;
        contentPane.add(tahunLabel, constraints);
        constraints.gridy++;
        contentPane.add(tahunField, constraints);

        //kategori
        JLabel kategoriLabel = new JLabel("Kategori:");
        JTextField kategoriField = new JTextField(20);
        constraints.gridy++;
        contentPane.add(kategoriLabel, constraints);
        constraints.gridy++;
        contentPane.add(kategoriField, constraints);

        //umur
        JLabel umurLabel = new JLabel("Umur:");
        JTextField umurField = new JTextField(20);
        constraints.gridy++;
        contentPane.add(umurLabel, constraints);
        constraints.gridy++;
        contentPane.add(umurField, constraints);

        //sinopsis
        JLabel sinopsisLabel = new JLabel("Sinopsis:");
        JTextField sinopsisField = new JTextField(20);
        constraints.gridy++;
        contentPane.add(sinopsisLabel, constraints);
        constraints.gridy++;
        contentPane.add(sinopsisField, constraints);
    
        // Tombol
        constraints.gridy++;
        JPanel tomb = new JPanel(); 
        JButton simpanButton = new JButton("Simpan");
        tomb.add(simpanButton, constraints);

        JButton kembali = new JButton("Kembali"); 
        constraints.gridx++; 
        tomb.add(kembali, constraints); 
        contentPane.add(tomb); 
    
        simpanButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JalaApp.judulBaru = judulField.getText(); 
                JalaApp.genreBaru = genreField.getText(); 
                JalaApp.tahunBaru = tahunField.getText(); 
                JalaApp.kategoriBaru = kategoriField.getText(); 
                JalaApp.umurBaru = umurField.getText(); 
                JalaApp.sinopsisBaru = sinopsisField.getText(); 

                boolean genreBener, tahunBener; 
                genreBener = false; tahunBener = false; 
                for (genreFilm genre : genreFilm.values()) {
                    if (genre.getGenre().equalsIgnoreCase(genreBaru)) {
                        genreBener = true;
                        break;
                    }
                }
                try{
                    int tangka = Integer.parseInt(JalaApp.tahunBaru); 
                    if(tangka<1800){
                        tahunBener = false; 
                    }else{
                        tahunBener = true; 
                    }
                }catch(Exception s){
                    tahunBener = false; 
                }

                if (tahunBener && genreBener) {
                    JOptionPane.showMessageDialog(frame, "Successfully add Film");
                } else {
                    JOptionPane.showMessageDialog(frame, "Fail to add Film");
                }

                frame.setContentPane(menuUtama(frame));
                frame.setVisible(true);
                
            }
        });

        kembali.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JalaApp.judulBaru = "0"; 
                JalaApp.genreBaru = "0"; 
                JalaApp.tahunBaru = "0"; 
                JalaApp.kategoriBaru = "0"; 
                JalaApp.umurBaru = "0"; 
                JalaApp.sinopsisBaru = "0"; 
                frame.setContentPane(menuUtama(frame));
                frame.setVisible(true);
            }
        });

        
    
        return contentPane;
    }
    
}
