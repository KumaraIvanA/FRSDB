package hellofx.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import hellofx.kelasData.Dosen;
import hellofx.kelasData.JadwalKelas;
import hellofx.kelasData.Mahasiswa;
import hellofx.kelasData.MataKuliah;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KoneksiDB {
    private static Connection conn = null;
    
    public static Connection hubungkan() {
		// if (conn != null) {
		// 	return conn;
		// }

		String url = "jdbc:sqlserver://localhost:1433;"
					 + "database=FRS;"
					 + "user=sa;"
					 + "password=Rahasia123;"
					 + "encrypt=true;"
					 + "trustServerCertificate=true;"
					 + "loginTimeout=30;";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            conn = DriverManager.getConnection(url);
            System.out.println("Connected to the database");

        } catch (ClassNotFoundException e) {
            System.out.println("Failed to connect to the database");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database");
            e.printStackTrace();
        }

        return conn;
    }

    public static String getNamaJurusanById(int idJurusan) {
        String query = "SELECT namaJurusan FROM Jurusan WHERE idJurusan = ?";

        try (Connection c = hubungkan();
                PreparedStatement ps = c.prepareStatement(query)) {

            ps.setInt(1, idJurusan);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("namaJurusan");
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil nama jurusan");
            e.printStackTrace();
            return null;
        }
    }

    public static void insertMataKuliah(String namaMK, String namaJurusan, int jumlahSKS) {
        int idJurusan = getIdJurusanByNama(namaJurusan);
        if (idJurusan == -1) {
            System.out.println("gagal");
            return;
        }

        String query = "INSERT INTO MataKuliah (kodeMK, namaMK, idJurusan, jumlahSKS) " +
                "VALUES (NEXT VALUE FOR seq_kode_mk, ?, ?, ?)";

        try (Connection c = hubungkan();
                PreparedStatement ps = c.prepareStatement(query)) {

            ps.setString(1, namaMK);
            ps.setInt(2, idJurusan);
            ps.setInt(3, jumlahSKS);

            ps.executeUpdate();
            System.out.println("berhasil insert");

        } catch (SQLException e) {
            System.out.println("gagal insert");
            e.printStackTrace();
        }
    }

    public static int getIdJurusanByNama(String namaJurusan) {
        String query = "SELECT idJurusan FROM Jurusan WHERE namaJurusan = ?";

        try (Connection c = hubungkan();
                PreparedStatement ps = c.prepareStatement(query)) {

            ps.setString(1, namaJurusan);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("idJurusan");
            } else {
                System.out.println("Jurusan tidak ditemukan: " + namaJurusan);
                return -1;
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil id jurusan");
            e.printStackTrace();
            return -1;
        }
    }

    public static int getKodeMkByNamaMK(String namaMK) {
        String query = "SELECT kodeMK FROM MataKuliah WHERE namaMK = ?";

        try (Connection c = hubungkan();
                PreparedStatement ps = c.prepareStatement(query)) {

            ps.setString(1, namaMK);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("kodeMK");
            } else {
                System.out.println("Mata kuliah tidak ditemukan : " + namaMK);
                return -1;
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil Kode MK");
            e.printStackTrace();
            return -1;
        }
    }

    public static void insertEnroll(String npm, int kodeMK, String kodeSemester, int idFRS, String tanggalFRS) {
        String query = """
                INSERT INTO Enroll (npm, kodeMK, kodeSemester, idFRS, tanggalFRS)
                VALUES(?, ?, ?, ?, ?)
                """;

        try (Connection c = hubungkan(); PreparedStatement ps = c.prepareStatement(query)) {
            ps.setString(1, npm);
            ps.setInt(2, kodeMK);
            ps.setString(3, kodeSemester);
            ps.setInt(4, idFRS);
            ps.setString(5, tanggalFRS);

            ps.executeUpdate();
            System.out.println("Berhasil insert");
        } catch (SQLException e) {
            System.out.println("gagal menambahkan");
        }
    }

    // Mengambil isi dari tabel mata kuliah menggunakan kodeMK dari tabel Enroll
    public static String getNamaMKByKodeMK(int kodeMK) {
        String query = """
                SELECT
                    m.namaMK,
                    m.jumlahSKS,
                FROM Enroll m
                JOIN MataKuliah j ON m.kodeMK = j.kodeMK
                WHERE kodeMK = ?
                """;

        try (Connection c = hubungkan(); PreparedStatement ps = c.prepareStatement(query)) {
            ps.setInt(1, kodeMK);

            ResultSet result = ps.executeQuery();

            if (result.next()) {
                return result.getString("namaMK");
            } else {
                System.out.println("Nama MK tidak ditemukan: " + kodeMK);
                return null;
            }

        } catch (SQLException e) {
            System.out.println("gagal mencari kodeMK");
            return null;
        }
    }

    public static boolean checkLogin(String email, String password) {
        String sql = "SELECT 1 FROM Mahasiswa WHERE email = ? AND password = ?";

        try (Connection connection = hubungkan();
                PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkLoginDosen(String email, String password) {
        String sql = "SELECT 1 FROM Dosen WHERE email = ? AND password = ?";

        try (Connection connection = hubungkan();
                PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ObservableList<MataKuliah> getAllMatakuliah() {
        ObservableList<MataKuliah> list = FXCollections.observableArrayList();

        String sql = "SELECT kodeMK, namaMK, jumlahSKS, idJurusan, idSemester FROM MataKuliah ORDER BY idSemester, kodeMK";

        try (Connection conn = hubungkan();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int kodeMk = rs.getInt("kodeMK");
                String namaMK = rs.getString("namaMK");
                int jumlahSKS = rs.getInt("jumlahSKS");
                int idSemester = rs.getInt("idSemester");
                int idJurusan = rs.getInt("idJurusan");

                MataKuliah mk = new MataKuliah(kodeMk, namaMK, jumlahSKS, idJurusan, idSemester);
                list.add(mk);
            }
            ;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("gagal");
            e.printStackTrace();
        }

        return list;
    }
    
    public static ObservableList<JadwalKelas> getAllJadwal() {
        ObservableList<JadwalKelas> list = FXCollections.observableArrayList();

        String sql = "SELECT namaMK, kelas, waktuMulai, durasi, hari FROM Teaches JOIN MataKuliah ON Teaches.kodeMk = MataKuliah.kodeMk";

        try (Connection conn = hubungkan(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String namaMk = rs.getString("namaMk");
                String kelas = rs.getString("kelas");
                String waktuMulai = rs.getString("waktuMulai");
                int durasi = rs.getInt("durasi");
                String hari = rs.getString("hari");

                JadwalKelas jadwal = new JadwalKelas(namaMk, kelas, waktuMulai, durasi, hari);
                list.add(jadwal);
            }
        } catch (Exception e) {
            System.out.println("gagal");
            e.printStackTrace();
        }

        return list;
    }
    
    public static Mahasiswa getDataMahasiswa(String email) {
        String query = "SELECT nama, npm, namaJurusan FROM Mahasiswa JOIN Jurusan ON Mahasiswa.idJurusan = Jurusan.idJurusan WHERE email = ?";

        try (Connection connection = hubungkan();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String nama = rs.getString("nama");
                String npm = rs.getString("npm");
                String namaJurusan = rs.getString("namaJurusan");

                return new Mahasiswa(npm, nama, email, namaJurusan);
            } else {
                System.out.println("Data Mahasiswa Tidak Ditemukan");
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public  static Dosen getDataDosen(String email) {
        String query = "SELECT nama, nip, namaJurusan FROM Dosen JOIN Jurusan ON Dosen.idJurusan = Jurusan.idJurusan WHERE email = ?";

        try (Connection connection = hubungkan();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String nama = rs.getString("nama");
                String nip = rs.getString("nip");
                String namaJurusan = rs.getString("namaJurusan");

                return new Dosen(nip, nama, email, namaJurusan);
            } else {
                System.out.println("Data Dosen Tidak Ditemukan");
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
