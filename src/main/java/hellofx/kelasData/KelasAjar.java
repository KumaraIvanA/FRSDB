package hellofx.kelasData;

public class KelasAjar {
    private String namaMK;
    private int jumlahSKS;
    private String kelas;
    private String hari;
    private String waktuMulai;
    private int durasi;
    private String jenisPertemuan;
    private String metodePertemuan;

    public KelasAjar(String namaMK, int jumlahSKS, String kelas, String hari, String waktuMulai, int durasi, String jenisPertemuan, String metodePertemuan) {
        this.namaMK = namaMK;
        this.jumlahSKS = jumlahSKS;
        this.kelas = kelas;
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

    public String getKelas() {
        return kelas;
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
