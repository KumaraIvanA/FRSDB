package hellofx.kelasData;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Mahasiswa {
    private SimpleStringProperty npm;
    private SimpleStringProperty nama;
    private SimpleStringProperty email;
    private SimpleStringProperty namaJurusan;

    public Mahasiswa(String npm, String nama, String email, String namaJurusan) {
        this.npm = new SimpleStringProperty(npm);
        this.nama = new SimpleStringProperty(nama);
        this.email = new SimpleStringProperty(email);
        this.namaJurusan = new SimpleStringProperty(namaJurusan);
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

    public String getNamaJurusan() {
        return this.namaJurusan.get();
    }

    public SimpleStringProperty npmProperty(){ return npm;}
    public SimpleStringProperty namaProperty(){ return nama;}
    public SimpleStringProperty emailProperty(){ return email;}
    public SimpleStringProperty namaJurusanProperty(){return namaJurusan;}

}
