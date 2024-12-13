package komponen;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*; 

class menus {
    public Scanner input = new Scanner(System.in); 
    private static ArrayList<Pelanggan> pelanggan = new ArrayList<Pelanggan>(); 
    public static ArrayList<Film> DaftarFilm = new ArrayList<Film>();

    public static Pelanggan getPelanggan(int i){
        return pelanggan.get(i); 
    }
    menus(){
        Dfilm.print();
        String folderPath = "Users";
        File folder = new File(folderPath);
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            for (File file : files) {
                ArrayList<String> data = new ArrayList<String>(); 
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            data.add(line); 
                        }
                    } catch (IOException e) {
                        System.out.println("An error occurred while reading the file: " + e.getMessage());
                        continue;
                    }
                }
                Pelanggan temp; 
                ArrayList<String> temphis = new ArrayList<String>(); 
                if(data.get(6).equals("null")) temphis = null; 
                else{
                    String[] idx = data.get(6).split("#"); 
                    for(int i=0; i<idx.length; i++){
                        temphis.add(idx[i]); 
                    }
                }
                //kategori instansiasi
                switch(data.get(3)){
                    case "Regular": 
                        temp = new pelangganBiasa(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4), data.get(5), temphis, data.get(7));
                    break; 
                    case "Gold": 
                        temp = new pelangganBiasa(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4), data.get(5), temphis, data.get(7));
                    break; 
                    case "Platinum":
                        temp = new pelangganBiasa(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4), data.get(5), temphis, data.get(7));
                    break; 
                    default:
                    temp = new pelangganBiasa(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4), data.get(5), temphis, data.get(7)); 

                }

                pelanggan.add(temp);
            }
        } else {
            System.out.println("Invalid folder path");
        }
    }

    public void menuAwal(){
        System.out.println("Selamat datang di JalaFlix! (Angka)");
        System.out.println("1. Login");
        System.out.println("2. Buat Akun");
        System.out.println("3. Keluar dari JalaFlix"); 
    }

    public void buatAkun(String nama, String password){

        Pelanggan temp = new pelangganBiasa("User-"+((int)(Math.random()*1001)), nama, "-", "User Biasa", "Aktif", password, null, "-"); 
        pelanggan.add(temp); 
        String filePath = "Users/" + temp.getNama() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(temp.getKodePelanggan());
            writer.newLine();
            writer.write(temp.getNama());
            writer.newLine();
            writer.write(temp.getNoTelp()); 
            writer.newLine();
            writer.write(temp.getKategori()); 
            writer.newLine();
            writer.write(temp.getStatus());
            writer.newLine();
            writer.write(temp.getPassword());
            writer.newLine();
            writer.write("null");
            writer.newLine(); 
            writer.write(temp.getUmur()); 
            writer.newLine();
            writer.close();
            
            System.out.println("File created successfully: " + filePath);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public boolean loginAkun(String nama, String pwd){ 
        System.out.println(pelanggan.size());
        for(int i=0; i<pelanggan.size(); i++){
            if(pelanggan.get(i).getNama().equals(nama)){
                if(pelanggan.get(i).getPassword().equals(pwd)){
                    return true; 
                }
            }
        }
        System.out.println("Nama/Password Salah atau tidak ditemukan!");
        return false; 
    }

    public int idLogin(String nama, String pwd){
        for(int i=0; i<pelanggan.size(); i++){
            if(pelanggan.get(i).getNama().equals(nama)){
                if(pelanggan.get(i).getPassword().equals(pwd)){
                    System.out.println("Berhasil Login!\n"); 
                    return i;
                }
            }
        }
        return -1; 
    }

    public void menuLogin(int idx){
        while(true){
            System.out.println("Selamat datang di JalaFlix! Silahkan pilih menu! (Angka)"); 
            System.out.println("1. Daftar Kategori");
            System.out.println("2. Nonton Film"); 
            System.out.println("3. Mengganti Kategori"); 
            System.out.println("4. Lihat Daftar Film"); 
            System.out.println("5. Logout");
            System.out.println("6. Profil Akun dan Lengkapi Profil"); 
            System.out.println("7. History"); 
            System.out.println("8. Menambahkan Film");
            JalaApp.pilMenu = null; 
            while (JalaApp.pilMenu == null) {
                try {
                    Thread.sleep(100); // Wait for 100 milliseconds before checking again
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String id = JalaApp.pilMenu; 
            switch(id){
                case"1":
                    if(pelanggan.get(idx).getKategori().equals("User Biasa")){
                        System.out.println("Pilih Kategori yang diinginkan! (angka)");
                        System.out.println("1. Regular"); 
                        System.out.println("2. Gold"); 
                        System.out.println("3. Platinum"); 
                        JalaApp.pilKategori = null; 
                        while (JalaApp.pilKategori == null) {
                            try {
                                Thread.sleep(100); // Wait for 100 milliseconds before checking again
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        String angka = JalaApp.pilKategori; 
                        switch(angka){
                            case "1":
                                this.daftarRegular(idx);
                            break; 
                            case "2":
                                this.daftarGold(idx);
                            break; 
                            case "3":
                                this.daftarPlatinum(idx);
                            break; 
                            default: 
                                System.out.println("Input Salah!!");
                        }
                    }else{
                        System.out.println("Anda sudah terdaftar sebagai pelanggan "+pelanggan.get(idx).getKategori());
                    }
                    JalaApp.pilMenu = null; 
                break; 
                case"2":
                    String anggota = pelanggan.get(idx).getKategori();
                    displayFilmKategori(anggota, idx);
                break;
                case"3":
                    if(!pelanggan.get(idx).getKategori().equals("")){
                        System.out.println("Anda sedang terdaftar sebagai pelanggan "+pelanggan.get(idx).getKategori());
                        System.out.println("Ingin mengganti kategori?");
                        System.out.println("1. Regular");
                        System.out.println("2. Gold");
                        System.out.println("3. Platinum");
                        System.out.println("4. Unsubscribe");
                        JalaApp.pilKategori = null; 
                        while (JalaApp.pilKategori == null) {
                            try {
                                Thread.sleep(100); // Wait for 100 milliseconds before checking again
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        String cmd = JalaApp.pilKategori; 
                        switch(cmd){
                            case "1":
                            this.upgradeRegular(idx);
                            break; 
                            case "2":
                            this.upgradeGold(idx); 
                            break; 
                            case "3":
                            this.upgradePlatinum(idx); 
                            break; 
                            case"4":
                            this.unsubs(idx); 
                            break; 
                            default: 
                            System.out.println("Gagal Mengganti Kategori");
                        }
                    }else{
                        System.out.println("Anda belum terdaftar dalam kategori manapun! Daftar terlebih dahulu");
                    }   
                        break;
                case"4":
                    System.out.println("Daftar Film JafaFlix: \n");
                    displayFilm();
                break;
                case"5": 
                    if(menuLogout()) return;
                break;
                case"6":
                    pelanggan.get(idx).profil(); 
                    JalaApp.pilupdate = null; 
                    while (JalaApp.pilupdate == null) {
                        try {
                            Thread.sleep(100); // Wait for 100 milliseconds before checking again
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    String cmd = JalaApp.pilupdate; 
                    switch(cmd){
                        case"1":
                            isidatadiri(idx);
                        break; 
                        case"2":
                            System.out.println("Gagal mengganti data akun\n"); 
                        break; 
                        default:
                        
                    }
                break; 
                case "7":
                if(pelanggan.get(idx).dapatHistori()==null){
                    System.out.println("Tidak ada Histori\n");
                }else{   
                        pelanggan.get(idx).displayHistory();
                        System.out.println("\nIngin hapus nomor berapa?");
                        System.out.println("Tidak hapus = 0");
                        
                    }
                break;
                case "8":
                    pelanggan.get(idx).tambahFilm();
                break; 
                default:
                System.out.println("Input Salah!");
            }
        }
            
    }

    public void isidatadiri(int idx){
        String noTelp=""; 
        String umur = ""; 
        System.out.println("Mengganti data akun!");
        System.out.println("No.Telepon:");
        noTelp = pelanggan.get(idx).getNoTelp(); 
        System.out.println("Umur: "); 
        umur = pelanggan.get(idx).getUmur(); 
        
        try{
            ArrayList<String> his = pelanggan.get(idx).dapatHistori(); 
            String pass = pelanggan.get(idx).getPassword(); 
            pelanggan.set(idx, new pelangganBiasa(pelanggan.get(idx).getKodePelanggan(), pelanggan.get(idx).getNama(), noTelp, pelanggan.get(idx).getKategori(), pelanggan.get(idx).getStatus(), umur)); 
            pelanggan.get(idx).setHistori(his);
            pelanggan.get(idx).setPassword(pass);
        }catch(UmurNegatifException ex){
            System.out.println(ex.getMessage());
        }

    }

    public boolean menuLogout(){
        while(true){
            System.out.println("Anda Yakin Ingin Logout?");
            System.out.println("1.YA");
            System.out.println("2.TIDAK");
            while (JalaApp.pillogout == null) {
                try {
                    Thread.sleep(100); // Wait for 100 milliseconds before checking again
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            String id = JalaApp.pillogout; 
            JalaApp.pilMenu = null; 
            switch(id){
                case"1":
                return true;
                case"2":
                return false; 
                default:
                System.out.println("Input Salah!");
            }
        }
    }

    void daftarPlatinum(int idx){
        pelanggan.get(idx).platinum();
        while (JalaApp.konfirmasikat == null) {
            try {
                Thread.sleep(100); // Wait for 100 milliseconds before checking again
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String angka3 = JalaApp.konfirmasikat; 
        switch(angka3){
            case"1":
                System.out.println("Berhasil Mendaftar ke kategori Platinum");
                pelanggan.get(idx).setKategori("Platinum"); 
                String kat = pelanggan.get(idx).getKodePelanggan(); 
                if(kat.contains("User")){
                    pelanggan.get(idx).setKodePelanggan(kat.replace("User", "PLT")); 
                }else if(kat.contains("REG")){
                    pelanggan.get(idx).setKodePelanggan(kat.replace("REG", "PLT"));
                }else if(kat.contains("GOLD")){
                    pelanggan.get(idx).setKodePelanggan(kat.replace("GOLD", "PLT"));
                }
                pelanggan.set(idx , new pelangganPlatinum(pelanggan.get(idx).getKodePelanggan(), pelanggan.get(idx).getNama(), pelanggan.get(idx).getNoTelp(), pelanggan.get(idx).getKategori(), pelanggan.get(idx).getStatus(), pelanggan.get(idx).getPassword(), pelanggan.get(idx).dapatHistori(), pelanggan.get(idx).getUmur())); 
            break;
            default: 
                System.out.println("Gagal Mendaftar kategori"); 
        }
    }

    void daftarRegular(int idx){
        pelanggan.get(idx).regular();
        while (JalaApp.konfirmasikat == null) {
            try {
                Thread.sleep(100); // Wait for 100 milliseconds before checking again
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String angka3 = JalaApp.konfirmasikat; 
        switch(angka3){
            case"1":
                System.out.println("Berhasil Mendaftar ke kategori Regular");
                pelanggan.get(idx).setKategori("Regular"); 
                String kat = pelanggan.get(idx).getKodePelanggan(); 
                if(kat.contains("User")){
                    pelanggan.get(idx).setKodePelanggan(kat.replace("User", "REG")); 
                    JalaApp.modifFile("REG", 1);
                }else if(kat.contains("PLT")){
                    pelanggan.get(idx).setKodePelanggan(kat.replace("PLT", "REG"));
                    JalaApp.modifFile("GOLD", 1);
                }else if(kat.contains("GOLD")){
                    pelanggan.get(idx).setKodePelanggan(kat.replace("GOLD", "REG"));
                    JalaApp.modifFile("PLT", 1);
                }  
                pelanggan.set(idx, new pelangganRegular(pelanggan.get(idx).getKodePelanggan(), pelanggan.get(idx).getNama(), pelanggan.get(idx).getNoTelp(), pelanggan.get(idx).getKategori(), pelanggan.get(idx).getStatus(), pelanggan.get(idx).getPassword(), pelanggan.get(idx).dapatHistori(), pelanggan.get(idx).getUmur())); 
            break;
            default: 
                System.out.println("Gagal Mendaftar kategori"); 
        }
    }

    void daftarGold(int idx){
        pelanggan.get(idx).gold();
        while (JalaApp.konfirmasikat == null) {
            try {
                Thread.sleep(100); // Wait for 100 milliseconds before checking again
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String angka3 = JalaApp.konfirmasikat; 
        switch(angka3){
            case"1":
                System.out.println("Berhasil Mendaftar ke kategori Gold");
                pelanggan.get(idx).setKategori("Gold"); 
                String kat = pelanggan.get(idx).getKodePelanggan(); 
                if(kat.contains("User")){
                    pelanggan.get(idx).setKodePelanggan(kat.replace("User", "GOLD")); 
                }else if(kat.contains("REG")){
                    pelanggan.get(idx).setKodePelanggan(kat.replace("REG", "GOLD"));
                }else if(kat.contains("PLT")){
                    pelanggan.get(idx).setKodePelanggan(kat.replace("PLT", "GOLD"));
                } 
                pelanggan.set(idx, new pelangganRegular(pelanggan.get(idx).getKodePelanggan(), pelanggan.get(idx).getNama(), pelanggan.get(idx).getNoTelp(), pelanggan.get(idx).getKategori(), pelanggan.get(idx).getStatus(), pelanggan.get(idx).getPassword(), pelanggan.get(idx).dapatHistori(), pelanggan.get(idx).getUmur())); 
            break;
            default: 
                System.out.println("Gagal Mendaftar kategori"); 
        }
    }

    void upgradePlatinum(int idx){
        pelanggan.get(idx).platinum();
        while (JalaApp.konfirmasikat == null) {
            try {
                Thread.sleep(100); // Wait for 100 milliseconds before checking again
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String angka3 = JalaApp.konfirmasikat; 
        switch(angka3){
            case"1":
                System.out.println("Berhasil Mendaftar ke kategori Platinum");
                pelanggan.get(idx).setKategori("Platinum"); 
                String kat = pelanggan.get(idx).getKodePelanggan(); 
                if(kat.contains("User")){
                    pelanggan.get(idx).setKodePelanggan(kat.replace("User", "PLT")); 
                }else if(kat.contains("REG")){
                    pelanggan.get(idx).setKodePelanggan(kat.replace("REG", "PLT"));
                }else if(kat.contains("GOLD")){
                    pelanggan.get(idx).setKodePelanggan(kat.replace("GOLD", "PLT"));
                }
                pelanggan.set(idx, new pelangganRegular(pelanggan.get(idx).getKodePelanggan(), pelanggan.get(idx).getNama(), pelanggan.get(idx).getNoTelp(), pelanggan.get(idx).getKategori(), pelanggan.get(idx).getStatus(), pelanggan.get(idx).getPassword(), pelanggan.get(idx).dapatHistori(), pelanggan.get(idx).getUmur())); 
            break;
            default: 
                System.out.println("Gagal Mendaftar kategori"); 
        }     
    }

    void upgradeRegular(int idx){
        pelanggan.get(idx).regular();
        while (JalaApp.konfirmasikat == null) {
            try {
                Thread.sleep(100); // Wait for 100 milliseconds before checking again
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String angka3 = JalaApp.konfirmasikat; 
        switch(angka3){
            case"1":
                System.out.println("Berhasil Mendaftar ke kategori Regular");
                pelanggan.get(idx).setKategori("Regular"); 
                String kat = pelanggan.get(idx).getKodePelanggan(); 
                if(kat.contains("User")){
                    pelanggan.get(idx).setKodePelanggan(kat.replace("User", "REG")); 
                }else if(kat.contains("PLT")){
                    pelanggan.get(idx).setKodePelanggan(kat.replace("PLT", "REG"));
                }else if(kat.contains("GOLD")){
                    pelanggan.get(idx).setKodePelanggan(kat.replace("GOLD", "REG"));
                }
                pelanggan.set(idx, new pelangganRegular(pelanggan.get(idx).getKodePelanggan(), pelanggan.get(idx).getNama(), pelanggan.get(idx).getNoTelp(), pelanggan.get(idx).getKategori(), pelanggan.get(idx).getStatus(), pelanggan.get(idx).getPassword(), pelanggan.get(idx).dapatHistori(), pelanggan.get(idx).getUmur()));  
            break;
            default: 
                System.out.println("Gagal Mendaftar kategori"); 
        }  
    }

    void upgradeGold(int idx){
        pelanggan.get(idx).gold();
        while (JalaApp.konfirmasikat == null) {
            try {
                Thread.sleep(100); // Wait for 100 milliseconds before checking again
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String angka3 = JalaApp.konfirmasikat;  
        switch(angka3){
            case"1":
                System.out.println("Berhasil Mendaftar ke kategori Gold");
                pelanggan.get(idx).setKategori("Gold"); 
                String kat = pelanggan.get(idx).getKodePelanggan(); 
                if(kat.contains("User")){
                    pelanggan.get(idx).setKodePelanggan(kat.replace("User", "GOLD")); 
                }else if(kat.contains("REG")){
                    pelanggan.get(idx).setKodePelanggan(kat.replace("REG", "GOLD"));
                }else if(kat.contains("PLT")){
                    pelanggan.get(idx).setKodePelanggan(kat.replace("PLT", "GOLD"));
                } 
                pelanggan.set(idx, new pelangganRegular(pelanggan.get(idx).getKodePelanggan(), pelanggan.get(idx).getNama(), pelanggan.get(idx).getNoTelp(), pelanggan.get(idx).getKategori(), pelanggan.get(idx).getStatus(), pelanggan.get(idx).getPassword(), pelanggan.get(idx).dapatHistori(), pelanggan.get(idx).getUmur())); 
            break;
            default: 
                System.out.println("Gagal Mendaftar kategori"); 
        }
    }

    void unsubs(int idx){
        pelanggan.get(idx).uns();
        while (JalaApp.konfirmasikat == null) {
            try {
                Thread.sleep(100); // Wait for 100 milliseconds before checking again
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String angka3 = JalaApp.konfirmasikat; 
        switch(angka3){
            case"1":
                System.out.println("Berhasil Unsubscribe");
                pelanggan.get(idx).setKategori("User Biasa");
                String kat = pelanggan.get(idx).getKodePelanggan(); 
                if(kat.contains("User")){
                    pelanggan.get(idx).setKodePelanggan(kat.replace("User", "User")); 
                }else if(kat.contains("REG")){
                    pelanggan.get(idx).setKodePelanggan(kat.replace("REG", "User"));
                }else if(kat.contains("GOLD")){
                    pelanggan.get(idx).setKodePelanggan(kat.replace("GOLD", "User"));
                }else{
                    pelanggan.get(idx).setKodePelanggan(kat.replace("PLT", "User"));
                }
                pelanggan.set(idx, new pelangganRegular(pelanggan.get(idx).getKodePelanggan(), pelanggan.get(idx).getNama(), pelanggan.get(idx).getNoTelp(), pelanggan.get(idx).getKategori(), pelanggan.get(idx).getStatus(), pelanggan.get(idx).getPassword(), pelanggan.get(idx).dapatHistori(), pelanggan.get(idx).getUmur())); 
            break;
            default: 
                System.out.println("Gagal Melakukan Unsubscribe"); 
        }

    }

    void displayFilmKategori(String kategoris, int idx){
        if(kategoris.equals("User Biasa")){
            this.displayFilm();
        } else if(kategoris.equals("Regular")){
            System.out.println("\nKamu User Regular, kamu dapat mengakses film Reguler: \n");
            System.out.println("\033[38;5;94m===============================================================================================\033[0m");
            System.out.printf("%-4s%-40s|%-25s|%-8s|%-10s|%-5s|\n", "","Judul", "Genre", "Tahun", "Kategori", "Umur");
            int id = 0; 
            for(Film x: DaftarFilm){
                if(x.kategori.equals("Regular")){
                    try{
                        x = new filmReguler(x.judul, x.genre, x.tahun, x.kategori, x.batasUmur);
                    }catch(TahunTooOldException ex){
                        System.out.println(ex.getMessage());
                    }
                    x.displayFilm(id);
                }else{
                    x.displayFilm(id);
                }
                id++; 
            }
            System.out.println("\033[38;5;94m===============================================================================================\033[0m");
            System.out.println(); 
            pilihFilm(idx); 
        } else if(kategoris.equals("Gold")){
            System.out.println("\nKamu User Gold, Kamu dapat mengakses film reguler dan new:\n"); 
            System.out.println("\033[38;5;220m===============================================================================================\033[0m");
            System.out.printf("%-4s%-40s|%-25s|%-8s|%-10s|%-5s|\n", "","Judul", "Genre", "Tahun", "Kategori", "Umur");
            int id = 0; 
            for(Film x: DaftarFilm){
                if(x.kategori.equals("Regular")){
                    try{
                        x = new filmReguler(x.judul, x.genre, x.tahun, x.kategori, x.batasUmur);
                    }catch(TahunTooOldException ex){
                        System.out.println(ex.getMessage());
                    }

                    x.displayFilm(id);
                }else if(x.kategori.equals("New")){
                    try{
                        x = new filmNew(x.judul, x.genre, x.tahun, x.kategori, x.batasUmur);
                    }catch(TahunTooOldException ex){
                        System.out.println(ex.getMessage());
                    }
                    x.displayFilm(id);
                }else{
                    x.displayFilm(id);
                }
                id++; 
            }
            System.out.println("\033[38;5;220m===============================================================================================\033[0m");
            System.out.println();
            pilihFilm(idx); 
        } else if(kategoris.equals("Platinum")){
            System.out.println("\nKamu User Platinum, kamu dapat mengakses semua Film: ");
            System.out.println("\033[35m===============================================================================================\033[0m");
            System.out.printf("%-4s%-40s|%-25s|%-8s|%-10s|%-5s|\n", "","Judul", "Genre", "Tahun", "Kategori", "Umur");
            int id = 0; 
            for(Film x: DaftarFilm){
                if(x.kategori.equals("Regular")){
                    try{
                        x = new filmReguler(x.judul, x.genre, x.tahun, x.kategori, x.batasUmur);
                    }catch(TahunTooOldException ex){
                        System.out.println(ex.getMessage());
                    }
                    x.displayFilm(id);
                }else if(x.kategori.equals("New")){
                    try{
                        x = new filmNew(x.judul, x.genre, x.tahun, x.kategori, x.batasUmur);
                    }catch(TahunTooOldException ex){
                        System.out.println(ex.getMessage());
                    }
                    x.displayFilm(id);
                }else{
                    try{
                        x = new filmOri(x.judul, x.genre, x.tahun, x.kategori, x.batasUmur);
                    }catch(TahunTooOldException ex){
                        System.out.println(ex.getMessage());
                    }
                    x.displayFilm(id);
                }
                id++; 
            }
            System.out.println("\033[35m===============================================================================================\033[0m");
            System.out.println();
            pilihFilm(idx); 
        }
    }

    void displayFilm(){
        System.out.println("\nKamu User biasa, tidak dapat menonton film. Ini list daftar film yang ada: \n");
        System.out.println("================================================================================================");
        System.out.printf("%-4s%-40s|%-25s|%-8s|%-10s|%-5s|\n","", "Judul", "Genre", "Tahun", "Kategori", "Umur");
        int id = 0; 
            for(Film x: DaftarFilm){
                x.displayFilm(id);
                id++; 
            }
        System.out.println("================================================================================================");
        System.out.println(); 
    }

    void pilihFilm(int idx){
        int id = 1;
        System.out.println("Pilih Film yang ingin ditonton: \n"); 
        //String anggota = pelanggan.get(idx).getKategori(); 
        JalaApp.pilNonton = null; 
        while (JalaApp.pilNonton == null) {
            try {
                Thread.sleep(100); // Wait for 100 milliseconds before checking again
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String cmd = JalaApp.pilNonton; 
        boolean ada = false ; 
        try{
            Integer.parseInt(cmd); 
        }catch(Exception e){
            System.out.println("Input salah!");
            return; 
        }
        if(pelanggan.get(idx).getKategori().equals("Regular")){
            for(Film x: DaftarFilm){
                if(Integer.parseInt(cmd) == id&&!x.kategori.equals("New")&&!x.kategori.equals("Ori")){
                    if(pelanggan.get(idx).getUmur().equals("-")){
                        System.out.println("\nIsi data diri lengkat terlebih dahulu pada menu 6!\n");
                        return; 
                    }

                    int umr = Integer.parseInt(pelanggan.get(idx).getUmur()); 
                    int batasan = Integer.parseInt(x.batasUmur); 
                    if(umr<batasan){
                        System.out.println("Kamu belum cukup umur!\n");
                        return; 
                    }
                    
                    x.displayFULL(); 
                    ada = true; 
                    pelanggan.get(idx).addHistory(x.getJudul());
                }
                id++; 
            }
        }else if(pelanggan.get(idx).getKategori().equals("Gold")){
            for(Film x: DaftarFilm){
                if(Integer.parseInt(cmd) == id&&!x.kategori.equals("Ori")){
                    if(pelanggan.get(idx).getUmur().equals("-")){
                        System.out.println("\nIsi data diri lengkat terlebih dahulu pada menu 6!\n");
                        return; 
                    }

                    int umr = Integer.parseInt(pelanggan.get(idx).getUmur()); 
                    int batasan = Integer.parseInt(x.batasUmur); 
                    if(umr<batasan){
                        System.out.println("Kamu belum cukup umur!\n");
                        return; 
                    }

                    x.displayFULL();
                    ada = true; 
                    pelanggan.get(idx).addHistory(x.getJudul());
                }
                id++; 
            }
        }else{
            for(Film x: DaftarFilm){
                if(Integer.parseInt(cmd) == id){
                    if(pelanggan.get(idx).getUmur().equals("")){
                        System.out.println("\nIsi data diri lengkap terlebih dahulu pada menu 6!\n");
                        return; 
                    }
                    int umr = Integer.parseInt(pelanggan.get(idx).getUmur()); 
                    int batasan = Integer.parseInt(x.batasUmur); 
                    if(umr<batasan){
                        System.out.println("Kamu belum cukup umur!\n");
                        return; 
                    }

                    x.displayFULL(); 
                    ada = true; 
                    pelanggan.get(idx).addHistory(x.getJudul());
                }
                id++; 
            }
        }
        if(!ada){
            System.out.println("Anda tidak dapat menonton Film ini\n");
        }else{
            System.out.println("Sedang menonton film....\n\n");
            System.out.println("Film Selesai!\n");
            String stemp = "null";

            if(getPelanggan(idx).dapatHistori() != null){
                stemp = ""; 
                for(String judul: getPelanggan(idx).dapatHistori()){
                    if(!judul.isBlank())
                    stemp += judul+"#";
                }
            }

            JalaApp.modifFile(stemp, 7);
        }
        
    }



}
