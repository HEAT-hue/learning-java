package com.melbourne.band.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public final class CustomerAuthRequestDto {
    private String email;
    private String password;
}
