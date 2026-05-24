package hellofx;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MataKuliah {
    private SimpleStringProperty namaMk;
    private SimpleStringProperty jurusan;
    private SimpleIntegerProperty jumlahSKS;

    public MataKuliah(String namaMk, String jurusan, int jumlahSKS) {
        this.namaMk = new SimpleStringProperty(namaMk);
        this.jurusan = new SimpleStringProperty(jurusan);
        this.jumlahSKS = new SimpleIntegerProperty(jumlahSKS);
    }

    public String getNamaMK() {
        return this.namaMk.get();
    }

    public String getJurusan() {
        return this.jurusan.get();
    }

    public int getJumlahSKS() {
        return this.jumlahSKS.get();
    }

    public SimpleStringProperty namaMKProperty() { return namaMk; }
    public SimpleStringProperty jurusanProperty() { return jurusan; }
    public SimpleIntegerProperty jumlahSKSProperty() { return jumlahSKS; }
}
