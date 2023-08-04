package com.titoosemobor.moviegenius.Controller;

import com.titoosemobor.moviegenius.DTO.UserDTORequest;
import com.titoosemobor.moviegenius.DTO.UserProfileDTO;
import com.titoosemobor.moviegenius.Entity.Profile;
import com.titoosemobor.moviegenius.Entity.User;
import com.titoosemobor.moviegenius.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moviegenius/users/profiles")
public class ProfileController {
  @Autowired
  private ProfileService profileService;

  @GetMapping
  public ResponseEntity<List<UserProfileDTO>> getAllUserProfiles() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User authUser =  (User) authentication.getPrincipal();
    UserDTORequest userDTORequest = new UserDTORequest(authUser.getUsername());
    return new ResponseEntity<>(profileService.allUserProfiles(userDTORequest), HttpStatus.OK);
  }

  @PostMapping("/create")
  public ResponseEntity<UserProfileDTO> createNewUserProfile(@RequestBody UserProfileDTO userProfileDTO) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) authentication.getPrincipal();
    UserProfileDTO createdProfile = profileService.createProfile(user, userProfileDTO);
    return ResponseEntity.ok(createdProfile);
  }

  @PutMapping("/update/{profileId}")
  public ResponseEntity<UserProfileDTO> updateUserProfile(@PathVariable Long profileId, @RequestBody UserProfileDTO userProfileDTO) {
    UserProfileDTO updateProfileDTO = profileService.updateProfile(profileId, userProfileDTO);
    return ResponseEntity.ok(updateProfileDTO);
  }
}
