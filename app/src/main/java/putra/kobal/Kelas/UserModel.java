package putra.kobal.Kelas;

/**
 * Created by Glory on 10/26/2017.
 */

public class UserModel {
    String nama;
    String email;
    String pass;
    Double lat;
    Double lon;
    String nope;

    public String getNope() {
        return nope;
    }

    public void setNope(String nope) {
        this.nope = nope;
    }

    public UserModel(String nama, String email, String pass, Double lat, Double lon,String nope ) {
        this.nama = nama;
        this.email = email;
        this.pass = pass;
        this.lat = lat;
        this.lon = lon;
        this.nope = nope;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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
