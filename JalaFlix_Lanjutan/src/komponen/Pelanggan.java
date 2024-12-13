package komponen;


import java.util.*; 

interface customer{
     void platinum(); 
     void gold(); 
     void uns(); 
     void regular(); 
}

public abstract class Pelanggan implements customer{
   private String kodePelanggan=""; 
   private String nama; 
   private String umur; 
   private String noTelp; 
   private String kategori = "";
   private String status_keanggotaan= ""; 
   private String Password=""; 
   private ArrayList<String> histori = new ArrayList<>(); 

   Pelanggan(String kodePelanggan, String nama, String noTelp, String kategori, String status_keanggotaan, String umur) throws UmurNegatifException{
     this.kodePelanggan = kodePelanggan; 
     this.nama = nama; 
     this.noTelp = noTelp; 
     this.kategori = kategori; 
     this.status_keanggotaan = status_keanggotaan;  
     if(umur.length()==0){
          this.umur = umur; 
     }else if(Character.compare(umur.charAt(0), '-')==0){
          throw new UmurNegatifException("\n\u001B[31mUmur harus positif! Gagal memperbarui umur!\u001B[0m\n"); 
     }else{
          try{
               Integer.parseInt(umur); 
               this.umur = umur; 
               System.out.println("Berhasil memperbaharui data!\n");
          }catch(Exception e){
               System.out.println("\n\u001B[31mMasukkan umur yang benar! gagal memperbarui umur!\u001B[0m\n");
          }

     }
   }

   Pelanggan(String kodePelanggan, String nama, String noTelp, String kategori, String status_keanggotaan, String Password, ArrayList<String> histori, String umur){
     this.kodePelanggan = kodePelanggan; 
     this.nama = nama; 
     this.noTelp = noTelp; 
     this.kategori = kategori; 
     this.status_keanggotaan = status_keanggotaan; 
     this.Password = Password;
     this.histori = histori; 
     this.umur = umur; 
   }

   Pelanggan(){

   }

   public void displayDataPelanggan(){
        System.out.println("\nData Akun:");
        System.out.printf("%-20s: %s\n", "Nama", nama);
        System.out.printf("%-20s: %s\n", "Kode Pelanggan", kodePelanggan);
        System.out.printf("%-20s: %s\n", "Umur", umur);
        System.out.printf("%-20s: %s\n", "No. Telepon", noTelp); 
        System.out.printf("%-20s: %s\n", "Kategori", kategori); 
        System.out.printf("%-20s: %s\n", "Status Keanggotaan", status_keanggotaan); 
   }

   String getKodePelanggan(){
        return this.kodePelanggan; 
   }

   String getNama(){
        return this.nama; 
   }

   String getNoTelp(){
        return this.noTelp; 
   }

   String getKategori(){
        return kategori; 
   }
   
   String getPassword(){
        return Password; 
   }

   String getStatus(){
      return status_keanggotaan; 
   }
   String getUmur(){
     return umur; 
   }

   void setNama(String nama){
        this.nama = nama; 
   }
   
   void setNoTelp(String noTelp){
        this.noTelp = noTelp; 
   }

   void setPassword(String password){
       this.Password = password; 
   }

   void setKategori(String kategori){
        this.kategori = kategori; 
   }

   void setStatus(String s){
     status_keanggotaan = s; 
   }

   void setKodePelanggan(String s){
     kodePelanggan = s; 
   }

   void setHistori(ArrayList<String> histori){
     this.histori = histori; 
   }

   void setUmur(String umur){
     this.umur = umur; 
   }


   void addHistory(String judul){
        if(histori == null) histori = new ArrayList<>(); 
        histori.add(0, judul); 
   }

   void clearHistory(){
        histori.clear(); 
   }

   void isHapusHistory(){
     this.displayHistory();              
     System.out.println("\nIngin Menghapus Histori?");
     yaTidak();
   }

   void displayHistory(){
        int id = 1; 
        System.out.println("\nHistory tontonan: ");
        System.out.printf("%-4s%-40s|\n", "","Judul");
        for(String x: histori){
            if(id==11)break; 
            System.out.printf("%d. |%-40s|\n", id, x);
            id++; 
        }
   }

   public void hapusFilmHistori(int i){
      histori.remove(i); 
   }

   ArrayList<String> dapatHistori(){
     return histori; 
   }

   int sizeHistori(){
     return histori.size(); 
   }

   void yaTidak(){
     System.out.println("1. YA");
     System.out.println("2. TIDAK");
   }

   void profil(){
     System.out.println("Informasi Akun Anda: ");
     this.displayDataPelanggan();  
     System.out.println("\nIngin mengganti/melengkapi Informasi Akun?"); 
     this.yaTidak();
   }

   public abstract void platinum(); 
   public abstract void gold(); 
   public abstract void regular(); 
   public abstract void uns();  

   public void tambahFilm(){
          System.out.println("\nMasukkan data Film!"); 
          String judul,genre, tahun, kategori, umur, sinopsis; 
          JalaApp.judulBaru = null; 
          JalaApp.genreBaru = null; 
          JalaApp.tahunBaru = null; 
          JalaApp.kategoriBaru = null; 
          JalaApp.umurBaru = null; 
          JalaApp.sinopsisBaru = null; 
          while (JalaApp.judulBaru == null || JalaApp.genreBaru == null || JalaApp.tahunBaru == null || JalaApp.kategoriBaru == null || JalaApp.umurBaru == null || JalaApp.sinopsisBaru == null) {
               try {
                   Thread.sleep(100); // Wait for 100 milliseconds before checking again
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
           

          Film.genreFilm gen = null;      
          System.out.print("Judul: ");
          judul = JalaApp.judulBaru; 
          try{
               System.out.print("Genre (SCIFI, ACTION, FANTASY, COMEDY): ");
               genre = JalaApp.genreBaru; 
               gen = Film.genreFilm.valueOf(genre); 
               System.out.println("berhasil");
          }catch(Exception e){
               System.out.println("Genre Salah, Masukkan Ulang! ");
               System.out.println("gagal");
               return; 
          }
          System.out.print("Tahun: ");
          tahun = JalaApp.tahunBaru; 
          System.out.print("Kategori: ");
          kategori = JalaApp.kategoriBaru; 
          System.out.print("Batas Umur: ");
          umur = JalaApp.umurBaru; 
          System.out.print("Sinopsis: "); 
          sinopsis = JalaApp.sinopsisBaru;  
          Film a = new Film();
          

          try{
               a = new Film(judul, gen, tahun, kategori, sinopsis, umur); 
               menus.DaftarFilm.add(a); 
               System.out.println("berhasil");
          }catch(TahunTooOldException ex){
               System.out.println(ex.getMessage());
               System.out.println("gagal");
               return; 
          }

          Dfilm.write();
   }


}

class pelangganRegular extends Pelanggan{
     int jmlFilmTambah = 0; 
     pelangganRegular(String kodePelanggan, String nama, String noTelp, String kategori, String status_keanggotaan, String Password, ArrayList<String> histori, String umur){
          super(kodePelanggan, nama, noTelp, kategori, status_keanggotaan, Password, histori, umur);
     }

     public void platinum(){
          System.out.println("Ingin Upgrade ke Platinum? - Harga Rp.75000");
          yaTidak();
     }
  
     public void gold(){
          System.out.println("Ingin Upgrade ke Gold? - Harga Rp.35000");
          yaTidak();
     }
  
     public void regular(){
          System.out.println("Anda tidak akan upgrade - Harga Rp.0");
          yaTidak();
     }

     public void uns(){
          System.out.println("Yakin ingin unsubscribe?");
          yaTidak();
     }

     public void tambahFilm(){
          super.tambahFilm(); 
     }
}

class pelangganGold extends Pelanggan{
     int jmlFilmTambah = 0; 
     pelangganGold(String kodePelanggan, String nama, String noTelp, String kategori, String status_keanggotaan, String Password, ArrayList<String> histori, String umur){
          super(kodePelanggan, nama, noTelp, kategori, status_keanggotaan, Password, histori, umur);
     }

     public void platinum(){
          System.out.println("Ingin Upgrade ke Platinum? - Harga Rp.50000");
          yaTidak();
     }
  
     public void gold(){
          System.out.println("Anda tidak akan upgrade - Harga Rp.0");
          yaTidak();
     }
  
     public void regular(){
          System.out.println("Ingin downgrade ke Regular? - Harga Rp.0");
          yaTidak();
     }

     public void uns(){
          System.out.println("Yakin ingin unsubscribe?");
          yaTidak();
     }

     public void tambahFilm(){
          super.tambahFilm(); 
     }

}

class pelangganPlatinum extends Pelanggan{
     int jmlFilmTambah = 0; 
     pelangganPlatinum(String kodePelanggan, String nama, String noTelp, String kategori, String status_keanggotaan, String Password, ArrayList<String> histori, String umur){
          super(kodePelanggan, nama, noTelp, kategori, status_keanggotaan, Password, histori, umur);
     }

     public void platinum(){
          System.out.println("Anda tidak akan upgrade - Harga Rp.0");
          yaTidak();
     }
  
     public void gold(){
          System.out.println("Ingin downgrade ke Gold? - Harga Rp.0");
          yaTidak();
     }
  
     public void regular(){
          System.out.println("Ingin downgrade ke Regular? - Harga Rp.0");
          yaTidak();
     }
     
     public void uns(){
          System.out.println("Yakin ingin unsubscribe?");
          yaTidak();
     }

     public void tambahFilm(){
          super.tambahFilm(); 
     }
}

class pelangganBiasa extends Pelanggan{
     pelangganBiasa(String kodePelanggan, String nama, String noTelp, String kategori, String status_keanggotaan, String Password, ArrayList<String> histori, String umur){
          super(kodePelanggan, nama, noTelp, kategori, status_keanggotaan, Password, histori, umur);
     }
     pelangganBiasa(String kodePelanggan, String nama, String noTelp, String kategori, String status_keanggotaan, String umur)throws UmurNegatifException{
          super(kodePelanggan, nama, noTelp, kategori, status_keanggotaan, umur); 
     }
     public void platinum(){
          System.out.println("Ingin membeli Platinum - Harga Rp.100000");
          yaTidak();
     }
     public void gold(){
          System.out.println("Ingin membeli Gold - Harga Rp.50000");
          yaTidak();
     }

     public void regular(){
          System.out.println("Ingin membeli Regular - Harga Rp.15000");
          yaTidak();
     }

     public void uns(){
          System.out.println("Anda Pelanggan biasa - Unsubscribe berarti tidak ada perubahan");
     }


}
