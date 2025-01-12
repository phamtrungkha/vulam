package com.lvl.Entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(length = 1000, columnDefinition = "NVARCHAR(1000)")
    private String description;

    @Column
    private Integer discount;

    @Column
    private Date enteredDate;

    @Column(length = 255, columnDefinition = "NVARCHAR(255)")
    private String image;

    @Column(length = 255, columnDefinition = "NVARCHAR(255)")
    private String name;

    private Double price;

    private Integer quantity;

    private Integer sold;

    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference // Ngăn đệ quy khi liên kết ngược với Category
    private Category category;
}

