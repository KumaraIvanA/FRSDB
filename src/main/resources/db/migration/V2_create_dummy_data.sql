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
	('2301010001', 'Budi Santoso',    'budi.santoso@student.ac.id',  'budi',  1),
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
INSERT INTO MataKuliah (namaMK, jumlahSKS, idJurusan)
VALUES
    -- =====================
    -- SEMESTER 1
    -- =====================

    -- Informatika
    ('Dasar Pemrograman', 4, 1),
    ('Pengantar Teknologi Informasi', 3, 1),
    ('Logika Informatika', 3, 1),
    ('Matematika Diskret', 3, 1),
    ('Sistem Digital', 3, 1),

    -- Matematika
    ('Kalkulus Dasar', 4, 2),
    ('Aljabar Linear Dasar', 3, 2),
    ('Logika Matematika', 3, 2),
    ('Pengantar Statistika', 3, 2),
    ('Geometri Dasar', 3, 2),

    -- Fisika
    ('Fisika Dasar I', 4, 3),
    ('Mekanika Dasar', 3, 3),
    ('Praktikum Fisika Dasar I', 2, 3),
    ('Matematika Fisika Dasar', 3, 3),
    ('Pengantar Ilmu Fisika', 3, 3),

    -- Kimia
    ('Kimia Dasar I', 4, 4),
    ('Praktikum Kimia Dasar I', 2, 4),
    ('Struktur Atom dan Molekul', 3, 4),
    ('Stoikiometri', 3, 4),
    ('Pengantar Ilmu Kimia', 3, 4),

    -- Biologi
    ('Biologi Umum', 4, 5),
    ('Praktikum Biologi Umum', 2, 5),
    ('Anatomi Tumbuhan', 3, 5),
    ('Ekologi Dasar', 3, 5),
    ('Pengantar Ilmu Biologi', 3, 5),


    -- =====================
-- SEMESTER 2
-- =====================

-- Informatika
('Algoritma dan Pemrograman', 4, 1),
('Struktur Data', 4, 1),
('Basis Data Dasar', 3, 1),
('Organisasi Komputer', 3, 1),
('Pemrograman Web Dasar', 3, 1),

-- Matematika
('Kalkulus Lanjut', 4, 2),
('Aljabar Linear Lanjut', 3, 2),
('Teori Bilangan', 3, 2),
('Statistika Dasar', 3, 2),
('Persamaan Diferensial Dasar', 3, 2),

-- Fisika
('Fisika Dasar II', 4, 3),
('Listrik dan Magnet', 3, 3),
('Praktikum Fisika Dasar II', 2, 3),
('Gelombang dan Optik', 3, 3),
('Termodinamika Dasar', 3, 3),

-- Kimia
('Kimia Dasar II', 4, 4),
('Praktikum Kimia Dasar II', 2, 4),
('Kimia Analitik Dasar', 3, 4),
('Ikatan Kimia', 3, 4),
('Kesetimbangan Kimia', 3, 4),

-- Biologi
('Biologi Sel', 4, 5),
('Praktikum Biologi Sel', 2, 5),
('Fisiologi Tumbuhan', 3, 5),
('Zoologi Dasar', 3, 5),
('Genetika Dasar', 3, 5),


-- =====================
-- SEMESTER 4
-- =====================

-- Informatika
('Pemrograman Berorientasi Objek', 4, 1),
('Analisis dan Desain Sistem', 3, 1),
('Basis Data Lanjut', 3, 1),
('Jaringan Komputer', 3, 1),
('Rekayasa Perangkat Lunak', 3, 1),

-- Matematika
('Analisis Real', 4, 2),
('Metode Numerik', 3, 2),
('Statistika Matematika', 3, 2),
('Matematika Komputasi', 3, 2),
('Riset Operasi', 3, 2),

-- Fisika
('Fisika Modern', 4, 3),
('Mekanika Klasik', 3, 3),
('Elektronika Dasar', 3, 3),
('Praktikum Elektronika', 2, 3),
('Fisika Komputasi', 3, 3),

-- Kimia
('Kimia Organik I', 4, 4),
('Kimia Fisik I', 3, 4),
('Kimia Analitik Lanjut', 3, 4),
('Praktikum Kimia Organik', 2, 4),
('Spektroskopi Dasar', 3, 4),

-- Biologi
('Mikrobiologi', 4, 5),
('Praktikum Mikrobiologi', 2, 5),
('Biologi Molekuler', 3, 5),
('Ekologi Populasi', 3, 5),
('Taksonomi Tumbuhan', 3, 5),


-- =====================
-- SEMESTER 5
-- =====================

-- Informatika
('Kecerdasan Buatan', 3, 1),
('Sistem Operasi', 3, 1),
('Keamanan Informasi', 3, 1),
('Data Mining', 3, 1),
('Pengembangan Aplikasi Desktop', 4, 1),

-- Matematika
('Analisis Kompleks', 4, 2),
('Teori Graf', 3, 2),
('Probabilitas Lanjut', 3, 2),
('Pemodelan Matematika', 3, 2),
('Optimasi Matematika', 3, 2),

-- Fisika
('Fisika Kuantum', 4, 3),
('Fisika Statistik', 3, 3),
('Instrumentasi Fisika', 3, 3),
('Praktikum Fisika Modern', 2, 3),
('Fisika Material', 3, 3),

-- Kimia
('Kimia Organik II', 4, 4),
('Kimia Fisik II', 3, 4),
('Biokimia', 3, 4),
('Praktikum Kimia Fisik', 2, 4),
('Kimia Lingkungan', 3, 4),

-- Biologi
('Genetika Lanjut', 4, 5),
('Bioteknologi', 3, 5),
('Fisiologi Hewan', 3, 5),
('Praktikum Bioteknologi', 2, 5),
('Konservasi Biologi', 3, 5);
 
 
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
	('2301010001', 6, 3, 1, '2023-07-20 08:15:00');

 
-- ----- Teaches (30) : tiap MK diajar 1 dosen (sesuai jurusan), -----
INSERT INTO Teaches (idSemester, kodeMK, nip, waktuMulai, durasi, hari, jenisPertemuan, metodePertemuan)
VALUES
	-- MK1 Dasar Pemrograman - Dr. Bambang Wijaya
	(1, 1, '198001011998021001', '07:30:00', 90, 'Senin',  'Kuliah',    'Luring'),
	(1, 1, '198001011998021001', '07:30:00', 90, 'Selasa', 'Kuliah',    'Luring'),
	(1, 1, '198001011998021001', '07:30:00', 90, 'Rabu',   'Kuliah',    'Daring'),
	(1, 1, '198001011998021001', '07:30:00', 90, 'Kamis',  'Kuliah',    'Luring'),
	(1, 1, '198001011998021001', '07:30:00', 90, 'Jumat',  'Praktikum', 'Luring'),

	-- MK2 Pengantar Teknologi Informasi - Dr. Maya Sari
	(1, 2, '198203152005012002', '09:30:00', 90, 'Senin',  'Kuliah',    'Luring'),
	(1, 2, '198203152005012002', '09:30:00', 90, 'Selasa', 'Kuliah',    'Luring'),
	(1, 2, '198203152005012002', '09:30:00', 90, 'Rabu',   'Kuliah',    'Daring'),
	(1, 2, '198203152005012002', '09:30:00', 90, 'Kamis',  'Kuliah',    'Luring'),
	(1, 2, '198203152005012002', '09:30:00', 90, 'Jumat',  'Praktikum', 'Luring'),

	-- MK3 Logika Informatika - Dr. Hendra Gunawan
	(1, 3, '197905202003121003', '11:30:00', 90, 'Senin',  'Kuliah',    'Luring'),
	(1, 3, '197905202003121003', '11:30:00', 90, 'Selasa', 'Kuliah',    'Luring'),
	(1, 3, '197905202003121003', '11:30:00', 90, 'Rabu',   'Kuliah',    'Daring'),
	(1, 3, '197905202003121003', '11:30:00', 90, 'Kamis',  'Kuliah',    'Luring'),
	(1, 3, '197905202003121003', '11:30:00', 90, 'Jumat',  'Praktikum', 'Luring'),

	-- MK4 Matematika Diskret - Dr. Rina Marlina
	(1, 4, '198506102010012004', '13:30:00', 90, 'Senin',  'Kuliah',    'Luring'),
	(1, 4, '198506102010012004', '13:30:00', 90, 'Selasa', 'Kuliah',    'Luring'),
	(1, 4, '198506102010012004', '13:30:00', 90, 'Rabu',   'Kuliah',    'Daring'),
	(1, 4, '198506102010012004', '13:30:00', 90, 'Kamis',  'Kuliah',    'Luring'),
	(1, 4, '198506102010012004', '13:30:00', 90, 'Jumat',  'Praktikum', 'Luring'),

	-- MK5 Sistem Digital - Dr. Agus Salim
	(1, 5, '198711252012011005', '15:30:00', 90, 'Senin',  'Kuliah',    'Luring'),
	(1, 5, '198711252012011005', '15:30:00', 90, 'Selasa', 'Kuliah',    'Luring'),
	(1, 5, '198711252012011005', '15:30:00', 90, 'Rabu',   'Kuliah',    'Daring'),
	(1, 5, '198711252012011005', '15:30:00', 90, 'Kamis',  'Kuliah',    'Luring'),
	(1, 5, '198711252012011005', '15:30:00', 90, 'Jumat',  'Praktikum', 'Luring'),

	-- MK6 Kalkulus Dasar - Dr. Maya Sari
	(1, 6, '198203152005012002', '17:30:00', 90, 'Senin',  'Kuliah',    'Luring'),
	(1, 6, '198203152005012002', '17:30:00', 90, 'Selasa', 'Kuliah',    'Luring'),
	(1, 6, '198203152005012002', '17:30:00', 90, 'Rabu',   'Kuliah',    'Daring'),
	(1, 6, '198203152005012002', '17:30:00', 90, 'Kamis',  'Kuliah',    'Luring'),
	(1, 6, '198203152005012002', '17:30:00', 90, 'Jumat',  'Praktikum', 'Luring');


INSERT INTO MatakuliahTerbuka (kodeMK, idSemester)
VALUES
	-- Semester 1
	(1, 1),
	(2, 1),
	(3, 1),
	(4, 1),
	(5, 1),
	(6, 1),
	(7, 1),
	(8, 1),
	(9, 1),
	(10, 1),
	(11, 1),
	(12, 1),
	(13, 1),
	(14, 1),
	(15, 1),
	(16, 1),
	(17, 1),
	(18, 1),
	(19, 1),
	(20, 1),
	(21, 1),
	(22, 1),
	(23, 1),
	(24, 1),
	(25, 1),

	-- Semester 2
	(26, 2),
	(27, 2),
	(28, 2),
	(29, 2),
	(30, 2),
	(31, 2),
	(32, 2),
	(33, 2),
	(34, 2),
	(35, 2),
	(36, 2),
	(37, 2),
	(38, 2),
	(39, 2),
	(40, 2),
	(41, 2),
	(42, 2),
	(43, 2),
	(44, 2),
	(45, 2),
	(46, 2),
	(47, 2),
	(48, 2),
	(49, 2),
	(50, 2),

	-- Semester 4
	(51, 4),
	(52, 4),
	(53, 4),
	(54, 4),
	(55, 4),
	(56, 4),
	(57, 4),
	(58, 4),
	(59, 4),
	(60, 4),
	(61, 4),
	(62, 4),
	(63, 4),
	(64, 4),
	(65, 4),
	(66, 4),
	(67, 4),
	(68, 4),
	(69, 4),
	(70, 4),
	(71, 4),
	(72, 4),
	(73, 4),
	(74, 4),
	(75, 4),

	-- Semester 5
	(76, 5),
	(77, 5),
	(78, 5),
	(79, 5),
	(80, 5),
	(81, 5),
	(82, 5),
	(83, 5),
	(84, 5),
	(85, 5),
	(86, 5),
	(87, 5),
	(88, 5),
	(89, 5),
	(90, 5),
	(91, 5),
	(92, 5),
	(93, 5),
	(94, 5),
	(95, 5),
	(96, 5),
	(97, 5),
	(98, 5),
	(99, 5),
	(100, 5);