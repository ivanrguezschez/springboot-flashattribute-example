package com.irs.springbootflashattributeexample.presentacion.comandos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerEditCommand {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
}
