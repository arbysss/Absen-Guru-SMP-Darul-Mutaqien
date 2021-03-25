<?php
$idguru = $_GET['idguru'];
	//Import File Koneksi Database
	require_once('koneksi.php');

	//Membuat SQL Query
	$sql = "SELECT * FROM absenkeluar WHERE idguru=$idguru";

	//Mendapatkan Hasil
	$r = mysqli_query($con,$sql);

	//Membuat Array Kosong
	$result = array();

	while($row = mysqli_fetch_array($r)){

		//Memasukkan Nama dan ID kedalam Array Kosong yang telah dibuat
		array_push($result,array(
			"idabsen"=>$row['idabsenkeluar'],
			"tgl"=>$row['tanggalkeluar'],
			"jam"=>$row['waktukeluar'],
			"idguru"=>$row['idguru']
		));
	}

	//Menampilkan Array dalam Format JSON
	echo json_encode(array('result'=>$result));

	mysqli_close($con);
?>