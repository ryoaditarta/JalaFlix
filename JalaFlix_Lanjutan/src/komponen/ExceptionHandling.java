package komponen;

class UmurNegatifException extends Exception {
    public UmurNegatifException(String errorMessage) {
        super(errorMessage);
    }
}

class TahunTooOldException extends Exception{
    public TahunTooOldException(String errorMessage){
        super(errorMessage); 
    }
}
