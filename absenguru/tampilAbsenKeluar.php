<?php


	//Mendapatkan Nilai Dari Variable kdbook yang ingin ditampilkan
	$idabsen = $_GET['idabsenkeluar'];

	//Importing database
	require_once('koneksi.php');

	//Membuat SQL Query dengan pegawai yang ditentukan secara spesifik sesuai kdbook
	$sql = "SELECT * FROM absenkeluar WHERE idabsenkeluar=$idabsen";

	//Mendapatkan Hasil
	$r = mysqli_query($con,$sql);

	//Memasukkan Hasil Kedalam Array
	$result = array();
	$row = mysqli_fetch_array($r);
	array_push($result,array(
			"idabsen"=>$row['idabsenkeluar'],
			"tgl"=>$row['tanggalkeluar'],
			"jam"=>$row['waktukeluar'],
			"idguru"=>$row['idguru']
		));

	//Menampilkan dalam format JSON
	echo json_encode(array('result'=>$result));

	mysqli_close($con);
?>
