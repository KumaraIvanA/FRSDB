package hellofx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KoneksiDB {

    public static Connection hubungkan() {
        Connection conn = null;

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
            System.out.println("Berhasil");

        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC mssql tidak ditemukan! Periksa kembali pom.xml");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Gagal koneksi! Periksa apakah SQL Server sudah aktif / password salah.");
            e.printStackTrace();
        }

        return conn;
    }

    public static ObservableList<MataKuliah> getAllMataKuliah() {
        ObservableList<MataKuliah> list = FXCollections.observableArrayList();

        String query = """
                SELECT
                    m.namaMK,
                    j.namaJurusan,
                    m.jumlahSKS
                FROM MataKuliah m
                JOIN Jurusan j ON m.idJurusan = j.idJurusan
                """;

        try (Connection c = hubungkan();
                Statement stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String nama = rs.getString("namaMK");
                String jurusan = rs.getString("namaJurusan");
                int jumlahSKS = rs.getInt("jumlahSKS");

                list.add(new MataKuliah(nama, jurusan, jumlahSKS));
            }

        } catch (SQLException e) {
            System.out.println("gagal mengambil data");
            e.printStackTrace();
        }

        return list;
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
}
