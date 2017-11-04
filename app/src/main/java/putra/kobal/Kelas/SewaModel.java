package putra.kobal.Kelas;

/**
 * Created by Glory on 10/27/2017.
 */

public class SewaModel {
    String nama;
    String nope;
    String email;

    public SewaModel(String nama, String nope, String email) {
        this.nama = nama;
        this.nope = nope;
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNope() {
        return nope;
    }

    public void setNope(String nope) {
        this.nope = nope;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
