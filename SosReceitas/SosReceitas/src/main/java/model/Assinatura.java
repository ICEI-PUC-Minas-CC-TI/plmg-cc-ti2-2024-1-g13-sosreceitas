package model;
import java.time.LocalDate;


public class Assinatura {
	
	private int id_assinatura;
	private LocalDate data_inicio;
	private LocalDate data_fim;
	private Usuario usuario;
	private int usuario_id;
	
	
	public Assinatura(Usuario user) {
		super();
		this.usuario = user;
		id_assinatura = -1;
		data_inicio = LocalDate.now();
		data_fim = data_inicio.plusMonths(3);
	}
	
	public Assinatura(int x) {
		super();
		this.usuario_id = x;
		id_assinatura = -1;
		data_inicio = LocalDate.now();
		data_fim = data_inicio.plusMonths(3);
	}
	
	public Assinatura(int id, LocalDate d_i, LocalDate d_f, int user_id){
		super();
		this.id_assinatura = id;
		this.data_inicio = d_i;
		this.data_fim = d_f;
		this.usuario_id = user_id;
		
	}
	
	
	
	
	
	// Getters e setters
    public int getId_assinatura() {
        return id_assinatura;
    }

    public void setId_assinatura(int id_assinatura) {
        this.id_assinatura = id_assinatura;
    }

    public LocalDate getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(LocalDate data_inicio) {
        this.data_inicio = data_inicio;
    }

    public LocalDate getData_fim() {
        return data_fim;
    }

    public void setData_fim(LocalDate data_fim) {
        this.data_fim = data_fim;
    }
    
    public Usuario getUsuario_assinatura(){
    	return usuario;
    }
    
    public void setUsuario_assinatura(Usuario user) {
    	this.usuario = user;
    }
    
    public int getUser_id() {
		return usuario_id;
	}
	
	public void setUser_id(int x) {
		this.usuario_id = x;
	}
	
	@Override
	public String toString() {
		return "Assinatura[Id_assinatura: " + id_assinatura + "   Data_inicio:" + data_inicio + "   data_fim: " + data_fim +  "]";
			
	}
	
    
	

}
