
package com.empleate.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FirebaseStorageService {

    public String cargaImagen(MultipartFile archivoLocalCliente, String carpeta, Long id);

    final String BucketName = "proyectoweb-195c6.firebasestorage.app";

    final String rutaSuperiorStorage = "proyectoweb";

    final String rutaJsonFile = "firebase";
    
    final String archivoJsonFile = "proyectoweb-195c6-firebase-adminsdk-fu2f3-2bb2c163ba.json";
}