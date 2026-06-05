INSERT INTO Jurusan (namaJurusan)
VALUES
	('Informatika'),
	('Fisika'),
	('Matematika')

INSERT INTO Semester (tahunAjaran, jenis)
VALUES
	('2023/2024', 'ganjil'),
	('2023/2024', 'genap'),
	('2024/2025', 'ganjil'),
	('2024/2025', 'genap')

INSERT INTO Mahasiswa
VALUES
	('618240101', 'example', 'examplestudent@gmail.com', 'example', 1)

INSERT INTO Dosen
VALUES
	('196502201992031002', 'example', 'exampledosen@gmail.com', 'example', 2)


INSERT INTO LogAktivitas (npm, tanggalWaktu, aktivitas)
VALUES
	('618240101', '2026-05-29 18:30:15', 'Isi FRS')

INSERT INTO FRS (idSemester)
VALUES (4)

INSERT INTO MataKuliah (namaMK, jumlahSKS, idJurusan, idSemester)
VALUES
	('Manajemen Informasi dan Basis Data', 4, 1, 4),
	('Analisis Data dengan Statistika dan R', 3, 1, 4),
	('Analisis dan Desain Perangkat Lunak', 2, 1, 4)


INSERT INTO Enroll
VALUES
	('618240101', 1, 4, 1, '2026-05-29 18:30:15'),
	('618240101', 2, 4, 1, '2026-05-29 18:30:15'),
	('618240101', 3, 4, 1, '2026-05-29 18:30:15')


INSERT INTO Teaches
VALUES
	(4, 1, '196502201992031002', 'A', '18:00:00', 90, 'senin', 'Daring', 'Praktikum')

INSERT INTO Teaches
VALUES
	(4, 2, '196502201992031002', 'A', '15:00:00', 90, 'senin', 'Daring', 'Praktikum')

INSERT INTO Teaches
VALUES
	(4, 2, '196502201992031002', 'A', '15:00:00', 90, 'rabu', 'Daring', 'Praktikum')