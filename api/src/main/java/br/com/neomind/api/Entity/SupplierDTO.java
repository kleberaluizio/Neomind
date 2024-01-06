package br.com.neomind.api.Entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.Objects;

public class SupplierDTO {

    @NotBlank(message = "O nome não pode estar em branco")
    private String name;
    @Email(message = "Por favor, digite um e-mail válido")
    private String email;
    private String comment;
    @NotBlank
    @Pattern(regexp = "^\\d{2}\\.\\d{3}/\\d{4}-\\d{2}$", message = "Your custom error message goes here")
    private String cnpj;

    public SupplierDTO(){
    }
    public SupplierDTO(String name,String email,String comment,String cnpj) {
        this.name = name;
        this.email = email;
        this.comment = comment;
        this.cnpj = cnpj;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        SupplierDTO that = (SupplierDTO) object;
        return Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(comment, that.comment) && Objects.equals(cnpj, that.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, comment, cnpj);
    }

    @Override
    public String toString() {
        return "Supplier{" +
                ", name='" + name + '\'' +
                ", cnpj='" + cnpj + '\'' +
                '}';
    }
}

