import java.io.Serializable;
import java.util.Objects;

public class Entidade implements Serializable {

    private int id;
    private boolean editado;
    private boolean excluido;

    public Entidade() {
        this.editado = false;
        this.excluido = false;
    }

    public Entidade(int id) {
        this.id = id;
        this.editado = false;
        this.excluido = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEditado() {
        return editado;
    }

    public void setEditado(boolean editado) {
        this.editado = editado;
    }

    public boolean isExcluido() {
        return excluido;
    }

    public void setExcluido(boolean excluido) {
        this.excluido = excluido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entidade entidade = (Entidade) o;
        return editado == entidade.editado &&
                excluido == entidade.excluido &&
                Objects.equals(id, entidade.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, editado, excluido);
    }

    @Override
    public String toString() {
        return "Entidade{" +
                "id=" + id +
                ", editado=" + editado +
                ", excluido=" + excluido +
                '}';
    }
}
