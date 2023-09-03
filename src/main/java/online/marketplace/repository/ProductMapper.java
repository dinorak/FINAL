package online.marketplace.repository;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.jpa.repository.JpaRepository;


import online.marketplace.entity.Product;


@Mapper(componentModel = "spring")
public interface ProductMapper {
	
	@Mapping(source = "productId", target = "id")
    ProductDao toDTO(Product product);

    @Mapping(source = "id", target = "productId")
    Product toEntity(ProductDao productDao);
}
