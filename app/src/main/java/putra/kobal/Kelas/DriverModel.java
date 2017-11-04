package putra.kobal.Kelas;

/**
 * Created by Glory on 10/22/2017.
 */

public class DriverModel {

    String nama;
    String email;
    String kode;
    String pass;
    String trayek;
    String plat_nomr;
    Double lat;
    Double lon;
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DriverModel(String nama, String email, String kode, String pass, String trayek, String plat_nomr, Double lat, Double lon,String status) {
        this.nama = nama;
        this.email = email;
        this.kode = kode;
        this.pass = pass;
        this.trayek = trayek;
        this.plat_nomr = plat_nomr;
        this.lat = lat;
        this.lon = lon;
        this.status = status;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTrayek() {
        return trayek;
    }

    public void setTrayek(String trayek) {
        this.trayek = trayek;
    }

    public String getPlat_nomr() {
        return plat_nomr;
    }

    public void setPlat_nomr(String plat_nomr) {
        this.plat_nomr = plat_nomr;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}
