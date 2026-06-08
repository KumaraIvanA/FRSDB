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
 
 
INSERT INTO MataKuliah (namaMK, jumlahSKS, idJurusan, nip)
VALUES
    -- =====================
    -- SEMESTER 1
    -- =====================

    -- Informatika
    ('Dasar Pemrograman', 4, 1, '198001011998021001'),
    ('Pengantar Teknologi Informasi', 3, 1, '198001011998021001'),
    ('Logika Informatika', 3, 1, '198001011998021001'),
    ('Matematika Diskret', 3, 1, '198001011998021001'),
    ('Sistem Digital', 3, 1, '198001011998021001'),

    -- Matematika
    ('Kalkulus Dasar', 4, 2, '198203152005012002'),
    ('Aljabar Linear Dasar', 3, 2, '198203152005012002'),
    ('Logika Matematika', 3, 2, '198203152005012002'),
    ('Pengantar Statistika', 3, 2, '198203152005012002'),
    ('Geometri Dasar', 3, 2, '198203152005012002'),

    -- Fisika
    ('Fisika Dasar I', 4, 3, '197905202003121003'),
    ('Mekanika Dasar', 3, 3, '197905202003121003'),
    ('Praktikum Fisika Dasar I', 2, 3, '197905202003121003'),
    ('Matematika Fisika Dasar', 3, 3, '197905202003121003'),
    ('Pengantar Ilmu Fisika', 3, 3, '197905202003121003'),

    -- Kimia
    ('Kimia Dasar I', 4, 4, '198506102010012004'),
    ('Praktikum Kimia Dasar I', 2, 4, '198506102010012004'),
    ('Struktur Atom dan Molekul', 3, 4, '198506102010012004'),
    ('Stoikiometri', 3, 4, '198506102010012004'),
    ('Pengantar Ilmu Kimia', 3, 4, '198506102010012004'),

    -- Biologi
    ('Biologi Umum', 4, 5, '198711252012011005'),
    ('Praktikum Biologi Umum', 2, 5, '198711252012011005'),
    ('Anatomi Tumbuhan', 3, 5, '198711252012011005'),
    ('Ekologi Dasar', 3, 5, '198711252012011005'),
    ('Pengantar Ilmu Biologi', 3, 5, '198711252012011005'),


    -- =====================
    -- SEMESTER 2
    -- =====================

    -- Informatika
    ('Algoritma dan Pemrograman', 4, 1, '198001011998021001'),
    ('Struktur Data', 4, 1, '198001011998021001'),
    ('Basis Data Dasar', 3, 1, '198001011998021001'),
    ('Organisasi Komputer', 3, 1, '198001011998021001'),
    ('Pemrograman Web Dasar', 3, 1, '198001011998021001'),

    -- Matematika
    ('Kalkulus Lanjut', 4, 2, '198203152005012002'),
    ('Aljabar Linear Lanjut', 3, 2, '198203152005012002'),
    ('Teori Bilangan', 3, 2, '198203152005012002'),
    ('Statistika Dasar', 3, 2, '198203152005012002'),
    ('Persamaan Diferensial Dasar', 3, 2, '198203152005012002'),

    -- Fisika
    ('Fisika Dasar II', 4, 3, '197905202003121003'),
    ('Listrik dan Magnet', 3, 3, '197905202003121003'),
    ('Praktikum Fisika Dasar II', 2, 3, '197905202003121003'),
    ('Gelombang dan Optik', 3, 3, '197905202003121003'),
    ('Termodinamika Dasar', 3, 3, '197905202003121003'),

    -- Kimia
    ('Kimia Dasar II', 4, 4, '198506102010012004'),
    ('Praktikum Kimia Dasar II', 2, 4, '198506102010012004'),
    ('Kimia Analitik Dasar', 3, 4, '198506102010012004'),
    ('Ikatan Kimia', 3, 4, '198506102010012004'),
    ('Kesetimbangan Kimia', 3, 4, '198506102010012004'),

    -- Biologi
    ('Biologi Sel', 4, 5, '198711252012011005'),
    ('Praktikum Biologi Sel', 2, 5, '198711252012011005'),
    ('Fisiologi Tumbuhan', 3, 5, '198711252012011005'),
    ('Zoologi Dasar', 3, 5, '198711252012011005'),
    ('Genetika Dasar', 3, 5, '198711252012011005'),


    -- =====================
    -- SEMESTER 3
    -- =====================

    -- Informatika
    ('Pemrograman Java', 4, 1, '198001011998021001'),
    ('Sistem Basis Data', 4, 1, '198001011998021001'),
    ('Arsitektur Komputer', 3, 1, '198001011998021001'),
    ('Interaksi Manusia dan Komputer', 3, 1, '198001011998021001'),
    ('Analisis Algoritma', 3, 1, '198001011998021001'),

    -- Matematika
    ('Kalkulus Multivariat', 4, 2, '198203152005012002'),
    ('Matematika Diskret Lanjut', 3, 2, '198203152005012002'),
    ('Statistika Inferensial', 3, 2, '198203152005012002'),
    ('Aljabar Abstrak Dasar', 3, 2, '198203152005012002'),
    ('Matematika Keuangan', 3, 2, '198203152005012002'),

    -- Fisika
    ('Mekanika Lanjut', 4, 3, '197905202003121003'),
    ('Fisika Gelombang', 3, 3, '197905202003121003'),
    ('Optika Dasar', 3, 3, '197905202003121003'),
    ('Praktikum Gelombang dan Optik', 2, 3, '197905202003121003'),
    ('Fisika Termal', 3, 3, '197905202003121003'),

    -- Kimia
    ('Kimia Organik Dasar', 4, 4, '198506102010012004'),
    ('Praktikum Kimia Organik Dasar', 2, 4, '198506102010012004'),
    ('Kimia Anorganik Dasar', 3, 4, '198506102010012004'),
    ('Termodinamika Kimia', 3, 4, '198506102010012004'),
    ('Kinetika Kimia', 3, 4, '198506102010012004'),

    -- Biologi
    ('Morfologi Tumbuhan', 3, 5, '198711252012011005'),
    ('Fisiologi Hewan Dasar', 3, 5, '198711252012011005'),
    ('Mikrobiologi Dasar', 4, 5, '198711252012011005'),
    ('Praktikum Mikrobiologi Dasar', 2, 5, '198711252012011005'),
    ('Evolusi', 3, 5, '198711252012011005'),


    -- =====================
    -- SEMESTER 4
    -- =====================

    -- Informatika
    ('Pemrograman Berorientasi Objek', 4, 1, '198001011998021001'),
    ('Analisis dan Desain Sistem', 3, 1, '198001011998021001'),
    ('Basis Data Lanjut', 3, 1, '198001011998021001'),
    ('Jaringan Komputer', 3, 1, '198001011998021001'),
    ('Rekayasa Perangkat Lunak', 3, 1, '198001011998021001'),

    -- Matematika
    ('Analisis Real', 4, 2, '198203152005012002'),
    ('Metode Numerik', 3, 2, '198203152005012002'),
    ('Statistika Matematika', 3, 2, '198203152005012002'),
    ('Matematika Komputasi', 3, 2, '198203152005012002'),
    ('Riset Operasi', 3, 2, '198203152005012002'),

    -- Fisika
    ('Fisika Modern', 4, 3, '197905202003121003'),
    ('Mekanika Klasik', 3, 3, '197905202003121003'),
    ('Elektronika Dasar', 3, 3, '197905202003121003'),
    ('Praktikum Elektronika', 2, 3, '197905202003121003'),
    ('Fisika Komputasi', 3, 3, '197905202003121003'),

    -- Kimia
    ('Kimia Organik I', 4, 4, '198506102010012004'),
    ('Kimia Fisik I', 3, 4, '198506102010012004'),
    ('Kimia Analitik Lanjut', 3, 4, '198506102010012004'),
    ('Praktikum Kimia Organik', 2, 4, '198506102010012004'),
    ('Spektroskopi Dasar', 3, 4, '198506102010012004'),

    -- Biologi
    ('Mikrobiologi', 4, 5, '198711252012011005'),
    ('Praktikum Mikrobiologi', 2, 5, '198711252012011005'),
    ('Biologi Molekuler', 3, 5, '198711252012011005'),
    ('Ekologi Populasi', 3, 5, '198711252012011005'),
    ('Taksonomi Tumbuhan', 3, 5, '198711252012011005'),


    -- =====================
    -- SEMESTER 5
    -- =====================

    -- Informatika
    ('Kecerdasan Buatan', 3, 1, '198001011998021001'),
    ('Sistem Operasi', 3, 1, '198001011998021001'),
    ('Keamanan Informasi', 3, 1, '198001011998021001'),
    ('Data Mining', 3, 1, '198001011998021001'),
    ('Pengembangan Aplikasi Desktop', 4, 1, '198001011998021001'),

    -- Matematika
    ('Analisis Kompleks', 4, 2, '198203152005012002'),
    ('Teori Graf', 3, 2, '198203152005012002'),
    ('Probabilitas Lanjut', 3, 2, '198203152005012002'),
    ('Pemodelan Matematika', 3, 2, '198203152005012002'),
    ('Optimasi Matematika', 3, 2, '198203152005012002'),

    -- Fisika
    ('Fisika Kuantum', 4, 3, '197905202003121003'),
    ('Fisika Statistik', 3, 3, '197905202003121003'),
    ('Instrumentasi Fisika', 3, 3, '197905202003121003'),
    ('Praktikum Fisika Modern', 2, 3, '197905202003121003'),
    ('Fisika Material', 3, 3, '197905202003121003'),

    -- Kimia
    ('Kimia Organik II', 4, 4, '198506102010012004'),
    ('Kimia Fisik II', 3, 4, '198506102010012004'),
    ('Biokimia', 3, 4, '198506102010012004'),
    ('Praktikum Kimia Fisik', 2, 4, '198506102010012004'),
    ('Kimia Lingkungan', 3, 4, '198506102010012004'),

    -- Biologi
    ('Genetika Lanjut', 4, 5, '198711252012011005'),
    ('Bioteknologi', 3, 5, '198711252012011005'),
    ('Fisiologi Hewan', 3, 5, '198711252012011005'),
    ('Praktikum Bioteknologi', 2, 5, '198711252012011005'),
    ('Konservasi Biologi', 3, 5, '198711252012011005');
 
 
-- ============================================================
--  RELATIONAL DATA
-- ============================================================
 
 -- ----- FRS -----
 INSERT INTO FRS DEFAULT VALUES 


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
INSERT INTO Teaches (kodeMK, nip, waktuMulai, durasi, hari, jenisPertemuan, metodePertemuan)
VALUES
	-- MK1 Dasar Pemrograman - Dr. Bambang Wijaya
	(1, '198001011998021001', '07:30:00', 90, 'Senin',  'Kuliah',    'Luring'),
	(1, '198001011998021001', '07:30:00', 90, 'Selasa', 'Kuliah',    'Luring'),
	(1, '198001011998021001', '07:30:00', 90, 'Rabu',   'Kuliah',    'Daring'),
	(1, '198001011998021001', '07:30:00', 90, 'Kamis',  'Kuliah',    'Luring'),
	(1, '198001011998021001', '07:30:00', 90, 'Jumat',  'Praktikum', 'Luring'),

	-- MK2 Pengantar Teknologi Informasi - Dr. Maya Sari
	(2, '198203152005012002', '09:30:00', 90, 'Senin',  'Kuliah',    'Luring'),
	(2, '198203152005012002', '09:30:00', 90, 'Selasa', 'Kuliah',    'Luring'),
	(2, '198203152005012002', '09:30:00', 90, 'Rabu',   'Kuliah',    'Daring'),
	(2, '198203152005012002', '09:30:00', 90, 'Kamis',  'Kuliah',    'Luring'),
	(2, '198203152005012002', '09:30:00', 90, 'Jumat',  'Praktikum', 'Luring'),

	-- MK3 Logika Informatika - Dr. Hendra Gunawan
	(3, '197905202003121003', '11:30:00', 90, 'Senin',  'Kuliah',    'Luring'),
	(3, '197905202003121003', '11:30:00', 90, 'Selasa', 'Kuliah',    'Luring'),
	(3, '197905202003121003', '11:30:00', 90, 'Rabu',   'Kuliah',    'Daring'),
	(3, '197905202003121003', '11:30:00', 90, 'Kamis',  'Kuliah',    'Luring'),
	(3, '197905202003121003', '11:30:00', 90, 'Jumat',  'Praktikum', 'Luring'),

	-- MK4 Matematika Diskret - Dr. Rina Marlina
	(4, '198506102010012004', '13:30:00', 90, 'Senin',  'Kuliah',    'Luring'),
	(4, '198506102010012004', '13:30:00', 90, 'Selasa', 'Kuliah',    'Luring'),
	(4, '198506102010012004', '13:30:00', 90, 'Rabu',   'Kuliah',    'Daring'),
	(4, '198506102010012004', '13:30:00', 90, 'Kamis',  'Kuliah',    'Luring'),
	(4, '198506102010012004', '13:30:00', 90, 'Jumat',  'Praktikum', 'Luring'),

	-- MK5 Sistem Digital - Dr. Agus Salim
	(5, '198711252012011005', '15:30:00', 90, 'Senin',  'Kuliah',    'Luring'),
	(5, '198711252012011005', '15:30:00', 90, 'Selasa', 'Kuliah',    'Luring'),
	(5, '198711252012011005', '15:30:00', 90, 'Rabu',   'Kuliah',    'Daring'),
	(5, '198711252012011005', '15:30:00', 90, 'Kamis',  'Kuliah',    'Luring'),
	(5, '198711252012011005', '15:30:00', 90, 'Jumat',  'Praktikum', 'Luring'),

	-- MK6 Kalkulus Dasar - Dr. Maya Sari
	(6, '198203152005012002', '17:30:00', 90, 'Senin',  'Kuliah',    'Luring'),
	(6, '198203152005012002', '17:30:00', 90, 'Selasa', 'Kuliah',    'Luring'),
	(6, '198203152005012002', '17:30:00', 90, 'Rabu',   'Kuliah',    'Daring'),
	(6, '198203152005012002', '17:30:00', 90, 'Kamis',  'Kuliah',    'Luring'),
	(6, '198203152005012002', '17:30:00', 90, 'Jumat',  'Praktikum', 'Luring');


INSERT INTO MataKuliahTerbuka (kodeMK, idSemester)
VALUES
    -- =====================
    -- SEMESTER 1
    -- =====================

    -- Informatika
    (1, 1),
    (2, 1),
    (3, 1),
    (4, 1),
    (5, 1),

    -- Matematika
    (6, 1),
    (7, 1),
    (8, 1),
    (9, 1),
    (10, 1),

    -- Fisika
    (11, 1),
    (12, 1),
    (13, 1),
    (14, 1),
    (15, 1),

    -- Kimia
    (16, 1),
    (17, 1),
    (18, 1),
    (19, 1),
    (20, 1),

    -- Biologi
    (21, 1),
    (22, 1),
    (23, 1),
    (24, 1),
    (25, 1),


    -- =====================
    -- SEMESTER 2
    -- =====================

    -- Informatika
    (26, 2),
    (27, 2),
    (28, 2),
    (29, 2),
    (30, 2),

    -- Matematika
    (31, 2),
    (32, 2),
    (33, 2),
    (34, 2),
    (35, 2),

    -- Fisika
    (36, 2),
    (37, 2),
    (38, 2),
    (39, 2),
    (40, 2),

    -- Kimia
    (41, 2),
    (42, 2),
    (43, 2),
    (44, 2),
    (45, 2),

    -- Biologi
    (46, 2),
    (47, 2),
    (48, 2),
    (49, 2),
    (50, 2),


    -- =====================
    -- SEMESTER 3
    -- =====================

    -- Informatika
    (51, 3),
    (52, 3),
    (53, 3),
    (54, 3),
    (55, 3),

    -- Matematika
    (56, 3),
    (57, 3),
    (58, 3),
    (59, 3),
    (60, 3),

    -- Fisika
    (61, 3),
    (62, 3),
    (63, 3),
    (64, 3),
    (65, 3),

    -- Kimia
    (66, 3),
    (67, 3),
    (68, 3),
    (69, 3),
    (70, 3),

    -- Biologi
    (71, 3),
    (72, 3),
    (73, 3),
    (74, 3),
    (75, 3),


    -- =====================
    -- SEMESTER 4
    -- =====================

    -- Informatika
    (76, 4),
    (77, 4),
    (78, 4),
    (79, 4),
    (80, 4),

    -- Matematika
    (81, 4),
    (82, 4),
    (83, 4),
    (84, 4),
    (85, 4),

    -- Fisika
    (86, 4),
    (87, 4),
    (88, 4),
    (89, 4),
    (90, 4),

    -- Kimia
    (91, 4),
    (92, 4),
    (93, 4),
    (94, 4),
    (95, 4),

    -- Biologi
    (96, 4),
    (97, 4),
    (98, 4),
    (99, 4),
    (100, 4),

    -- =====================
    -- SEMESTER 5
    -- =====================

    -- Informatika
    (101, 5),
    (102, 5),
    (103, 5),
    (104, 5),
    (105, 5),

    -- Matematika
    (106, 5),
    (107, 5),
    (108, 5),
    (109, 5),
    (110, 5),

    -- Fisika
    (111, 5),
    (112, 5),
    (113, 5),
    (114, 5),
    (115, 5),

    -- Kimia
    (116, 5),
    (117, 5),
    (118, 5),
    (119, 5),
    (120, 5),

    -- Biologi
    (121, 5),
    (122, 5),
    (123, 5),
    (124, 5),
    (125, 5);