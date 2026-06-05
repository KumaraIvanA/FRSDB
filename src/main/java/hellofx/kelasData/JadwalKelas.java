package hellofx.kelasData;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class JadwalKelas {
    private SimpleStringProperty namaMk;
    private SimpleStringProperty kelas;
    private SimpleStringProperty waktuMulai;
    private SimpleIntegerProperty durasi;
    private SimpleStringProperty hari;

    public JadwalKelas(String namaMk, String kelas, String waktuMulai, int durasi, String hari) {
        this.namaMk = new SimpleStringProperty(namaMk);
        this.kelas = new SimpleStringProperty(kelas);
        this.waktuMulai = new SimpleStringProperty(waktuMulai);
        this.durasi = new SimpleIntegerProperty(durasi);
        this.hari = new SimpleStringProperty(hari);
    }

    public String getNamaMk() {
        return this.namaMk.get();
    }

    public String getKelas() {
        return this.kelas.get();
    }

    public String getWaktuMulai() {
        return this.waktuMulai.get();
    }

    public int getDurasi() {
        return this.durasi.get();
    }

    public String getHari() {
        return this.hari.get();
    }

    public SimpleStringProperty NamaMkProperty(){return namaMk;}
    public SimpleStringProperty kelasProperty(){return kelas;}
    public SimpleStringProperty waktuMulaiProperty(){return waktuMulai;}
    public SimpleIntegerProperty durasiProperty(){return durasi;}
    public SimpleStringProperty hariProperty(){return hari;}
}