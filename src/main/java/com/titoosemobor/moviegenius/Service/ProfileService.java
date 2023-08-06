package com.titoosemobor.moviegenius.Service;

import com.titoosemobor.moviegenius.DTO.UserDTORequest;
import com.titoosemobor.moviegenius.DTO.UserProfileDTO;
import com.titoosemobor.moviegenius.DTO.UserProfileDTOMapper;
import com.titoosemobor.moviegenius.Entity.Profile;
import com.titoosemobor.moviegenius.Entity.User;
import com.titoosemobor.moviegenius.Exception.ProfileException;
import com.titoosemobor.moviegenius.Repository.ProfileRepository;
import com.titoosemobor.moviegenius.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {
  @Autowired
  private ProfileRepository profileRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UserProfileDTOMapper userProfileDTOMapper;

  public List<UserProfileDTO> allUserProfiles(UserDTORequest userDTORequest) {
    return profileRepository.findProfilesByUser(userRepository.findUserByEmail(userDTORequest.getEmail()).get())
      .stream()
      .map(userProfileDTOMapper)
      .collect(Collectors.toList());
  }

  public Profile ProfileById(Long profileId) {
    return profileRepository.findProfileById(profileId);
  }

  public UserProfileDTO createProfile(User user, UserProfileDTO userProfileDTO) {
    if (user.getProfiles().size() == 4) {
      throw new ProfileException.MaxNumberOfProfiles("Maximum Number of Profiles");
    }
    if (profileRepository.existsProfileByUserAndProfileName(user, userProfileDTO.getProfileName())) {
      throw new ProfileException.NameAlreadyInUse("Profile name already in use");
    }
    Profile profile = Profile.builder()
      .profileName(userProfileDTO.getProfileName())
      .profileImage(userProfileDTO.getProfileImage())
      .user(user)
      .createdAt(new Timestamp(System.currentTimeMillis()))
      .build();
    profileRepository.save(profile);
    return UserProfileDTOMapper.INSTANCE.apply(profile);
  }

  public UserProfileDTO updateProfile(Long id, UserProfileDTO userProfileDTO) {
    Profile profile = profileRepository.findProfileById(id);
    String newProfileName = userProfileDTO.getProfileName();

    if (!newProfileName.equals(profile.getProfileName())) {
      boolean profileNameExistsForUser = profileRepository.existsProfileByUserAndProfileName(profile.getUser(), newProfileName);
      if (profileNameExistsForUser) {
        throw new ProfileException.NameAlreadyInUse("Profile name is already in use.");
      }
    }
    profile.setProfileName(userProfileDTO.getProfileName());
    profile.setProfileImage(userProfileDTO.getProfileImage());
    profileRepository.save(profile);
    return UserProfileDTOMapper.INSTANCE.apply(profile);
  }

  public UserProfileDTO deleteProfile(Long profileId) {
    Profile profile = profileRepository.findProfileById(profileId);
    profileRepository.deleteById(profileId);
    return UserProfileDTOMapper.INSTANCE.apply(profile);
  }
}
