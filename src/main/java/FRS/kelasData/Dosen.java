package FRS.kelasData;

public class Dosen {
    private String nip;
    private String nama;
    private String email;
    private String namaJurusan;

    public Dosen(String nip, String nama, String email, String namaJurusan) {
        this.nip = nip;
        this.nama = nama;
        this.email = email;
        this.namaJurusan = namaJurusan;
    }

    public String getNip() {
        return nip;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

    public String getNamaJurusan() {
        return namaJurusan;
    }
}
