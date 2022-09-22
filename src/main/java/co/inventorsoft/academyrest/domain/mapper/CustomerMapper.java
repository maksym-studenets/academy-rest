package co.inventorsoft.academyrest.domain.mapper;

import co.inventorsoft.academyrest.domain.Customer;
import co.inventorsoft.academyrest.domain.dto.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(ignore = true, target = "id")
    Customer toEntity(CustomerDto dto);

    CustomerDto toDto(Customer customer);

    @Mapping(target = "id", ignore = true)
    void update(CustomerDto dto, @MappingTarget Customer entity);
}
