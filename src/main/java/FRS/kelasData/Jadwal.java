package FRS.kelasData;

import javafx.beans.property.SimpleStringProperty;

public class Jadwal {
    private SimpleStringProperty jam;
    private JadwalKelas senin;
    private JadwalKelas selasa;
    private JadwalKelas rabu;
    private JadwalKelas kamis;
    private JadwalKelas jumat;
    private JadwalKelas sabtu;

    public Jadwal(String jam, JadwalKelas senin, JadwalKelas selasa, JadwalKelas rabu, JadwalKelas kamis,
            JadwalKelas jumat, JadwalKelas sabtu) {
        this.jam = new SimpleStringProperty(jam);
        this.senin = senin;
        this.selasa = selasa;
        this.rabu = rabu;
        this.kamis = kamis;
        this.jumat = jumat;
        this.sabtu = sabtu;
    }

    public String getJam() {
        String jamMulai = this.jam.get(); 

        int jam = Integer.parseInt(jamMulai.substring(0, 2));
        int menit = Integer.parseInt(jamMulai.substring(3, 5));

        int totalMenit = jam * 60 + menit + 30;
        int jamSelesai = totalMenit / 60;
        int menitSelesai = totalMenit % 60;

        String jamAkhir = String.format("%02d:%02d", jamSelesai, menitSelesai);

        return jamMulai + " - " + jamAkhir; 
    }

    public String getSenin() {
        if (senin != null) {
            return senin.getNamaMk() + "\n"  + senin.getMetodePertemuan();
        }

        return "";
    }

    public String getSelasa() {
        if (selasa != null) {
            return selasa.getNamaMk() + "\n"  + selasa.getMetodePertemuan();
        }

        return "";
    }

    public String getRabu() {
        if (rabu != null) {
            return rabu.getNamaMk() + "\n"  + rabu.getMetodePertemuan();
        }

        return "";
    }

    public String getKamis() {
        if (kamis != null) {
            return kamis.getNamaMk() + "\n" + kamis.getMetodePertemuan();
        }

        return "";
    }

    public String getJumat() {
        if (jumat != null) {
            return jumat.getNamaMk() + "\n" +  jumat.getMetodePertemuan();
        }

        return "";
    }

    public String getSabtu() {
        if (sabtu != null) {
            return sabtu.getNamaMk() + "\n"  + sabtu.getMetodePertemuan();
        }

        return "";
    }

    public SimpleStringProperty jamProperty() {
        return new SimpleStringProperty(getJam());
    }

    public SimpleStringProperty seninProperty() {
        return new SimpleStringProperty(getSenin());
    }

    public SimpleStringProperty selasaProperty() {
        return new SimpleStringProperty(getSelasa());
    }

    public SimpleStringProperty rabuProperty() {
        return new SimpleStringProperty(getRabu());
    }

    public SimpleStringProperty kamisProperty() {
        return new SimpleStringProperty(getKamis());
    }

    public SimpleStringProperty jumatProperty() {
        return new SimpleStringProperty(getJumat());
    }

    public SimpleStringProperty sabtuProperty() {
        return new SimpleStringProperty(getSabtu());
    }

    public void setSenin(JadwalKelas senin) {
        this.senin = senin;
    }

    public void setSelasa(JadwalKelas selasa) {
        this.selasa = selasa;
    }

    public void setRabu(JadwalKelas rabu) {
        this.rabu = rabu;
    }

    public void setKamis(JadwalKelas kamis) {
        this.kamis = kamis;
    }

    public void setJumat(JadwalKelas jumat) {
        this.jumat = jumat;
    }

    public void setSabtu(JadwalKelas sabtu) {
        this.sabtu = sabtu;
    }
}
