package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_plot")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@TypeDef(name="json",typeClass = JSONArray.class)
public class Properties {

    @Id
    @Column(name="plot_id")
    @JsonProperty("plot_id")
    private String plot_id;


    @Column(name = "plot_num")
    @JsonProperty("OBJECTID")
    private Long plot_num;

    @Column(name = "plot_city")
    @JsonProperty("plot_city_")
    private String plot_city_;


    @Column(name ="plot_villa")
    @JsonProperty("plot_villa")
    private String plot_villa;


    @Column(name ="plot_town")
    @JsonProperty("plot_town_")
    private String plot_town_;

    @Column(name ="memo")
    @JsonProperty("memo")
    private String memo;

    @Column(name="name")
    @JsonProperty("plot_contr")
    private String plot_contr;

    @Column(name="plot_con_1")
    @JsonProperty("plot_con_1")
    private String plot_con_1;

    @Column(name="plot_con_2")
    @JsonProperty("plot_con_2")
    private String plot_con_2;

    @Column(name="plot_user")
    @JsonProperty("plot_user")
    private String plot_user_;



    @Column(name="plot_area")
    @JsonProperty("plot_area")
    private BigDecimal plot_area;

    @Column(name="plot_area_")
    @JsonProperty("plot_area")
    private BigDecimal plot_area_;

    @Column(name="plot_plant")
    @JsonProperty("plot_plant")
    private Integer plot_plant;

    @Column(name="plot_pla_1")
    @JsonProperty("plot_pla_1")
    private String plot_pla_1;

    @Column(name="plot_pla_2")
    @JsonProperty("plot_pla_2")
    private String plot_pla_2;

    @Column(name="coordinate")
    @JsonProperty("coordinate")
    private String coordinate;

    @Column(name="shape_leng")
    @JsonProperty("SHAPE_Leng")
    private BigDecimal SHAPE_Leng;

    @Column(name="shape_area")
    @JsonProperty("SHAPE_Area")
    private BigDecimal SHAPE_Area;

    @Column(name="is_push")
    @JsonProperty("是否可推")
    private String isPush;

    @Column(name="link")
    @JsonProperty("link")
    private String link;

    @Column(name="is_yn")
    @JsonProperty("IFYN")
    private String isYn;

    @Column(name="plot_suer")
    @JsonProperty("plot_suer_")
    private String plot_suer_;

    @Column(name="remark")
    @JsonProperty("备注")
    private String remark;

    @Column(name="inspect")
    @JsonProperty("检查")
    private String inspect;

   // @Type(type = "json")
    @Column(name = "coordinates",columnDefinition = "json")
    private String coordinates;
}
