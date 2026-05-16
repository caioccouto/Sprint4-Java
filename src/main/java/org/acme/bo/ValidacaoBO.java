package org.acme.bo;

public class ValidacaoBO {

    public static void validarEmail(String email){
        if (email == null || !email.contains("@") || !email.contains(".")){
            throw new IllegalArgumentException("Email deve conter '@' e '.' ");
        }
    }
}
