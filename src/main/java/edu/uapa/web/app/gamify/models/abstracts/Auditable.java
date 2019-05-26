package edu.uapa.web.app.gamify.models.abstracts;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@EntityListeners({AuditingEntityListener.class})
@MappedSuperclass
public class Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @CreatedBy
    String createBy;
    @LastModifiedBy
    String lastModifiedBy;

    @CreatedDate
    Date createdDate;
    @LastModifiedDate
    Date lastModifiedDate;

    @Column(nullable = false)
    boolean enabled;
}
