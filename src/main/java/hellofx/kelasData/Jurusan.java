package hellofx.kelasData;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Jurusan {
    private SimpleIntegerProperty idJurusan;
    private SimpleStringProperty namaJurusan;

    public Jurusan(int idJurusan, String namaJurusan) {
        this.idJurusan = new SimpleIntegerProperty(idJurusan);
        this.namaJurusan = new SimpleStringProperty(namaJurusan);
    }

    public int idJurusan() {
        return this.idJurusan.get();
    }

    public String getnamaJurusan() {
        return this.namaJurusan.get();
    }

    public SimpleIntegerProperty idJurusanProperty() {
        return idJurusan;
    }

    public SimpleStringProperty namaJurusanProperty() {
        return namaJurusan;
    }
}
