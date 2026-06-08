package hellofx.kelasData;

import javafx.beans.property.SimpleStringProperty;

public class Jadwal {
    private SimpleStringProperty jam;
    private JadwalKelas senin;
    private JadwalKelas selasa;
    private JadwalKelas rabu;
    private JadwalKelas kamis;
    private JadwalKelas jumat;
    private JadwalKelas sabtu;

    public Jadwal(String jam, JadwalKelas senin, JadwalKelas selasa, JadwalKelas rabu, JadwalKelas kamis, JadwalKelas jumat, JadwalKelas sabtu) {
        this.jam = new SimpleStringProperty(jam);
        this.senin = senin;
        this.selasa = selasa;
        this.rabu = rabu;
        this.kamis = kamis;
        this.jumat = jumat;
        this.sabtu = sabtu;
    }

    public String getJam() {
        return this.jam.get();
    }
 
    public String getSenin() {
        if (senin != null) {
            return senin.getNamaMk() + "\n";
        }
        
        return "";
    }

    public String getSelasa() {
        if (selasa != null) {
            return selasa.getNamaMk() + "\n";
        }

        return "";
    }

    public String getRabu() {
        if (rabu != null) {
            return rabu.getNamaMk() + "\n";
        }

        return "";
    }

    public String getKamis() {
        if (kamis != null) {
            return kamis.getNamaMk() + "\n";
        }

        return "";
    }

    public String getJumat() {
        if (jumat != null) {
            return jumat.getNamaMk() + "\n";
        }

        return "";
    }

    public String getSabtu() {
        if (sabtu != null) {
            return sabtu.getNamaMk() + "\n";
        }

        return "";
    }
    
    public SimpleStringProperty jamProperty(){return jam;}
    public SimpleStringProperty seninProperty(){return new SimpleStringProperty(getSenin());}
    public SimpleStringProperty selasaProperty(){return new SimpleStringProperty(getSelasa());}
    public SimpleStringProperty rabuProperty(){return new SimpleStringProperty(getRabu());}
    public SimpleStringProperty kamisProperty(){return new SimpleStringProperty(getKamis());}
    public SimpleStringProperty jumatProperty(){return new SimpleStringProperty(getJumat());}
    public SimpleStringProperty sabtuProperty(){return new SimpleStringProperty(getSabtu());}

    public void setSenin(JadwalKelas senin){this.senin = senin;}
    public void setSelasa(JadwalKelas selasa){this.selasa = selasa;}    
    public void setRabu(JadwalKelas rabu){this.rabu = rabu;}
    public void setKamis(JadwalKelas kamis){this.kamis = kamis;}
    public void setJumat(JadwalKelas jumat){this.jumat = jumat;}
    public void setSabtu(JadwalKelas sabtu){this.sabtu = sabtu;}
}
