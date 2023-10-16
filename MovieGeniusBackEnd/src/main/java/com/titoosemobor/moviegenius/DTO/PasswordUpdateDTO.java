package com.titoosemobor.moviegenius.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PasswordUpdateDTO {
  private String email;
  private String currentPassword;
  private String newPassword;
  private String reNewPassword;
}
