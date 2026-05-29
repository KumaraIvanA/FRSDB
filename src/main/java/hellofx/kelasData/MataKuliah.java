package hellofx.kelasData;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MataKuliah {
    private SimpleIntegerProperty kodeMk;
    private SimpleStringProperty namaMk;
    private SimpleIntegerProperty jumlahSKS;
    private SimpleIntegerProperty idSemester;

    public MataKuliah(int kodeMK, String namaMk, int jumlahSKS, int idSemester) {
        this.namaMk = new SimpleStringProperty(namaMk);
        this.jumlahSKS = new SimpleIntegerProperty(jumlahSKS);
        this.kodeMk = new SimpleIntegerProperty(kodeMK);
        this.idSemester = new SimpleIntegerProperty(idSemester);
    }

    public String getNamaMK() {
        return this.namaMk.get();
    }

    public int getkodeMK(){
        return this.kodeMk.get();
    }

    public int getJumlahSKS() {
        return this.jumlahSKS.get();
    }

    public int getIdSemester(){
        return this.idSemester.get();
    }

    public SimpleStringProperty namaMKProperty() { return namaMk; }
    public SimpleIntegerProperty kodeMkProperty() {return kodeMk;}
    public SimpleIntegerProperty jumlahSKSProperty() { return jumlahSKS; }
    public SimpleIntegerProperty idSemesterProperty() {return idSemester;}
}
