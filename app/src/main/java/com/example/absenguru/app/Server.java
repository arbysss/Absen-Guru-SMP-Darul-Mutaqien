package com.example.absenguru.app;

public class Server {
    public static final String URL = "http://192.168.1.104/absenguru/";
    public static final String URL_GET_ALL = "http://192.168.1.104/absenguru/tampilSemuaMasuk.php?idguru=";
    public static final String URL_GET_ALLL = "http://192.168.1.104/absenguru/tampilSemuaKeluar.php?idguru=";
    public static final String URL_GET_MSK = "http://192.168.1.104/absenguru/tampilAbsenMasuk.php?idabsenmasuk=";
    public static final String URL_GET_PLG = "http://192.168.1.104/absenguru/tampilAbsenKeluar.php?idabsenkeluar=";
    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_IDGURU = "idguru";
    public static final String TAG_ABSENTGL = "tgl";
    public static final String TAG_ABSENJAM = "jam";
    public static final String TAG_IDABSEN = "idabsen";
    public static final String ABSN_ID = "absn_id";
    public static final String GURU_ID = "guru_id";
}
