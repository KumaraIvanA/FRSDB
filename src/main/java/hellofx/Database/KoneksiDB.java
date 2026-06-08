package hellofx.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import hellofx.halaman.HalamanMahasiswa.HalamanFRS;
import hellofx.kelasData.Dosen;
import hellofx.kelasData.FRS;
import hellofx.kelasData.JadwalKelas;
import hellofx.kelasData.KelasAjar;
import hellofx.kelasData.Mahasiswa;
import hellofx.kelasData.MataKuliah;
import hellofx.kelasData.Semester;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KoneksiDB {
    private static Connection conn = null;

    public static Connection hubungkan() {
        if (conn != null) {
            return conn;
        }

        String url = "jdbc:sqlserver://localhost:1433;"
					 + "database=FRS;"
					 + "user=sa;"
					 + "password=Rahasia123;"
					 + "encrypt=true;"
					 + "trustServerCertificate=true;"
					 + "loginTimeout=30;";

        try {
            System.out.println("Mencoba konek...");
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

	public static void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("Failed to close the database connection");
			e.printStackTrace();
		}
	}

	public static String getNamaJurusanById(int idJurusan) {
		String query = "SELECT namaJurusan FROM Jurusan WHERE idJurusan = ?";

		try {
			Connection c = hubungkan();
			PreparedStatement ps = c.prepareStatement(query);
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

        try  {
			Connection c = hubungkan();
            PreparedStatement ps = c.prepareStatement(query);

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

        try  {
			Connection c = hubungkan();
            PreparedStatement ps = c.prepareStatement(query);

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

        try {
            Connection c = hubungkan();
            PreparedStatement ps = c.prepareStatement(query);
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

        try  {
			Connection c = hubungkan();
			PreparedStatement ps = c.prepareStatement(query);
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

	public static boolean checkLogin(String email, String password) {
        String sql = "SELECT 1 FROM Mahasiswa WHERE email = ? AND password = ?";

        try  {
			Connection connection = hubungkan();
            PreparedStatement ps = connection.prepareStatement(sql);

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

        try  {
			Connection connection = hubungkan();
            PreparedStatement ps = connection.prepareStatement(sql);

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

	public static ObservableList<MataKuliah> getAllMatakuliahBerdasarkanIdJurusan(int idJ) {
		ObservableList<MataKuliah> list = FXCollections.observableArrayList();

        String sql = "SELECT MatakuliahTerbuka.kodeMK, MataKuliah.namaMK, MataKuliah.jumlahSKS, MataKuliah.idJurusan, MatakuliahTerbuka.idSemester, MataKuliah.nip\r\n" + //
                     "FROM MataKuliah\r\n" + //
                     "JOIN MatakuliahTerbuka ON MatakuliahTerbuka.kodeMK = MataKuliah.kodeMK\r\n" + //
                     "JOIN Semester ON Semester.idSemester = MatakuliahTerbuka.idSemester\r\n" + //
                     "WHERE MataKuliah.idJurusan = ?";

		try {
			Connection conn = hubungkan();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idJ);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int kodeMk = rs.getInt("kodeMK");
				String namaMK = rs.getString("namaMK");
				int jumlahSKS = rs.getInt("jumlahSKS");
				int idSemester = rs.getInt("idSemester");
				int idJurusan = idJ;
				String nip = rs.getString("nip");

				MataKuliah mk = new MataKuliah(kodeMk, namaMK, jumlahSKS, idJurusan, idSemester, nip);
				list.add(mk);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("gagal");
			e.printStackTrace();
		}

		return list;
	}


    public static ObservableList<JadwalKelas> getAllJadwal(int idSemester) {
        ObservableList<JadwalKelas> list = FXCollections.observableArrayList();

        String sql = "SELECT namaMK, waktuMulai, durasi, hari, metodePertemuan FROM Teaches JOIN MataKuliah ON Teaches.kodeMk = MataKuliah.kodeMk WHERE Teaches.idSemester = ?";

        try {
            Connection conn = hubungkan();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idSemester);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String namaMk = rs.getString("namaMk");
                String waktuMulai = rs.getString("waktuMulai");
                int durasi = rs.getInt("durasi");
                String hari = rs.getString("hari");
                String metodePertemuan = rs.getString("metodePertemuan");

                JadwalKelas jadwal = new JadwalKelas(namaMk, waktuMulai, durasi, hari, metodePertemuan);
                list.add(jadwal);
            }
        } catch (Exception e) {
            System.out.println("gagal");
            e.printStackTrace();
        }

        return list;
    }

	public static ObservableList<JadwalKelas> getJadwalMahasiswa(String npm, int idSemester) {
        ObservableList<JadwalKelas> list = FXCollections.observableArrayList();

        String sql = "SELECT \r\n" + //
                        "    MataKuliah.namaMK, Teaches.waktuMulai, Teaches.durasi, Teaches.hari, Teaches.metodePertemuan\r\n" + //
                        "FROM \r\n" + //
                        "(\r\n" + //
                        "    SELECT \r\n" + //
                        "        MAX(Enroll.idFRS) as maxIdFrs\r\n" + //
                        "    FROM \r\n" + //
                        "        Enroll\r\n" + //
                        "    JOIN Mahasiswa ON Enroll.npm = Mahasiswa.npm\r\n" + //
                        "    WHERE Mahasiswa.npm = ? AND Enroll.idSemester = ?\r\n" + //
                        ") as maxidFrs\r\n" + //
                        "JOIN Enroll ON Enroll.idFRS = maxidFrs.maxIdFrs\r\n" + //
                        "JOIN MataKuliah ON MataKuliah.kodeMK = Enroll.kodeMK \r\n" + //
                        "JOIN Teaches ON Teaches.kodeMK = Enroll.kodeMK";

        try {
            Connection conn = hubungkan();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, npm);
            ps.setInt(2, idSemester);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String namaMk = rs.getString("namaMk");
                String waktuMulai = rs.getString("waktuMulai");
                int durasi = rs.getInt("durasi");
                String hari = rs.getString("hari");
                String metodePertemuan = rs.getString("metodePertemuan");

                JadwalKelas jadwal = new JadwalKelas(namaMk, waktuMulai, durasi, hari, metodePertemuan);
                list.add(jadwal);
            }
        } catch (Exception e) {
            System.out.println("gagal");
            e.printStackTrace();
        }

        return list;
    }

	public static Mahasiswa getDataMahasiswa(String email) {
        String query = "SELECT nama, npm, Mahasiswa.idJurusan FROM Mahasiswa JOIN Jurusan ON Mahasiswa.idJurusan = Jurusan.idJurusan WHERE email = ?";

        try {
            Connection connection = hubungkan();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String nama = rs.getString("nama");
                String npm = rs.getString("npm");
                int idJurusan = rs.getInt("idJurusan");

                return new Mahasiswa(npm, nama, email, idJurusan);
            } else {
                System.out.println("Data Mahasiswa Tidak Ditemukan");
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

	public static Dosen getDataDosen(String email) {
        String query = "SELECT nama, nip, namaJurusan FROM Dosen JOIN Jurusan ON Dosen.idJurusan = Jurusan.idJurusan WHERE email = ?";

        try {
            Connection connection = hubungkan();
            PreparedStatement ps = connection.prepareStatement(query);
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

	public static ObservableList<Semester> getAllSemester() {
        ObservableList<Semester> list = FXCollections.observableArrayList();

        String sql = """
                    SELECT
                        idSemester, tahunAjaran, jenis
                    FROM
                        Semester
                """;

        try {
            Connection conn = hubungkan();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

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
        String query = "INSERT INTO FRS DEFAULT VALUES";

        int idFRS = -1;
        try {
            Connection conn = hubungkan();
            PreparedStatement ps = conn.prepareStatement(query, java.sql.Statement.RETURN_GENERATED_KEYS);

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
        String query = "SELECT idSemester FROM Semester WHERE tahunAjaran = ? AND jenis = ?";

        try {
            Connection conn = hubungkan();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, tahunAjaran);
            ps.setString(2, jenis);

            ResultSet rs = ps.executeQuery();

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
        String query = "INSERT INTO Enroll (npm, kodeMK, idSemester, idFRS, tanggalFRS) VALUES "
					   + "(? , ? , ? , ? , ?)";

        try {
            Connection conn = hubungkan();
            PreparedStatement ps = conn.prepareStatement(query);

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

	public static ObservableList<JadwalKelas> getJadwalDosen(int idSemester, String nip) {
        ObservableList<JadwalKelas> list = FXCollections.observableArrayList();

        String sql = """
                SELECT
                    mk.namaMK,
                    t.hari,
                    t.waktuMulai,
                    t.durasi,
                    t.metodePertemuan
                FROM 
                    Teaches t
                JOIN 
                    MataKuliah mk
                ON 
                    t.kodeMK = mk.kodeMK
                WHERE
                    t.idSemester = ? AND t.nip = ?
                ORDER BY
                    t.hari, t.waktuMulai
                """;

        try {
            Connection conn = hubungkan();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, idSemester);
            ps.setString(2, nip);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                JadwalKelas jadwal = new JadwalKelas(
                    rs.getString("namaMK"),
                    rs.getString("waktuMulai"),
                    rs.getInt("durasi"),
                    rs.getString("hari"),
                    rs.getString("metodePertemuan"));

                list.add(jadwal);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

	public static ArrayList<KelasAjar> getKelasAjarByDosen(String nip, int idSemester) {
        ArrayList<KelasAjar> daftarKelas = new ArrayList<>();

        String query = """
                SELECT
                    mk.namaMK,
                    mk.jumlahSKS,
                    t.hari,
                    t.waktuMulai,
                    t.durasi,
                    t.jenisPertemuan,
                    t.metodePertemuan
                FROM
                    Teaches t
                JOIN
                    MataKuliah mk
                ON
                    t.kodeMK = mk.kodeMK
                WHERE
                    t.nip = ? AND t.idSemester = ?
                ORDER BY
                    t.hari, t.waktuMulai
                """;

        try {
            Connection conn = hubungkan();
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, nip);
            ps.setInt(2, idSemester);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                KelasAjar kelas = new KelasAjar(
                    rs.getString("namaMK"),
                    rs.getInt("jumlahSKS"),
                    rs.getString("hari"),
                    rs.getString("waktuMulai"),
                    rs.getInt("durasi"),
                    rs.getString("jenisPertemuan"),
                    rs.getString("metodePertemuan"));

                daftarKelas.add(kelas);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return daftarKelas;
    }


	public static List<FRS> getFRS(String npm, int idSemester) {
        List<FRS> list = new ArrayList<>();

		String SQL = """
		SELECT
			Enroll.idFRS as idFRS,
			Enroll.tanggalFRS as tanggalFRS,
			MataKuliah.kodeMK as kodeMK,
			MataKuliah.namaMK as namaMK,
			MataKuliah.jumlahSKS as jumlahSKS,
			MataKuliah.idJurusan as idJurusan,
			MataKuliah.nip as nip,
			Enroll.idSemester as idSemester
		FROM
			Enroll JOIN MataKuliah ON Enroll.kodeMK = MataKuliah.kodeMK
		WHERE
			Enroll.npm = ? AND Enroll.idSemester = ?
		""";

		try {
			Connection conn = hubungkan();
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, npm);
			ps.setInt(2, idSemester);

			ResultSet rs = ps.executeQuery();
			FRS currentFRS = null;
			while (rs.next()) {
				int currentFRSID = rs.getInt("idFRS");
				LocalDateTime localDateTime = rs.getObject("tanggalFRS", LocalDateTime.class);

				if (currentFRS == null || currentFRS.getID() != currentFRSID) {
					if (currentFRS != null) {
						list.add(currentFRS);
					}

					currentFRS = new FRS(currentFRSID, localDateTime);
				}

				MataKuliah mk = new MataKuliah(
					rs.getInt("kodeMK"),
					rs.getString("namaMK"),
					rs.getInt("jumlahSKS"),
					rs.getInt("idJurusan"),
					rs.getInt("idSemester"),
					rs.getString("nip")
				);

				currentFRS.addMataKuliah(mk);
			}

			if (currentFRS != null) {
				list.add(currentFRS);
			}
		} catch (SQLException e) {
            e.printStackTrace();
		}

		return list;
	}

	public static boolean deleteEnrollment(int idFRS, int kodeMK) {
		String sql = "DELETE FROM Enroll WHERE idFRS = ? AND kodeMK = ?";

		try {
			Connection conn = hubungkan();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idFRS);
			ps.setInt(2, kodeMK);

			return (ps.executeUpdate() == 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public static List<MataKuliah> getMataKuliahBySemesterJurusan(int idSemester, int idJurusan) {
		List<MataKuliah> list = new ArrayList<MataKuliah>();

		String sql = """
			SELECT
				MataKuliah.kodeMK,
				MataKuliah.namaMK,
				MataKuliah.jumlahSKS,
				MataKuliah.idJurusan,
				MataKuliah.nip
			FROM
				MatakuliahTerbuka JOIN MataKuliah ON MataKuliahTerbuka.kodeMK = MataKuliah.kodeMK
			WHERE
				MatakuliahTerbuka.idSemester = ? AND MataKuliah.idJurusan = ?
			""";

		try {
			Connection conn = hubungkan();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idSemester);
			ps.setInt(2, idJurusan);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new MataKuliah(
					rs.getInt("kodeMK"),
					rs.getString("namaMk"),
					rs.getInt("jumlahSKS"),
					rs.getInt("idJurusan"),
					idSemester,
					rs.getString("nip")
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
}
