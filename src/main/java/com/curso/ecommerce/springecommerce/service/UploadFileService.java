
package com.curso.ecommerce.springecommerce.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {
    private String folder = "images//";
    
    public String saveImage(MultipartFile file) throws IOException{
        if (!file.isEmpty()) {
            //Convertimos la imagen a ceros y unos para que pueda enviarse 
            //del cliente al servidor y lo guardo en un arreglo
            byte [] bytes = file.getBytes();
            //Pasamos la URI a donde queremos almacenar nuestra imagen
            //Y guardamos todo en path
            Path path = Paths.get(folder + file.getOriginalFilename());
            //Escribe la ruta que le estamos enviando
            Files.write(path, bytes);    
            //Retorno el nombre que tiene mi imagen
            return file.getOriginalFilename();
        }
        return "default.jpg";
    }
    
    //Recibe por parámetro el nombre de la imagen
    public void deleteImage(String nombre){
        String ruta = "images//";
        File file = new File(ruta + nombre);
        file.delete();
    }
}
