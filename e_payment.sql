-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 17 Bulan Mei 2023 pada 10.08
-- Versi server: 10.4.27-MariaDB
-- Versi PHP: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `e_payment`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_transaksi_beli`
--

CREATE TABLE `detail_transaksi_beli` (
  `kode_detail_tr_beli` int(5) NOT NULL,
  `kode_tr_beli` varchar(10) DEFAULT NULL,
  `kode_supplier` varchar(5) DEFAULT NULL,
  `nama_barang` varchar(30) DEFAULT NULL,
  `jumlah_barang` varchar(10) DEFAULT NULL,
  `total_harga` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data untuk tabel `detail_transaksi_beli`
--

INSERT INTO `detail_transaksi_beli` (`kode_detail_tr_beli`, `kode_tr_beli`, `kode_supplier`, `nama_barang`, `jumlah_barang`, `total_harga`) VALUES
(13, 'TRB001', 'SP01', 'Bakso ', '20', 2000),
(14, 'TRB001', 'SP01', 'Mie Lidi', '1', 1000),
(15, 'TRB002', 'SP01', 'Bakso Ikan', '10 kg', 200000),
(16, 'TRB002', 'SP01', 'Mie Lidi', '10 kg', 50000),
(17, 'TRB003', 'SP01', 'Kemasan ukuran 9*12', '100 pcs', 73000),
(18, 'TRB003', 'SP01', 'Stiker Label', '2 lembar', 22000),
(20, 'TRB004', 'SP01', 'Bakso Ikan', '10', 200000),
(21, 'TRB004', 'SP01', 'Mie lidi', '10', 70000),
(22, 'TRB005', 'SP01', 'Bakso ikan', '9', 180000),
(23, 'TRB005', 'SP01', 'Usus Kering', '5', 50000),
(24, 'TRB006', 'SP01', 'Plastik', '2Kg 3', 50000),
(25, 'TRB007', 'SP01', 'Bakso Ikan', '10 kg', 200000),
(26, 'TRB007', 'SP01', 'Usus Kering', '5 kg', 35000),
(27, 'TRB008', 'SP01', 'mie', '10 kg', 30000),
(28, 'TRB008', 'SP01', 'STiker', '5 lembar', 60000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_transaksi_jual`
--

CREATE TABLE `detail_transaksi_jual` (
  `kode_detail_tr_jual` int(5) NOT NULL,
  `kode_tr_jual` varchar(10) DEFAULT NULL,
  `kode_produk` varchar(20) DEFAULT NULL,
  `nama_produk` varchar(50) DEFAULT NULL,
  `jumlah` int(5) DEFAULT NULL,
  `total_harga` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data untuk tabel `detail_transaksi_jual`
--

INSERT INTO `detail_transaksi_jual` (`kode_detail_tr_jual`, `kode_tr_jual`, `kode_produk`, `nama_produk`, `jumlah`, `total_harga`) VALUES
(62, 'TRJ001', '1111-BASRENG-0002', 'Mie Lidi Pedas  80 g Pedas  80 g', 6, 30000),
(63, 'TRJ001', '1111-BASRENG-0001', 'Basreng Pedas Level 1 110 g Pedas Level 1 110 g', 2, 13000),
(64, 'TRJ001', '1111-BASRENG-0003', 'Basreng Original 220 g Original 220 g', 8, 88000),
(65, 'TRJ002', '1111-BASRENG-0001', 'Basreng Pedas Level 1 110 g Pedas Level 1 110 g', 10, 65000),
(66, 'TRJ003', '1111-BASRENG-0001', 'Basreng Pedas Level 1 110 g Pedas Level 1 110 g', 8, 52000),
(67, 'TRJ003', '1111-BASRENG-0002', 'Mie Lidi Pedas  80 g Pedas  80 g', 2, 10000),
(68, 'TRJ003', '1111-BASRENG-0003', 'Basreng Original 220 g Original 220 g', 10, 110000),
(69, 'TRJ004', '1111-BASRENG-0001', 'Basreng Pedas Level 1 110 g Pedas Level 1 110 g', 6, 39000),
(70, 'TRJ004', '1111-BASRENG-0002', 'Mie Lidi Pedas  80 g Pedas  80 g', 9, 45000),
(71, 'TRJ005', '1111-BASRENG-0002', 'Mie Lidi Pedas  80 g Pedas  80 g', 4, 20000),
(72, 'TRJ006', '1111-BASRENG-0003', 'Basreng Original 220 g Original 220 g', 11, 121000),
(73, 'TRJ006', '1111-BASRENG-0001', 'Basreng Pedas Level 1 110 g Pedas Level 1 110 g', 7, 45500),
(74, 'TRJ007', '1111-BASRENG-0001', 'Basreng Pedas Level 1 110 g Pedas Level 1 110 g', 8, 52000),
(75, 'TRJ007', '1111-BASRENG-0003', 'Basreng Original 220 g Original 220 g', 7, 77000),
(76, 'TRJ008', '1111-BASRENG-0001', 'Basreng Pedas Level 1 110 g Pedas Level 1 110 g', 1, 6500),
(77, 'TRJ008', '1111-BASRENG-0002', 'Mie Lidi Pedas  80 g Pedas  80 g', 3, 15000),
(78, 'TRJ009', '1111-BASRENG-0003', 'Basreng Original 220 g Original 220 g', 3, 33000),
(79, 'TRJ009', '1111-BASRENG-0002', 'Mie Lidi Pedas  80 g Pedas  80 g', 2, 10000),
(80, 'TRJ010', '1111-BASRENG-0002', 'Mie Lidi Pedas  80 g Pedas  80 g', 3, 15000),
(81, 'TRJ010', '1111-BASRENG-0003', 'Basreng Original 220 g Original 220 g', 7, 77000),
(82, 'TRJ001', '1111-BASRENG-0003', 'Basreng Original 220 g Original 220 g', 3, 33000),
(83, 'TRJ001', '1111-BASRENG-0001', 'Basreng Pedas Level 1 110 g Pedas Level 1 110 g', 4, 26000),
(84, 'TRJ011', '1111-BASRENG-0002', 'Mie Lidi Pedas  80 g Pedas  80 g', 7, 35000),
(85, 'TRJ011', '1111-BASRENG-0003', 'Basreng Original 220 g Original 220 g', 6, 66000),
(86, 'TRJ012', '1111-BASRENG-0001', 'Basreng Pedas Level 1 110 g Pedas Level 1 110 g', 8, 52000),
(87, 'TRJ012', '1111-BASRENG-0003', 'Basreng Original 220 g Original 220 g', 5, 55000),
(88, 'TRJ013', '1111-BASRENG-0001', 'Basreng Pedas Level 1 110 g Pedas Level 1 110 g', 12, 78000),
(89, 'TRJ013', '1111-BASRENG-0002', 'Mie Lidi Pedas  80 g Pedas  80 g', 1, 5000),
(90, 'TRJ013', '1111-BASRENG-0003', 'Basreng Original 220 g Original 220 g', 2, 22000),
(91, 'TRJ014', '1111-BASRENG-0001', 'Basreng Pedas Level 1 110 g Pedas Level 1 110 g', 2, 13000),
(92, 'TRJ014', '1111-BASRENG-0003', 'Basreng Original 220 g Original 220 g', 1, 11000),
(93, 'TRJ015', '1111-BASRENG-0001', 'Basreng Pedas Level 1 110 g Pedas Level 1 110 g', 7, 45500),
(94, 'TRJ015', '1111-BASRENG-0003', 'Basreng Original 220 g Original 220 g', 2, 22000),
(95, 'TRJ016', '1111-BASRENG-0003', 'Basreng Original 220 g Original 220 g', 1, 11000),
(96, 'TRJ016', '1111-BASRENG-0001', 'Basreng Pedas Level 1 110 g Pedas Level 1 110 g', 2, 13000),
(97, 'TRJ017', '1111-BASRENG-0001', 'Basreng Pedas Level 1 110 g Pedas Level 1 110 g', 13, 84500),
(98, 'TRJ017', '1111-BASRENG-0003', 'Basreng Original 220 g Original 220 g', 7, 77000),
(99, 'TRJ018', '1111-BASRENG-0001', 'Basreng Pedas Level 1 110 g Pedas Level 1 110 g', 5, 32500),
(100, 'TRJ018', '1111-BASRENG-0003', 'Basreng Original 220 g Original 220 g', 4, 44000),
(105, 'TRJ019', '1111-BASRENG-0001', 'Basreng Pedas Level 1 110 g Pedas Level 1 110 g', 4, 26000),
(106, 'TRJ019', '1111-BASRENG-0002', 'Mie Lidi Pedas  80 g Pedas  80 g', 1, 5000),
(107, 'TRJ019', '1111-BASRENG-0003', 'Basreng Original 220 g Original 220 g', 2, 22000),
(108, 'TRJ020', '1111-BASRENG-0001', 'Basreng Pedas Level 1 110 g Pedas Level 1 110 g', 3, 19500),
(109, 'TRJ020', '1111-BASRENG-0003', 'Basreng Original 220 g Original 220 g', 4, 44000),
(110, 'TRJ021', '1111-BASRENG-0001', 'Basreng Pedas Level 1 110 g Pedas Level 1 110 g', 3, 19500),
(111, 'TRJ021', '1111-BASRENG-0002', 'Mie Lidi Pedas  80 g Pedas  80 g', 3, 15000),
(112, 'TRJ022', '1111-BASRENG-0002', 'Mie Lidi Pedas  80 g Pedas  80 g', 2, 10000),
(113, 'TRJ022', '1111-BASRENG-0001', 'Basreng Pedas Level 1 110 g Pedas Level 1 110 g', 5, 32500),
(114, 'TRJ023', '1111-BASRENG-0001', 'Basreng Pedas Level 1 110 g Pedas Level 1 110 g', 1, 6500),
(115, 'TRJ023', '1111-BASRENG-0002', 'Mie Lidi Pedas  80 g Pedas  80 g', 1, 5000),
(116, 'TRJ024', '1111-BASRENG-0001', 'Basreng Pedas Level 1 110 g Pedas Level 1 110 g', 1, 6500),
(117, 'TRJ024', '1111-BASRENG-0003', 'Basreng Original 220 g Original 220 g', 1, 11000),
(118, 'TRJ025', '1111-BASRENG-0001', 'Basreng Pedas Level 1 110 g Pedas Level 1 110 g', 1, 6500),
(119, 'TRJ025', '1111-BASRENG-0003', 'Basreng Original 220 g Original 220 g', 1, 11000),
(120, 'TRJ026', '1111-BASRENG-0001', 'Basreng Pedas Level 1 110 g Pedas Level 1 110 g', 1, 6500),
(121, 'TRJ026', '1111-BASRENG-0003', 'Basreng Original 220 g Original 220 g', 1, 11000),
(122, 'TRJ027', '1111-BASRENG-0003', 'Basreng Original 220 g Original 220 g', 5, 55000),
(123, 'TRJ027', '1111-BASRENG-0002', 'Mie Lidi Pedas  80 g Pedas  80 g', 7, 35000),
(124, 'TRJ027', '1111-BASRENG-0001', 'Basreng Pedas Level 1 110 g Pedas Level 1 110 g', 3, 19500),
(125, 'TRJ028', '1111-BASRENG-0001', 'Basreng Pedas Level 1 110 g Pedas Level 1 110 g', 2, 13000),
(126, 'TRJ028', '1111-BASRENG-0002', 'Mie Lidi Pedas  80 g Pedas  80 g', 2, 10000),
(127, 'TRJ029', '1111-BASRENG-0001', 'Basreng Pedas Level 1 110 g Pedas Level 1 110 g', 2, 13000),
(128, 'TRJ029', '1111-BASRENG-0003', 'Basreng Original 220 g Original 220 g', 2, 22000),
(129, 'TRJ030', '1111-BASRENG-0001', 'Basreng Pedas Level 1 110 g Pedas Level 1 110 g', 2, 13000),
(130, 'TRJ030', '1111-BASRENG-0003', 'Basreng Original 220 g Original 220 g', 2, 22000);

--
-- Trigger `detail_transaksi_jual`
--
DELIMITER $$
CREATE TRIGGER `kurang_stok` AFTER INSERT ON `detail_transaksi_jual` FOR EACH ROW BEGIN
UPDATE produk
SET stok= stok - NEW.jumlah
WHERE `kode_produk` = NEW.kode_produk;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `karyawan`
--

CREATE TABLE `karyawan` (
  `kode_karyawan` varchar(20) NOT NULL,
  `username` varchar(10) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `nama_karyawan` varchar(30) DEFAULT NULL,
  `no_telp` varchar(15) DEFAULT NULL,
  `alamat` varchar(30) DEFAULT NULL,
  `level_user` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data untuk tabel `karyawan`
--

INSERT INTO `karyawan` (`kode_karyawan`, `username`, `password`, `nama_karyawan`, `no_telp`, `alamat`, `level_user`) VALUES
('0008242317', 'tria01', 'yaudah', 'TRIA YUNITA', '+62895342743004', 'Kab Nganjuk', 'Owner'),
('KR01', 'agus01', 'cobasi', 'MIN YOONGI', '+62895413801079', 'Kab. Nganjuk', 'karyawan');

-- --------------------------------------------------------

--
-- Struktur dari tabel `member`
--

CREATE TABLE `member` (
  `kode_member` varchar(15) NOT NULL,
  `nama_member` varchar(30) DEFAULT NULL,
  `no_telp` varchar(15) DEFAULT NULL,
  `alamat` varchar(30) DEFAULT NULL,
  `tanggal_daftar` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data untuk tabel `member`
--

INSERT INTO `member` (`kode_member`, `nama_member`, `no_telp`, `alamat`, `tanggal_daftar`) VALUES
('0008487459', 'Septia', '+628953123456', 'Kab. Nganjuk', '2023-04-06');

-- --------------------------------------------------------

--
-- Struktur dari tabel `produk`
--

CREATE TABLE `produk` (
  `kode_produk` varchar(20) NOT NULL,
  `nama_produk` varchar(10) DEFAULT NULL,
  `varian` varchar(20) DEFAULT NULL,
  `ukuran` varchar(10) DEFAULT NULL,
  `harga` int(11) DEFAULT NULL,
  `stok` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data untuk tabel `produk`
--

INSERT INTO `produk` (`kode_produk`, `nama_produk`, `varian`, `ukuran`, `harga`, `stok`) VALUES
('1111-BASRENG-0001', 'Basreng', 'Pedas Level 1', '110 g', 6500, 81),
('1111-BASRENG-0002', 'Mie Lidi', 'Pedas ', '80 g', 5000, 81),
('1111-BASRENG-0003', 'Basreng', 'Original', '220 g', 11000, 81);

-- --------------------------------------------------------

--
-- Struktur dari tabel `supplier`
--

CREATE TABLE `supplier` (
  `kode_supplier` varchar(5) NOT NULL,
  `nama_supplier` varchar(30) DEFAULT NULL,
  `no_telp` varchar(15) DEFAULT NULL,
  `alamat` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data untuk tabel `supplier`
--

INSERT INTO `supplier` (`kode_supplier`, `nama_supplier`, `no_telp`, `alamat`) VALUES
('SP01', 'Rustamawati', '0895413801079', 'Kab. Nganjuk');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi_beli`
--

CREATE TABLE `transaksi_beli` (
  `kode_tr_beli` varchar(10) NOT NULL,
  `kode_karyawan` varchar(20) NOT NULL,
  `total_harga` int(15) DEFAULT NULL,
  `tanggal` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data untuk tabel `transaksi_beli`
--

INSERT INTO `transaksi_beli` (`kode_tr_beli`, `kode_karyawan`, `total_harga`, `tanggal`) VALUES
('TRB001', '0008242317', 3000, '2023-05-13 20:28:09'),
('TRB002', '0008242317', 250000, '2023-05-13 20:45:07'),
('TRB003', '0008242317', 95000, '2023-05-13 20:54:36'),
('TRB004', '0008242317', 270000, '2023-05-14 22:55:32'),
('TRB005', '0008242317', 230000, '2023-05-14 23:02:22'),
('TRB006', '0008242317', 180000, '2023-05-15 11:01:16'),
('TRB007', '0008242317', 235000, '2023-06-15 21:32:51'),
('TRB008', '0008242317', 90000, '2023-05-17 14:51:57');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi_jual`
--

CREATE TABLE `transaksi_jual` (
  `kode_tr_jual` varchar(10) NOT NULL,
  `kode_karyawan` varchar(20) NOT NULL,
  `kode_member` varchar(15) DEFAULT NULL,
  `total_item` int(5) DEFAULT NULL,
  `total_harga` int(15) DEFAULT NULL,
  `harga_diskon` int(20) NOT NULL,
  `bayar` int(20) NOT NULL,
  `tanggal` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data untuk tabel `transaksi_jual`
--

INSERT INTO `transaksi_jual` (`kode_tr_jual`, `kode_karyawan`, `kode_member`, `total_item`, `total_harga`, `harga_diskon`, `bayar`, `tanggal`) VALUES
('TRJ001', '0008242317', '', 16, 131000, 0, 131000, '2023-05-11 19:58:49'),
('TRJ002', '0008242317', '0008487459', 10, 65000, 63700, 63700, '2023-05-11 19:59:42'),
('TRJ003', '0008242317', '0008487459', 20, 172000, 168560, 168560, '2023-05-11 20:00:20'),
('TRJ004', '0008242317', '', 15, 84000, 0, 84000, '2023-05-12 13:25:08'),
('TRJ005', '0008242317', '', 4, 20000, 0, 20000, '2023-05-12 15:27:49'),
('TRJ006', 'KR01', '', 18, 166500, 0, 166500, '2023-05-12 19:07:50'),
('TRJ007', '0008242317', '', 15, 129000, 0, 129000, '2023-05-13 21:00:01'),
('TRJ008', '0008242317', '', 4, 21500, 0, 21500, '2023-05-13 21:02:02'),
('TRJ009', '0008242317', '', 5, 43000, 0, 43000, '2023-05-13 21:11:52'),
('TRJ010', '0008242317', '', 10, 92000, 0, 92000, '2023-05-14 19:05:49'),
('TRJ011', '0008242317', '', 13, 101000, 0, 101000, '2023-05-14 19:56:36'),
('TRJ012', '0008242317', '0008487459', 13, 107000, 104860, 104860, '2023-05-14 20:32:29'),
('TRJ013', '0008242317', '', 15, 105000, 0, 105000, '2023-05-14 21:45:37'),
('TRJ014', '0008242317', '', 3, 24000, 0, 24000, '2023-05-14 21:51:04'),
('TRJ015', '0008242317', '', 9, 67500, 0, 67500, '2023-05-14 22:02:02'),
('TRJ016', '0008242317', '', 3, 24000, 0, 24000, '2023-05-14 22:24:14'),
('TRJ017', '0008242317', '0008487459', 20, 161500, 158270, 158270, '2023-05-14 22:57:31'),
('TRJ018', '0008242317', '', 9, 76500, 0, 76500, '2023-05-14 23:02:57'),
('TRJ019', '0008242317', '', 7, 53000, 0, 53000, '2023-06-14 21:33:29'),
('TRJ020', '0008242317', '', 7, 63500, 0, 63500, '2023-05-15 22:22:38'),
('TRJ021', '0008242317', '', 6, 34500, 0, 34500, '2023-05-16 12:10:41'),
('TRJ022', '0008242317', '', 7, 42500, 0, 42500, '2023-05-16 15:50:33'),
('TRJ023', '0008242317', '', 2, 11500, 0, 11500, '2023-05-16 15:57:15'),
('TRJ024', '0008242317', '', 2, 17500, 0, 17500, '2023-05-16 15:59:45'),
('TRJ025', '0008242317', '', 2, 17500, 0, 17500, '2023-05-16 16:34:26'),
('TRJ026', '0008242317', '', 2, 17500, 0, 17500, '2023-05-16 16:36:33'),
('TRJ027', '0008242317', '0008487459', 15, 109500, 107310, 107310, '2023-05-16 20:47:11'),
('TRJ028', '0008242317', '', 4, 23000, 0, 23000, '2023-05-17 13:58:16'),
('TRJ029', '0008242317', '', 4, 35000, 0, 35000, '2023-05-17 14:11:10'),
('TRJ030', '0008242317', '', 4, 35000, 0, 35000, '2023-05-17 14:30:07');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `detail_transaksi_beli`
--
ALTER TABLE `detail_transaksi_beli`
  ADD PRIMARY KEY (`kode_detail_tr_beli`),
  ADD KEY `kode_tr_beli` (`kode_tr_beli`,`kode_supplier`),
  ADD KEY `kode_supplier` (`kode_supplier`);

--
-- Indeks untuk tabel `detail_transaksi_jual`
--
ALTER TABLE `detail_transaksi_jual`
  ADD PRIMARY KEY (`kode_detail_tr_jual`),
  ADD KEY `kode_tr_jual` (`kode_tr_jual`),
  ADD KEY `kode_karyawan` (`kode_produk`),
  ADD KEY `kode_produk` (`kode_produk`);

--
-- Indeks untuk tabel `karyawan`
--
ALTER TABLE `karyawan`
  ADD PRIMARY KEY (`kode_karyawan`);

--
-- Indeks untuk tabel `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`kode_member`);

--
-- Indeks untuk tabel `produk`
--
ALTER TABLE `produk`
  ADD PRIMARY KEY (`kode_produk`);

--
-- Indeks untuk tabel `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`kode_supplier`);

--
-- Indeks untuk tabel `transaksi_beli`
--
ALTER TABLE `transaksi_beli`
  ADD PRIMARY KEY (`kode_tr_beli`);

--
-- Indeks untuk tabel `transaksi_jual`
--
ALTER TABLE `transaksi_jual`
  ADD PRIMARY KEY (`kode_tr_jual`),
  ADD KEY `kode_tr_jual` (`kode_tr_jual`),
  ADD KEY `kode_member` (`kode_member`),
  ADD KEY `kode_karyawan` (`kode_karyawan`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `detail_transaksi_beli`
--
ALTER TABLE `detail_transaksi_beli`
  MODIFY `kode_detail_tr_beli` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT untuk tabel `detail_transaksi_jual`
--
ALTER TABLE `detail_transaksi_jual`
  MODIFY `kode_detail_tr_jual` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=131;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `detail_transaksi_beli`
--
ALTER TABLE `detail_transaksi_beli`
  ADD CONSTRAINT `detail_transaksi_beli_ibfk_1` FOREIGN KEY (`kode_tr_beli`) REFERENCES `transaksi_beli` (`kode_tr_beli`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detail_transaksi_beli_ibfk_2` FOREIGN KEY (`kode_supplier`) REFERENCES `supplier` (`kode_supplier`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `detail_transaksi_jual`
--
ALTER TABLE `detail_transaksi_jual`
  ADD CONSTRAINT `detail_transaksi_jual_ibfk_1` FOREIGN KEY (`kode_produk`) REFERENCES `produk` (`kode_produk`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detail_transaksi_jual_ibfk_2` FOREIGN KEY (`kode_tr_jual`) REFERENCES `transaksi_jual` (`kode_tr_jual`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `transaksi_jual`
--
ALTER TABLE `transaksi_jual`
  ADD CONSTRAINT `transaksi_jual_ibfk_1` FOREIGN KEY (`kode_karyawan`) REFERENCES `karyawan` (`kode_karyawan`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
