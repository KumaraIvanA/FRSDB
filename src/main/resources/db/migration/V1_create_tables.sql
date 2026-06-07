DROP TABLE IF EXISTS Teaches
DROP TABLE IF EXISTS Enroll
DROP TABLE IF EXISTS LogAktivitas
DROP TABLE IF EXISTS MataKuliah
DROP TABLE IF EXISTS FRS
DROP TABLE IF EXISTS Dosen
DROP TABLE IF EXISTS Mahasiswa
DROP TABLE IF EXISTS Semester
DROP TABLE IF EXISTS Jurusan


CREATE TABLE Jurusan
(
	idJurusan int IDENTITY(1, 1) PRIMARY KEY, 
	namaJurusan varchar(75) NOT NULL
)


CREATE TABLE Semester
(
	idSemester int IDENTITY(1, 1) NOT NULL PRIMARY KEY,
	tahunAjaran char(9) NOT NULL,
	jenis varchar(6) NOT NULL
)


CREATE TABLE Mahasiswa
(
	npm char(10) NOT NULL PRIMARY KEY,
	nama varchar(100) NOT NULL,
	email varchar(100) UNIQUE NOT NULL,
	password varchar(100) NOT NULL,
	idJurusan int FOREIGN KEY REFERENCES Jurusan (idJurusan) NOT NULL
)


CREATE TABLE Dosen
(
	nip char(18) NOT NULL PRIMARY KEY,
	nama varchar(100) NOT NULL,
	email varchar(100) UNIQUE NOT NULL,
	password varchar(100) NOT NULL,
	idJurusan int FOREIGN KEY REFERENCES Jurusan (idJurusan) NOT NULL
)


CREATE TABLE LogAktivitas(
	idLog int IDENTITY(1, 1) NOT NULL PRIMARY KEY,
	npm char(10) FOREIGN KEY REFERENCES Mahasiswa(npm) NOT NULL,
	tanggalWaktu datetime NOT NULL,
	aktivitas varchar(255) NOT NULL,
)


CREATE TABLE FRS
(
	idFRS int IDENTITY(1, 1) NOT NULL PRIMARY KEY,
	idSemester int FOREIGN KEY REFERENCES Semester (idSemester) NOT NULL,
)


CREATE TABLE MataKuliah
(
	kodeMK int IDENTITY(1, 1) NOT NULL PRIMARY KEY,
	namaMK varchar(75) NOT NULL,
	jumlahSKS int NOT NULL,
	idJurusan int FOREIGN KEY REFERENCES Jurusan (idJurusan) NOT NULL,
	idSemester int FOREIGN KEY REFERENCES Semester (idSemester) NOT NULL
)


CREATE TABLE Enroll 
(
    npm char(10) FOREIGN KEY REFERENCES Mahasiswa (npm) NOT NULL,
    kodeMK int FOREIGN KEY REFERENCES MataKuliah (kodeMK) NOT NULL,
	idSemester int FOREIGN KEY REFERENCES Semester (idSemester) NOT NULL,
    idFRS int FOREIGN KEY REFERENCES FRS (idFRS) NOT NULL,
    tanggalFRS datetime NOT NULL
)


CREATE TABLE Teaches
(
	idSemester int FOREIGN KEY REFERENCES Semester (idSemester) NOT NULL,
    kodeMK int FOREIGN KEY REFERENCES MataKuliah (kodeMK) NOT NULL,
    nip char(18) FOREIGN KEY REFERENCES Dosen (nip) NOT NULL,
    kelas varchar(10) NOT NULL,
	waktuMulai time,
    durasi int NULL,
	hari varchar(10),
    jenisPertemuan varchar(50) NULL,
    metodePertemuan varchar(50) NULL,
	PRIMARY KEY (idSemester, kodeMK, nip, hari)
)