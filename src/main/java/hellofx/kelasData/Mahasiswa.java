package hellofx.kelasData;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Mahasiswa {
    private SimpleStringProperty npm;
    private SimpleStringProperty nama;
    private SimpleStringProperty email;
    private SimpleIntegerProperty idJurusan;

    public Mahasiswa(String npm, String nama, String email, int idJurusan) {
        this.npm = new SimpleStringProperty(npm);
        this.nama = new SimpleStringProperty(nama);
        this.email = new SimpleStringProperty(email);
        this.idJurusan = new SimpleIntegerProperty(idJurusan);
    }

    public String getNPM() {
        return this.npm.get();
    }

    public String getNama() {
        return this.nama.get();
    }

    public String getEmail() {
        return this.email.get();
    }

    public int getIdJurusan(){
        return this.idJurusan.get();
    }

    
    public SimpleStringProperty npmProperty(){ return npm;}
    public SimpleStringProperty namaProperty(){ return nama;}
    public SimpleStringProperty emailProperty(){ return email;}
    public SimpleIntegerProperty idJurusan(){ return idJurusan;}
}
