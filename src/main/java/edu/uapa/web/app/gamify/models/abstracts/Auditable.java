package edu.uapa.web.app.gamify.models.abstracts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners({AuditingEntityListener.class})
@MappedSuperclass
@JsonIgnoreProperties(value = {"createdBy", "lastModifiedBy", "createdDate", "lastModifiedDate", "hibernateLazyInitializer", "handler"})
public abstract class Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;
    @LastModifiedBy
    private String lastModifiedBy;

    @CreatedDate
    @Column(updatable = false)
    private Date createdDate;
    @LastModifiedDate
    private Date lastModifiedDate;

    @Column(nullable = false)
    private boolean enabled = true;


    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (this == o) {
            return true;
        } else if (!this.getClass().equals(o.getClass())) {
            return false;
        }
        return this.getId().equals(((Auditable) o).id);
    }

    @Override
    public int hashCode() {
        if (getId() != null) {
            return getId().hashCode();
        }
        return super.hashCode();
    }

    //TODO A causa de que e;l id siempre tiene un valor, se debe realizar un proceso de consulta extra en el save
    public Long getId() {
        if (id == null)
            id = 0L;
        return id;
    }
}
