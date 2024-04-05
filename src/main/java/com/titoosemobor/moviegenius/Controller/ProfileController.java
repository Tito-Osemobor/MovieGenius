package com.titoosemobor.moviegenius.Controller;

import com.titoosemobor.moviegenius.DTO.UserDTORequest;
import com.titoosemobor.moviegenius.DTO.UserProfileDTO;
import com.titoosemobor.moviegenius.Entity.Profile;
import com.titoosemobor.moviegenius.Entity.User;
import com.titoosemobor.moviegenius.Exception.ProfileException;
import com.titoosemobor.moviegenius.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "${allowed.origins}")
@RequestMapping("/moviegenius/user/profiles")
public class ProfileController {
  @Value("${allowed.origins}")
  private String allowedOrigins;

  @Autowired
  private ProfileService profileService;

  @GetMapping
  public ResponseEntity<List<UserProfileDTO>> getAllUserProfiles(@AuthenticationPrincipal User authUser) {
    UserDTORequest userDTORequest = new UserDTORequest(authUser.getUsername());
    return new ResponseEntity<>(profileService.allUserProfiles(userDTORequest), HttpStatus.OK);
  }

  @PostMapping("/create")
  public ResponseEntity<?> createNewUserProfile(@RequestBody UserProfileDTO userProfileDTO,
                                                             @AuthenticationPrincipal User authUser) {
    try {
      UserProfileDTO createdProfile = profileService.createProfile(authUser, userProfileDTO);
      return ResponseEntity.ok(createdProfile);
    } catch (ProfileException.MaxNumberOfProfiles | ProfileException.NameAlreadyInUse ex) {
      return ResponseEntity.badRequest().body(ex.getMessage());
    }
   }

  @PutMapping("/update/{profileId}")
  public ResponseEntity<?> updateUserProfile(@PathVariable Long profileId,
                                                          @RequestBody UserProfileDTO userProfileDTO,
                                                          @AuthenticationPrincipal User authUser) {
    try {
      if (isOwnedByAuthenticatedUser(profileService.ProfileById(profileId), authUser)) {
        UserProfileDTO updateProfileDTO = profileService.updateProfile(profileId, userProfileDTO);
        return ResponseEntity.ok(updateProfileDTO);
      }
      throw new ProfileException.ProfileNotFound("Profile not found");
    } catch (ProfileException.NameAlreadyInUse | ProfileException.ProfileNotFound ex) {
      return ResponseEntity.badRequest().body(ex.getMessage());
    }
  }

  @DeleteMapping("/delete/{profileId}")
  public ResponseEntity<?> deleteUserProfile(@PathVariable Long profileId,
                                                          @AuthenticationPrincipal User authUser) {
    try {
      if (isOwnedByAuthenticatedUser(profileService.ProfileById(profileId), authUser)) {
        UserProfileDTO deleteProfileDTO = profileService.deleteProfile(profileId);
        return ResponseEntity.ok(deleteProfileDTO);
      }
      throw new ProfileException.ProfileNotFound("Profile not found");
    } catch (ProfileException.ProfileNotFound ex) {
      return ResponseEntity.badRequest().body(ex.getMessage());
    }
  }

  @GetMapping("/pictures")
  public ResponseEntity<?> getAllProfilePictures(@AuthenticationPrincipal User authUser) throws IOException {
    try {
      return ResponseEntity.ok(profileService.allProfilePictureNames(authUser));
    } catch (ProfileException.ProfilePictureNotFound ex) {
      return ResponseEntity.badRequest().body(ex.getMessage());
    }
  }

  @GetMapping("/pictures/{fileName:.+}")
  public ResponseEntity<?> getProfilePicture(@PathVariable String fileName) throws IOException {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.IMAGE_JPEG);
      return ResponseEntity.ok()
        .headers(headers)
        .body(profileService.profilePicture(fileName));
    } catch (ProfileException.ProfileNotFound ex) {
      return ResponseEntity.badRequest().body(ex.getMessage());
    }
  }

  public boolean isOwnedByAuthenticatedUser(Profile requestProfileUser, User authUser) {
    return authUser.getEmail().equals(requestProfileUser.getUser().getEmail());
  }
}
