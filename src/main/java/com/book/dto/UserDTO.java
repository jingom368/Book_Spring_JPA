package com.book.dto;

import com.book.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String gender;
    private String phone;

        // Entity -> DTO로 이동
        public UserDTO(UserEntity userEntity) {
            this.id = userEntity.getId();
            this.name = userEntity.getName();
            this.email = userEntity.getEmail();
            this.gender = userEntity.getGender();
            this.phone = userEntity.getPhone();
        }
    
        // DTO -> Entity로 이동 (Builder 패턴 사용)
        public UserEntity dtoToEntity(UserDTO userDTO) {
            UserEntity userEntity = UserEntity.builder()
                                    .id(userDTO.getId())
                                    .name(userDTO.getName())
                                    .email(userDTO.getEmail())
                                    .gender(userDTO.getGender())
                                    .phone(userDTO.getPhone())
                                    .build();
            return userEntity;
        }
}
