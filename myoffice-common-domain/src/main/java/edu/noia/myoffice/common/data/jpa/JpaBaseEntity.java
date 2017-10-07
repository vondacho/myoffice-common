package edu.noia.myoffice.common.data.jpa;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class JpaBaseEntity {

    @Id
    @GenericGenerator(
            name = "ID_GENERATOR",
            strategy = "enhanced-sequence",
            parameters = {
                    @Parameter(name = "sequence_name", value = "seq_myoffice_sequence"),
                    @Parameter(name = "increment_size", value = "100"),
                    @Parameter(name = "optimizer", value = "pooled-lo")
            })
    @GeneratedValue(generator = "ID_GENERATOR")
    @Column(name = "pk_id")
    Long primaryId;
}
