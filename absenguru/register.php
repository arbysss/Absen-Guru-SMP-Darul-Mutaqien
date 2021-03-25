	<?php
	include_once "koneksi.php";

	 class usr{}
	 $idguru = $_POST["idguru"];
	 $nama = $_POST["nama"];
	 $jenkel = $_POST["jenkel"];
	 $jabatan = $_POST["jabatan"];
	 $katasandi = $_POST["katasandi"];
	 
	 if ((empty($idguru))) {
	 	$response = new usr();
	 	$response->success = 0;
	 	$response->message = "Kolom idguru tidak boleh kosong";
	 	die(json_encode($response));
	 } else if ((empty($nama))) {
	 	$response = new usr();
	 	$response->success = 0;
	 	$response->message = "Kolom nama tidak boleh kosong";
	 	die(json_encode($response));
	 } else if ((empty($jenkel))) {
	 	$response = new usr();
	 	$response->success = 0;
	 	$response->message = "Kolom jenis kelamin tidak boleh kosong";
	 	die(json_encode($response));
	 } else if ((empty($jabatan))) {
	 	$response = new usr();
	 	$response->success = 0;
	 	$response->message = "Kolom jabatan tidak boleh kosong";
	 	die(json_encode($response));
	 }else if ((empty($katasandi))) {
	 	$response = new usr();
	 	$response->success = 0;
	 	$response->message = "Kolom kata sandi tidak boleh kosong";
	 	die(json_encode($response));
	 } else {
		 if (!empty($idguru)){
		 	$num_rows = mysqli_num_rows(mysqli_query($con, "SELECT * FROM guru WHERE idguru='".$idguru."'"));

		 	if ($num_rows == 0){
		 		$query = mysqli_query($con, "INSERT INTO guru (idguru, nama, jenkel, jabatan, katasandi) VALUES('".$idguru."','".$nama."', '".$jenkel."', '".$jabatan."','".$katasandi."')");

		 		if ($query){
		 			$response = new usr();
		 			$response->success = 1;
		 			$response->message = "Register berhasil, silahkan login.";
		 			die(json_encode($response));

		 		} else {
		 			$response = new usr();
		 			$response->success = 0;
		 			$response->message = "Gagal Mendaftar";
		 			die(json_encode($response));
		 		}
		 	} else {
		 		$response = new usr();
		 		$response->success = 0;
				$response->message = "ID Guru sudah ada";
		 		die(json_encode($response));
		 	}
		 }
	 }
	 mysqli_close($con);
?>