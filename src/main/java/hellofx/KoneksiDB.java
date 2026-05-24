package hellofx;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KoneksiDB {

    public static Connection hubungkan() {
        Connection conn = null;

       String url = "jdbc:sqlserver://localhost:1433;"
                + "database=Testing;"
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

        String query = "SELECT Nama_MK, Jurusan, jumlahSKS FROM MATA_KULIAH";

        try {
            Connection c = hubungkan();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String nama = rs.getString("Nama_mk");
                String jurusan = rs.getString("Jurusan");
                int jumlahSKS = rs.getInt("jumlahSKS");

                list.add(new MataKuliah(nama, jurusan, jumlahSKS));
            }
        } catch (SQLException e) {
            System.out.println("gagal mengambil data");
            e.printStackTrace();
        }

        return list;
    }
    
    public static void insertMataKuliah(String namaMK, String jurusan, int jumlahSKS) {
        String query = "INSERT INTO MATA_KULIAH (Nama_MK, Jurusan, JumlahSKS) VALUES(?, ?, ?)";

        try {
            Connection c = hubungkan();
            PreparedStatement ps = c.prepareStatement(query);

            ps.setString(1, namaMK);
            ps.setString(2, jurusan);
            ps.setInt(3, jumlahSKS);

            ps.executeUpdate();
            System.out.println("berhasil insert");
        } catch (SQLException e) {
            System.out.println("gagal insert");
            e.printStackTrace();
        }
    }
}
