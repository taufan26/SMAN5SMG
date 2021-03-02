package app.klikgss.sman5smg.model.login;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("nama")
	private String nama;

	@SerializedName("foto")
	private String foto;

	@SerializedName("id_login")
	private String idLogin;

	@SerializedName("id_pegawai")
	private String idPegawai;

	@SerializedName("username")
	private String username;

	@SerializedName("id_siswa")
	private String idSiswa;

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setFoto(String foto){
		this.foto = foto;
	}

	public String getFoto(){
		return foto;
	}

	public void setIdLogin(String idLogin){
		this.idLogin = idLogin;
	}

	public String getIdLogin(){
		return idLogin;
	}

	public void setIdPegawai(String idPegawai){
		this.idPegawai = idPegawai;
	}

	public String getIdPegawai(){
		return idPegawai;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public void setIdSiswa(String idSiswa){
		this.idSiswa = idSiswa;
	}

	public String getIdSiswa(){
		return idSiswa;
	}
}