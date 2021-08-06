package br.com.zupacademy.osmarjunior.mercadolivre.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UploaderImage {

    @Value("${mercadolivre.io.images}")
    private String localToStorage;

    public Set<String> send(List<MultipartFile> images) {
        return images
                .stream()
                .map(image -> localToStorage
                                .concat(UUID.randomUUID().toString())
                                .concat("/").concat(image.getOriginalFilename()))
                .collect(Collectors.toSet());
    }
}
