package vapeShop.mapper;

public interface Mapper<T, R>  {

    T mapToEntity(R object);

    R mapToDTO(T object);
}
