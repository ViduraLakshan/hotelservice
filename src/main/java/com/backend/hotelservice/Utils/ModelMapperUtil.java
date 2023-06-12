package com.backend.hotelservice.Utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.DestinationSetter;
import org.modelmapper.spi.SourceGetter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ModelMapperUtil {
    private static final ModelMapper modelMapper = new ModelMapper();

    public ModelMapperUtil() {
    }

    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return (List) source.stream().map((element) -> {
            return modelMapper.map(element, targetClass);
        }).collect(Collectors.toList());
    }

    public static <S, T> Set<T> mapSet(Set<S> source, Class<T> targetClass) {
        return source.stream().map((element) -> {
            return modelMapper.map(element, targetClass);
        }).collect(Collectors.toSet());
    }

    public static <S, T> T map(S source, Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }

    public static <S, T, V> void addPropertyMapping(Class<S> sourceClass, Class<T> targetClass, SourceGetter<S> sourceGetter, DestinationSetter<T, V> destinationSetter) {
        TypeMap<S, T> propertyMapper = modelMapper.createTypeMap(sourceClass, targetClass);
        propertyMapper.addMapping(sourceGetter, destinationSetter);
    }
}
