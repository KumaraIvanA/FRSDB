-- ============================================================
--  MASTER DATA
-- ============================================================

-- ----- Jurusan (5) -> id 1..5 -----
INSERT INTO Jurusan (namaJurusan)
VALUES
	('Informatika'),   -- 1
	('Matematika'),    -- 2
	('Fisika'),        -- 3
	('Kimia'),         -- 4
	('Biologi')        -- 5

-- ----- Semester (5) -> id 1..5 -----
INSERT INTO Semester (tahunAjaran, jenis)
VALUES
	('2022/2023', 'ganjil'),   -- 1
	('2022/2023', 'genap'),    -- 2
	('2023/2024', 'ganjil'),   -- 3  <-- semester aktif (dipakai FRS/Enroll/Teaches)
	('2023/2024', 'genap'),    -- 4
	('2024/2025', 'ganjil')    -- 5

-- ----- Mahasiswa (5) : 1 per jurusan -----
INSERT INTO Mahasiswa (npm, nama, email, password, idJurusan)
VALUES
	('2301010001', 'Budi Santoso',    'budi.santoso@student.ac.id',  'pass_budi01',  1),
	('2301010002', 'Siti Nurhaliza',  'siti.nur@student.ac.id',      'pass_siti02',  2),
	('2301010003', 'Ahmad Fauzi',     'ahmad.fauzi@student.ac.id',   'pass_ahmad03', 3),
	('2301010004', 'Dewi Lestari',    'dewi.lestari@student.ac.id',  'pass_dewi04',  4),
	('2301010005', 'Eko Prasetyo',    'eko.prasetyo@student.ac.id',  'pass_eko05',   5)

-- ----- Dosen (5) : 1 per jurusan -----
INSERT INTO Dosen (nip, nama, email, password, idJurusan)
VALUES
	('198001011998021001', 'Dr. Bambang Wijaya',  'bambang.w@dosen.ac.id', 'pass_dosen01', 1),
	('198203152005012002', 'Dr. Maya Sari',       'maya.sari@dosen.ac.id', 'pass_dosen02', 2),
	('197905202003121003', 'Dr. Hendra Gunawan',  'hendra.g@dosen.ac.id',  'pass_dosen03', 3),
	('198506102010012004', 'Dr. Rina Marlina',    'rina.m@dosen.ac.id',    'pass_dosen04', 4),
	('198711252012011005', 'Dr. Agus Salim',      'agus.salim@dosen.ac.id','pass_dosen05', 5)

-- ----- FRS (5) : 1 per mahasiswa, semester aktif = 3 -> id 1..5 -----
INSERT INTO FRS (idSemester)
VALUES
	(3),   -- FRS 1 (Budi)
	(3),   -- FRS 2 (Siti)
	(3),   -- FRS 3 (Ahmad)
	(3),   -- FRS 4 (Dewi)
	(3)    -- FRS 5 (Eko)

-- ----- MataKuliah (6) : semua di semester aktif = 3 -> kode 1..6 -----
INSERT INTO MataKuliah (namaMK, jumlahSKS, idJurusan, idSemester)
VALUES
	('Matematika Dasar',   4, 2, 3),   -- 1
	('Fisika Dasar',       4, 3, 3),   -- 2
	('Kimia Dasar',        4, 4, 3),   -- 3
	('Biologi Umum',       3, 5, 3),   -- 4
	('Dasar Pemrograman',  4, 1, 3),   -- 5
	('Logika Informatika', 3, 1, 3)    -- 6


-- ============================================================
--  RELATIONAL DATA
-- ============================================================

-- ----- Enroll (30) : tiap mahasiswa ambil 6 MK (5 x 6 = 30) -----
INSERT INTO Enroll (npm, kodeMK, idSemester, idFRS, tanggalFRS)
VALUES
	-- Budi (FRS 1)
	('2301010001', 1, 3, 1, '2023-07-20 08:15:00'),
	('2301010001', 2, 3, 1, '2023-07-20 08:15:00'),
	('2301010001', 3, 3, 1, '2023-07-20 08:15:00'),
	('2301010001', 4, 3, 1, '2023-07-20 08:15:00'),
	('2301010001', 5, 3, 1, '2023-07-20 08:15:00'),
	('2301010001', 6, 3, 1, '2023-07-20 08:15:00'),
	-- Siti (FRS 2)
	('2301010002', 1, 3, 2, '2023-07-20 09:30:00'),
	('2301010002', 2, 3, 2, '2023-07-20 09:30:00'),
	('2301010002', 3, 3, 2, '2023-07-20 09:30:00'),
	('2301010002', 4, 3, 2, '2023-07-20 09:30:00'),
	('2301010002', 5, 3, 2, '2023-07-20 09:30:00'),
	('2301010002', 6, 3, 2, '2023-07-20 09:30:00'),
	-- Ahmad (FRS 3)
	('2301010003', 1, 3, 3, '2023-07-21 10:00:00'),
	('2301010003', 2, 3, 3, '2023-07-21 10:00:00'),
	('2301010003', 3, 3, 3, '2023-07-21 10:00:00'),
	('2301010003', 4, 3, 3, '2023-07-21 10:00:00'),
	('2301010003', 5, 3, 3, '2023-07-21 10:00:00'),
	('2301010003', 6, 3, 3, '2023-07-21 10:00:00'),
	-- Dewi (FRS 4)
	('2301010004', 1, 3, 4, '2023-07-21 13:45:00'),
	('2301010004', 2, 3, 4, '2023-07-21 13:45:00'),
	('2301010004', 3, 3, 4, '2023-07-21 13:45:00'),
	('2301010004', 4, 3, 4, '2023-07-21 13:45:00'),
	('2301010004', 5, 3, 4, '2023-07-21 13:45:00'),
	('2301010004', 6, 3, 4, '2023-07-21 13:45:00'),
	-- Eko (FRS 5)
	('2301010005', 1, 3, 5, '2023-07-22 11:20:00'),
	('2301010005', 2, 3, 5, '2023-07-22 11:20:00'),
	('2301010005', 3, 3, 5, '2023-07-22 11:20:00'),
	('2301010005', 4, 3, 5, '2023-07-22 11:20:00'),
	('2301010005', 5, 3, 5, '2023-07-22 11:20:00'),
	('2301010005', 6, 3, 5, '2023-07-22 11:20:00')

-- ----- Teaches (30) : tiap MK diajar 1 dosen (sesuai jurusan), -----
-- ----- kelas A, Senin-Jumat, jam tetap per MK (tanpa bentrok)  -----
INSERT INTO Teaches (idSemester, kodeMK, nip, kelas, waktuMulai, durasi, hari, jenisPertemuan, metodePertemuan)
VALUES
	-- MK1 Matematika Dasar - Dr. Maya Sari - 07:30
	(3, 1, '198203152005012002', 'A', '07:30:00', 90, 'Senin',  'Kuliah',    'Luring'),
	(3, 1, '198203152005012002', 'A', '07:30:00', 90, 'Selasa', 'Kuliah',    'Luring'),
	(3, 1, '198203152005012002', 'A', '07:30:00', 90, 'Rabu',   'Kuliah',    'Daring'),
	(3, 1, '198203152005012002', 'A', '07:30:00', 90, 'Kamis',  'Kuliah',    'Luring'),
	(3, 1, '198203152005012002', 'A', '07:30:00', 90, 'Jumat',  'Praktikum', 'Luring'),
	-- MK2 Fisika Dasar - Dr. Hendra Gunawan - 09:30
	(3, 2, '197905202003121003', 'A', '09:30:00', 90, 'Senin',  'Kuliah',    'Luring'),
	(3, 2, '197905202003121003', 'A', '09:30:00', 90, 'Selasa', 'Kuliah',    'Luring'),
	(3, 2, '197905202003121003', 'A', '09:30:00', 90, 'Rabu',   'Kuliah',    'Daring'),
	(3, 2, '197905202003121003', 'A', '09:30:00', 90, 'Kamis',  'Kuliah',    'Luring'),
	(3, 2, '197905202003121003', 'A', '09:30:00', 90, 'Jumat',  'Praktikum', 'Luring'),
	-- MK3 Kimia Dasar - Dr. Rina Marlina - 11:30
	(3, 3, '198506102010012004', 'A', '11:30:00', 90, 'Senin',  'Kuliah',    'Luring'),
	(3, 3, '198506102010012004', 'A', '11:30:00', 90, 'Selasa', 'Kuliah',    'Luring'),
	(3, 3, '198506102010012004', 'A', '11:30:00', 90, 'Rabu',   'Kuliah',    'Daring'),
	(3, 3, '198506102010012004', 'A', '11:30:00', 90, 'Kamis',  'Kuliah',    'Luring'),
	(3, 3, '198506102010012004', 'A', '11:30:00', 90, 'Jumat',  'Praktikum', 'Luring'),
	-- MK4 Biologi Umum - Dr. Agus Salim - 13:30
	(3, 4, '198711252012011005', 'A', '13:30:00', 90, 'Senin',  'Kuliah',    'Luring'),
	(3, 4, '198711252012011005', 'A', '13:30:00', 90, 'Selasa', 'Kuliah',    'Luring'),
	(3, 4, '198711252012011005', 'A', '13:30:00', 90, 'Rabu',   'Kuliah',    'Daring'),
	(3, 4, '198711252012011005', 'A', '13:30:00', 90, 'Kamis',  'Kuliah',    'Luring'),
	(3, 4, '198711252012011005', 'A', '13:30:00', 90, 'Jumat',  'Praktikum', 'Luring'),
	-- MK5 Dasar Pemrograman - Dr. Bambang Wijaya - 15:30
	(3, 5, '198001011998021001', 'A', '15:30:00', 90, 'Senin',  'Kuliah',    'Luring'),
	(3, 5, '198001011998021001', 'A', '15:30:00', 90, 'Selasa', 'Kuliah',    'Luring'),
	(3, 5, '198001011998021001', 'A', '15:30:00', 90, 'Rabu',   'Kuliah',    'Daring'),
	(3, 5, '198001011998021001', 'A', '15:30:00', 90, 'Kamis',  'Kuliah',    'Luring'),
	(3, 5, '198001011998021001', 'A', '15:30:00', 90, 'Jumat',  'Praktikum', 'Luring'),
	-- MK6 Logika Informatika - Dr. Bambang Wijaya - 17:30
	(3, 6, '198001011998021001', 'A', '17:30:00', 90, 'Senin',  'Kuliah',    'Luring'),
	(3, 6, '198001011998021001', 'A', '17:30:00', 90, 'Selasa', 'Kuliah',    'Luring'),
	(3, 6, '198001011998021001', 'A', '17:30:00', 90, 'Rabu',   'Kuliah',    'Daring'),
	(3, 6, '198001011998021001', 'A', '17:30:00', 90, 'Kamis',  'Kuliah',    'Luring'),
	(3, 6, '198001011998021001', 'A', '17:30:00', 90, 'Jumat',  'Praktikum', 'Luring')