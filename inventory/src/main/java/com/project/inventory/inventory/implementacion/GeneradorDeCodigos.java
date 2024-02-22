package com.project.inventory.inventory.implementacion;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GeneradorDeCodigos {
    
    public String GeneradorDeCodigoUsuarios() {
        char[] mayusculas = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'X', 'Y', 'Z'};
       // char[] minuscula = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'x', 'y', 'z'};
        char[] numeros = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        StringBuilder caracteres = new StringBuilder();
        caracteres.append(mayusculas);
        //caracteres.append(minuscula);
        caracteres.append(numeros);
        StringBuilder codigo = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int Cantidadcodigo = caracteres.length();
            int NumeroRamdom = (int) (Math.random() * Cantidadcodigo);
            codigo.append((caracteres.toString()).charAt(NumeroRamdom));
        }       
        return codigo.toString();
    }
    public String generadorPasswordRecover() {
        char[] mayusculas = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'X', 'Y', 'Z'};
       // char[] minuscula = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'x', 'y', 'z'};
        char[] numeros = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        StringBuilder caracteres = new StringBuilder();
        caracteres.append(mayusculas);
        //caracteres.append(minuscula);
        caracteres.append(numeros);
        StringBuilder codigo = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int Cantidadcodigo = caracteres.length();
            int NumeroRamdom = (int) (Math.random() * Cantidadcodigo);
            codigo.append((caracteres.toString()).charAt(NumeroRamdom));
        }       
        return codigo.toString();
    }
    public String passwordSecurity(String passwordSecurity){
        try {
            // Obtener instancia de MessageDigest con el algoritmo SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Aplicar el algoritmo de hash a la entrada
            byte[] hash = digest.digest(passwordSecurity.getBytes());
            // Convertir el hash byte a hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                // Convertir byte a formato hexadecimal y agregar al StringBuilder
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Manejar caso de algoritmo no válido
            e.printStackTrace();
            return null;
        }
    }

}


