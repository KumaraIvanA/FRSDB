package FRS.kelasData;

public class Semester {
    private int idSemester;
    private String tahunAjaran;
    private String jenis;

    public Semester(int idSemester, String tahunAjaran, String jenis){
        this.idSemester = idSemester;
        this.tahunAjaran = tahunAjaran;
        this.jenis = jenis;
    }

    public int getIdSemester() {
        return idSemester;
    }

    public String getTahunAjuran() {
        return tahunAjaran;
    }

    public String getJenis() {
        return jenis;
    }

    public String toString(){
        return "Semester " + tahunAjaran + " " + jenis;
    }
}
