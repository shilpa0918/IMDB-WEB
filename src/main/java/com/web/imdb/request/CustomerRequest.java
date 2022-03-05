package com.web.imdb.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CustomerRequest {
    private String customerName;
    private String email;
    private String password;
    private String role;
}
