<?php
	include_once "koneksi.php";

	class absen{}
	
	$id = $_POST["id"];
	$tgl = $_POST["tgl"];
	$jam = $_POST["jam"];
	
	
	if (!empty($id && $tgl)){
 		$num_rows = mysqli_num_rows(mysqli_query($con, "SELECT * FROM absenkeluar WHERE tanggalkeluar='".$tgl."' AND idguru='".$id."'"));
		if ($num_rows == 0 ){
	 		$query = mysqli_query($con, "INSERT INTO absenkeluar (idabsenkeluar, tanggalkeluar, waktukeluar, idguru) VALUES(0,'".$tgl."','".$jam."','".$id."')");
			if ($query){
	 			$response = new absen();
	 			$response->success = 1;
	 			$response->message = "Absen berhasil";
	 			die(json_encode($response));
	 		} else {
	 			$response = new absen();
	 			$response->success = 0;
	 			$response->message = "Gagal Absen";
	 			die(json_encode($response));
	 		}
	 	} else {
	 		$response = new absen();
	 		$response->success = 0;
			$response->message = "Maaf anda sudah melaksanakan absen";
	 		die(json_encode($response));
	 	}
	} else {
	 	$response = new absen();
	 	$response->success = 0;
		$response->message = "Maaf anda sudah melaksanakan absen";
 		die(json_encode($response));
	}
	mysqli_close($con);
?>