package com.example.demo.entity;

import com.example.demo.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "tb_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Convert(converter = EnumConvert.class)
    @Column(name = "status")
    private StatusEnum statusEnum;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createTime;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date updateTime;


     static class EnumConvert implements AttributeConverter<StatusEnum, Integer>{
        @Override
        public Integer convertToDatabaseColumn(StatusEnum attribute) {
            return attribute.getValue();
        }

        @Override
        public StatusEnum convertToEntityAttribute(Integer dbData) {
            return StatusEnum.fromValue(dbData);
        }
    }
}
