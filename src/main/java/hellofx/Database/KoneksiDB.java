package hellofx.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import hellofx.halaman.HalamanMahasiswa.HalamanFRS;
import hellofx.kelasData.Mahasiswa;
import hellofx.kelasData.MataKuliah;
import hellofx.kelasData.Semester;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KoneksiDB {
    private static Connection conn = null;

    public static Connection hubungkan() {
        // if (conn != null) {
        // return conn;
        // }

        String url = "jdbc:sqlserver://localhost:1433;"
                + "database=FRS;"
                + "user=sa;"
                + "password=passowrd;"
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

    // public static void insertEnroll(String npm, int kodeMK, String kodeSemester,
    // int idFRS, String tanggalFRS) {
    // String query = """
    // INSERT INTO Enroll (npm, kodeMK, kodeSemester, idFRS, tanggalFRS)
    // VALUES(?, ?, ?, ?, ?)
    // """;

    // try (Connection c = hubungkan(); PreparedStatement ps =
    // c.prepareStatement(query)) {
    // ps.setString(1, npm);
    // ps.setInt(2, kodeMK);
    // ps.setString(3, kodeSemester);
    // ps.setInt(4, idFRS);
    // ps.setString(5, tanggalFRS);

    // ps.executeUpdate();
    // System.out.println("Berhasil insert");
    // } catch (SQLException e) {
    // System.out.println("gagal menambahkan");
    // }
    // }

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
            System.out.println("Server : " + conn.getMetaData().getURL());
            System.out.println("DB     : " + conn.getCatalog());

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
                System.out.println("Mahasiswa Tidak Ditemukan");
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Integer getIdSemesterFromNamaMK(String namaMK) {
        String query = "SELECT idSemester FROM Matakuliah WHERE namaMK = ?";

        try (Connection connection = hubungkan();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, namaMK);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idSemester = rs.getInt("idSemester");
                return idSemester;
            } else {
                System.out.println("Tidak ada Matakuliah tersebut");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ObservableList<Semester> getAllSemester() {
        ObservableList<Semester> list = FXCollections.observableArrayList();

        String sql = """
                    SELECT idSemester, tahunAjaran, jenis
                    FROM Semester
                    ORDER BY tahunAjaran,
                             CASE
                                WHEN jenis = 'Ganjil' THEN 1
                                WHEN jenis = 'Genap' THEN 2
                                ELSE 3
                             END
                """;

        try (Connection conn = hubungkan();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idSemester = rs.getInt("idSemester");
                String tahunAjaran = rs.getString("tahunAjaran");
                String jenis = rs.getString("jenis");

                list.add(new Semester(idSemester, tahunAjaran, jenis));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static int makeNewIdFRS(int idSemester) {
        String query = "INSERT INTO FRS (idSemester) VALUES (?)";

        int idFRS = -1;
        try (Connection conn = hubungkan();
                PreparedStatement ps = conn.prepareStatement(query, java.sql.Statement.RETURN_GENERATED_KEYS);) {

            ps.setInt(1, idSemester);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    idFRS = rs.getInt(1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return idFRS;
    }

    public static Integer getIdSemester(String tahunAjaran, String jenis) {
        String query = "SELECT idSemester FROM Semester WHERE tahunAjaran = ? & jenis = ?";

        try (Connection conn = hubungkan();
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                int idSemester = rs.getInt("idSemester");
                return idSemester;
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void isiDataEnroll(ArrayList<HalamanFRS.Course> selectedCourse, String npm, int idSemester,
            int idFRS) {
        String query = "INSERT INTO Enroll (npm, kodeMK, idSemester, idFRS, tanggalFRS) VALUES " +
                "(? , ? , ? , ? , ?)";

        try (Connection conn = hubungkan(); PreparedStatement ps = conn.prepareStatement(query)) {

            for (HalamanFRS.Course c : selectedCourse) {
                ps.setString(1, npm);
                ps.setInt(2, c.getKodeMK());
                ps.setInt(3, idSemester);
                ps.setInt(4, idFRS);
                ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
                ps.addBatch();
            }

            ps.executeBatch();
            System.out.println("Berhasil insert");
        } catch (SQLException e) {
            System.out.println("gagal menambahkan");
        }
    }
}
