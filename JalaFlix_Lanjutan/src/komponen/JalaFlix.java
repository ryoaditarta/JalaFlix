package komponen;
import java.util.*; 

public class JalaFlix {

    static Scanner input = new Scanner(System.in); 
    public static void main(String[] args) {
        
        menus mn = new menus();
        JalaApp reglog = new JalaApp();
        while(true){
            mn.menuAwal();
            String cmd = null;
            while (reglog.getLogOrReg() == null) {
                try {
                    Thread.sleep(100); // Wait for 100 milliseconds before checking again
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            cmd = reglog.getLogOrReg();
            switch(cmd){
                case "1":
                    System.out.println("\nLogin ke Akun Yang Sudah ada!"); 
                    System.out.println("Nama: ");
                    String nama = JalaApp.username; 
                    System.out.println("Password: "); 
                    String pwd = JalaApp.password;
                    boolean test = mn.loginAkun(nama, pwd); 
                    if(test){
                        JalaApp.berhasilMasuk = "true"; 
                        int xi = mn.idLogin(nama, pwd); 
                        if(xi!=-1){
                            JalaApp.id = xi; 
                            mn.menuLogin(xi); 
                        }else{
                            System.out.println("Gagal Masuk!\n2");
                        }
                    }else{
                        JalaApp.berhasilMasuk = "false"; 
                    }
                break; 
                case "2":
                    mn.buatAkun(JalaApp.username, JalaApp.password); 
                break;
                case"3":
                    System.out.println("\nKeluar JalaFlix. Terima Kasih");
                    return; 
                default:
                System.out.println("Input salah!"); 
                    return; 
            }
            JalaApp.reset(); 
        }
    }
}
