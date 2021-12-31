package com.smj.productapp.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Products {
    @Id
    Long id;
    String name;
    Float price;
}
