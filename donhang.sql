-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Máy chủ: localhost:3306
-- Thời gian đã tạo: Th6 11, 2019 lúc 11:17 PM
-- Phiên bản máy phục vụ: 10.1.23-MariaDB-9+deb9u1
-- Phiên bản PHP: 7.0.33-0+deb9u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `nhasach`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `donhang`
--

CREATE TABLE `donhang` (
  `madonhang` int(11) NOT NULL,
  `mathanhvien` int(11) DEFAULT NULL,
  `ngaylap` timestamp NULL DEFAULT NULL,
  `tongtien` double NOT NULL,
  `trangthai` int(11) NOT NULL DEFAULT '1' COMMENT 'trạng thái đơn hàng, -1: Chờ xác nhận, 0: bị hủy, 1: đang tiếp nhận, 2: đang giao hàng, 3: đã hoàn tất',
  `diachigiaohang` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `maphiship` int(11) DEFAULT NULL,
  `phiship` double DEFAULT NULL,
  `sodienthoai` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `ghichu` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `donhang`
--

INSERT INTO `donhang` (`madonhang`, `mathanhvien`, `ngaylap`, `tongtien`, `trangthai`, `diachigiaohang`, `maphiship`, `phiship`, `sodienthoai`, `ghichu`) VALUES
(117, 61, '2019-06-03 17:00:00', 13000, 3, 'ĐH CNTT', 78, 25000, '0358708844', '123'),
(120, 61, '2019-06-03 17:00:00', 45000, 3, 'ĐH CNTT', 142, 40000, '0358708844', ''),
(121, 61, '2019-06-04 17:00:00', 32000, 3, 'ĐH CNTT', 81, 18000, '0358708844', ''),
(124, 68, '2019-06-04 17:00:00', 270000, 0, '612 Lê Đức Thọ, P. 15, Gò Vấp, TP. HCM', 142, 40000, '0909285622', ''),
(125, 68, '2019-06-06 17:23:11', 600000, -1, '', 137, 40000, '0909288952', ''),
(126, 61, '2019-06-07 17:00:00', 451200, 3, 'ĐH CNTT', 142, 20000, '0358708844', ''),
(127, 61, '2019-06-08 17:00:00', 431200, 0, 'ĐH CNTT', 128, 20000, '0358708844', ''),
(129, 46, '2019-06-08 17:00:00', 49200, 0, '243 đường 8,Phường 11, Quận Gò Vấp, Hồ Chí Minh', 72, 18000, '0902519819', ''),
(130, 46, '2019-06-09 17:00:00', 49200, 0, '243 đường 8,Phường 11, Quận Gò Vấp, Hồ Chí Minh', 72, 18000, '0902519819', ''),
(131, 46, '2019-06-09 17:00:00', 25850, 3, '243 đường 8,Phường 11, Quận Gò Vấp, Hồ Chí Minh', 72, 18000, '0902519819', 'giao hàng buổi trưa nhé'),
(132, 46, '2019-06-09 17:00:00', 45500, 3, '243 đường 8,Phường 11, Quận Gò Vấp, Hồ Chí Minh', 127, 20000, '0902519819', '213123'),
(133, 61, '2019-06-11 14:37:11', 95900, -1, 'ĐH CNTT', 139, 25000, '0358708844', '');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`madonhang`),
  ADD KEY `fk_hoadon_phiship1_idx` (`maphiship`),
  ADD KEY `fk_hoadon_thanhvien1_idx` (`mathanhvien`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `donhang`
--
ALTER TABLE `donhang`
  MODIFY `madonhang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=134;
--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD CONSTRAINT `fk_hoadon_phiship1` FOREIGN KEY (`maphiship`) REFERENCES `phiship` (`maphiship`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hoadon_thanhvien1` FOREIGN KEY (`mathanhvien`) REFERENCES `thanhvien` (`mathanhvien`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
