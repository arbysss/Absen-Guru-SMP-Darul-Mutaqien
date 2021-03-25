<?php
	include_once "koneksi.php";

	class usr{}
	
	$idguru = $_POST["idguru"];
	$katasandi = $_POST["katasandi"];
	
	if ((empty($idguru)) || (empty($katasandi))) { 
	$response = new usr();
	 	$response->success = 0;
	 	$response->message = "Kolom tidak boleh kosong"; 
	 	die(json_encode($response));
	 }
	
	 $query = mysqli_query($con, "SELECT * FROM guru WHERE idguru='$idguru' AND katasandi='$katasandi'");
	
	 $row = mysqli_fetch_array($query);
	
	if (!empty($row)){
		$response = new usr();
		$response->success = 1;
		$response->message = "Selamat datang ".$row['idguru'];
		$response->idguru = $row['idguru'];
		die(json_encode($response));
		
	} else { 
		$response = new usr();
		$response->success = 0;
		$response->message = "idguru atau katasandi salah";
		die(json_encode($response));
	}
	
	mysqli_close($con);


?>