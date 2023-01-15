package locacaomidias.entidades;

import javax.validation.constraints.NotNull;

public class Exemplar {
    @NotNull
    private Long codigo_interno;
    
    @NotNull
    private boolean disponivel;
    
    @NotNull
    private Midia midia;

    public Long getCodigo_interno() {
        return codigo_interno;
    }

    public void setCodigo_interno(Long codigo_interno) {
        this.codigo_interno = codigo_interno;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Midia getMidia() {
        return midia;
    }

    public void setMidia(Midia midia) {
        this.midia = midia;
    }
    
    
}
