package com.iprwc2.model;

import com.iprwc2.model.Rights;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private String name;
    private Rights rights;
    private String city;
    private String street;
    private String postalcode;
}
