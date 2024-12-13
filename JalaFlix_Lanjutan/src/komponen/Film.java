package komponen;
public class Film {
    protected String judul; 
    protected genreFilm genre; 
    protected String tahun; 
    protected String kategori; 
    protected String batasUmur; 
    protected String sinopsis;

    public String getJudul() {
        return judul;
    }

    public String getGenre(){
        return genre.toString(); 
    }

    public String getBatasUmur() {
        return batasUmur;
    }

    public String getKategori(){
        return kategori;
    }

    public String getSinopsis(){
        return sinopsis; 
    }

    public String getTahun(){
        return tahun; 
    }
    Film(){

    }

    public enum genreFilm{
        SCIFI("Science Fiction"),
        HORROR("Horror"), 
        FANTASY("Fantasy"),
        MYSTERY("Mystery"),
        COMEDY("Comedy"),
        DARKCOMEDY("DarkComedy"),
        ADVENTURE("Adventure"),
        ACTION("Action"),
        THRILLER("Thriller"),
        ANIME("Anime"); 


        private String gen; 

        private genreFilm(String gen){
            this.gen = gen; 
        }

        public String getGenre(){
            return gen; 
        }

    }

    Film(String judul, genreFilm genre, String tahun, String kategori, String sinopsis, String batasUmur) throws TahunTooOldException{
        int thn = 0; 
        if(!tahun.equals("")){
            try{
                thn = Integer.parseInt(tahun); 
            }catch(Exception e){
                System.out.println("\n Masukkan tahun yang benar!");
                return;
            }
        }else if(tahun.equals("")){

        }
        
        if(!tahun.equals("")){
            if(thn<1800){
                throw new TahunTooOldException("\n\u001B[31mTahun film terlalu tua! gagal menambahkan film.\u001B[0m\n"); 
            }
        }

        this.judul = judul; 
        this.genre = genre; 
        this.tahun = tahun; 
        this.kategori = kategori; 
        this.sinopsis = sinopsis; 
        this.batasUmur = batasUmur; 
    }

    public void displayFilm(int i){
        
        System.out.printf("%3s %-40s|%-25s|%-8s|%-10s|%-5s|\n", (i+1)+".", judul, genre, tahun, kategori, batasUmur);
    }

    public void displayFULL(){
        System.out.println("\nJudul: "+judul); 
        System.out.println("Genre: "+genre);
        System.out.println("Tahun: "+tahun);
        System.out.println("Kategori: "+kategori);
        System.out.println("Sinopsis: "+sinopsis); 
        System.out.println("Batas: "+batasUmur); 
    }

}

class filmReguler extends Film{
    filmReguler(String judul, genreFilm genre, String tahun, String kategori, String batasUmur)throws TahunTooOldException{
        super(judul, genre, tahun, kategori, kategori, batasUmur);
    }
    public void displayFilm(int i){
        System.out.printf("\u001B[33m%3s %-40s|%-25s|%-8s|%-10s|%-5s|\u001B[0m\n", (i+1)+".", super.judul, super.genre, super.tahun, super.kategori, super.batasUmur);
    }
}

class filmNew extends Film{
    filmNew(String judul, genreFilm genre, String tahun, String kategori, String batasUmur)throws TahunTooOldException{
        super(judul, genre, tahun, kategori, kategori, batasUmur); 
    }
    public void displayFilm(int i){ 
        System.out.printf("\u001B[32m%3s %-40s|%-25s|%-8s|%-10s|%-5s|\u001B[0m\n", (i+1)+".", super.judul, super.genre, super.tahun, super.kategori, super.batasUmur);
    }
}

class filmOri extends Film{
    filmOri(String judul, genreFilm genre, String tahun, String kategori, String batasUmur)throws TahunTooOldException{
        super(judul, genre, tahun, kategori, kategori, batasUmur);
    }
    public void displayFilm(int i){
        System.out.printf("\u001B[34m%3s %-40s|%-25s|%-8s|%-10s|%-5s|\u001B[0m\n", (i+1)+".", super.judul, super.genre, super.tahun, super.kategori, super.batasUmur);
    }
}