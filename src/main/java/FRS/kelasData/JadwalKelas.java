package FRS.kelasData;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class JadwalKelas {
    private SimpleStringProperty namaMk;
    private SimpleStringProperty waktuMulai;
    private SimpleIntegerProperty durasi;
    private SimpleStringProperty hari;
    private SimpleStringProperty metodePertemuan;

    public JadwalKelas(String namaMk,String waktuMulai, int durasi, String hari, String metodePertemuan) {
        this.namaMk = new SimpleStringProperty(namaMk);
        this.waktuMulai = new SimpleStringProperty(waktuMulai);
        this.durasi = new SimpleIntegerProperty(durasi);
        this.hari = new SimpleStringProperty(hari);
        this.metodePertemuan = new SimpleStringProperty(metodePertemuan);
    }

    public String getNamaMk() {
        return this.namaMk.get();
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

    public String getMetodePertemuan() {
        return this.metodePertemuan.get();
    }

    public void setJam(String jam) {
        this.waktuMulai = new SimpleStringProperty(jam);
    }

    public SimpleStringProperty NamaMkProperty(){return namaMk;}
    public SimpleStringProperty waktuMulaiProperty(){return waktuMulai;}
    public SimpleStringProperty metodePertemuanProperty(){return metodePertemuan;}
    public SimpleIntegerProperty durasiProperty(){return durasi;}
    public SimpleStringProperty hariProperty(){return hari;}
}