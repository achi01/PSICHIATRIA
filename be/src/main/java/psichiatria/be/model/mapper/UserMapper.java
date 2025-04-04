package psichiatria.be.model.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;
import psichiatria.be.model.domain.User;
import psichiatria.be.model.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto user);
}
