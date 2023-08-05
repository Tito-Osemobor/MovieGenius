package com.titoosemobor.moviegenius.Service;

import com.titoosemobor.moviegenius.DTO.UserDTORequest;
import com.titoosemobor.moviegenius.DTO.UserProfileDTO;
import com.titoosemobor.moviegenius.DTO.UserProfileDTOMapper;
import com.titoosemobor.moviegenius.Entity.Profile;
import com.titoosemobor.moviegenius.Entity.User;
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

  public UserProfileDTO createProfile(User user, UserProfileDTO userProfileDTO) {
    Profile profile = Profile.builder()
      .profile_name(userProfileDTO.getProfile_name())
      .profile_image(userProfileDTO.getProfile_image())
      .user(user)
      .created_at(new Timestamp(System.currentTimeMillis()))
      .build();
    profileRepository.save(profile);
    return UserProfileDTOMapper.INSTANCE.apply(profile);
  }

  public UserProfileDTO updateProfile(Long id, UserProfileDTO userProfileDTO) {
    Profile profile = profileRepository.findProfileById(id);
    profile.setProfile_name(userProfileDTO.getProfile_name());
    profile.setProfile_image(userProfileDTO.getProfile_image());
    profileRepository.save(profile);
    return UserProfileDTOMapper.INSTANCE.apply(profile);
  }

  public UserProfileDTO deleteProfile(Long profileId) {
    Profile profile = profileRepository.findProfileById(profileId);
    profileRepository.deleteById(profileId);
    return UserProfileDTOMapper.INSTANCE.apply(profile);
  }
}
