package hellofx.kelasData;

public class KelasAjar {

    private String namaMK;
    private int jumlahSKS;
    private String hari;
    private String waktuMulai;
    private int durasi;
    private String jenisPertemuan;
    private String metodePertemuan;

    public KelasAjar(String namaMK, int jumlahSKS, String hari, String waktuMulai, int durasi, String jenisPertemuan, String metodePertemuan) {
        this.namaMK = namaMK;
        this.jumlahSKS = jumlahSKS;
        this.hari = hari;
        this.waktuMulai = waktuMulai;
        this.durasi = durasi;
        this.jenisPertemuan = jenisPertemuan;
        this.metodePertemuan = metodePertemuan;
    }

    public String getNamaMK() {
        return namaMK;
    }

    public int getJumlahSKS() {
        return jumlahSKS;
    }

    public String getHari() {
        return hari;
    }

    public String getWaktuMulai() {
        return waktuMulai;
    }

    public int getDurasi() {
        return durasi;
    }

    public String getJenisPertemuan() {
        return jenisPertemuan;
    }

    public String getMetodePertemuan() {
        return metodePertemuan;
    }
}
