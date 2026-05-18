package org.acme.bo;

import java.time.LocalDate;

public class ValidacaoBO {

    public static void validarEmail(String email){
        if (email == null || !email.contains("@") || !email.contains(".")){
            throw new IllegalArgumentException("Email deve conter '@' e '.' ");
        }
    }

    public static void validarCpf(String cpf){
        if (cpf == null){
            throw new IllegalArgumentException("CPF não pode ser nulo!");
        }
        String novoCpf = cpf.replaceAll("[^0-9]", "");
        if (novoCpf.length() != 11){
            throw new IllegalArgumentException("CPF deve conter 11 dígitos!");
        }
    }

    public static void validarCpfOuCnpj(String documento){
        if (documento == null){
            throw new IllegalArgumentException("Documento não pode ser nulo!");
        }
        String novoDoc = documento.replaceAll("[^0-9]", "");
        if (novoDoc.length() != 11 && novoDoc.length() != 14){
            throw new IllegalArgumentException("Documento inválido! Deve conter 11 dígitos (CPF) ou 14 dígitos (CNPJ)!");
        }
    }

    public static void validarTelefone(String telefone){
        if (telefone == null){
            throw new IllegalArgumentException("Telefone não pode ser nulo!");
        }
        String novoTelefone = telefone.replaceAll("[^0-9]", "");
        if (novoTelefone.length() < 10 || novoTelefone.length() > 11){
            throw new IllegalArgumentException("Telefone deve conter 10 ou 11 dígitos!");
        }
    }

    public static void validarNome(String nome){
        if (nome == null){
            throw new IllegalArgumentException("Nome não pode ser nulo!");
        }
        if (nome.trim().length() < 2){
            throw new IllegalArgumentException("Nome deve conter ao menos 2 dígitos!");
        }
    }

    public static void validarValorDoacao(Double valor){
        if (valor == null || valor <= 0){
            throw new IllegalArgumentException("Valor não pode ser nulo!");
        }
    }

    public static void validarCep(String cep){
        if (cep == null){
            throw new IllegalArgumentException("CEP não pode ser nulo!");
        }
        if (cep.length() != 8){
            throw new IllegalArgumentException("CEP deve conter 8 dígitos!");
        }
    }

    public static void validarDataNasc(LocalDate data){
        if (data == null){
            throw new IllegalArgumentException("Data de Nascimento não pode ser nula!");
        }
        if (data.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Data de nascimento não pode ser uma data futura!");
        }
        if (data.isBefore(LocalDate.of(1900, 1, 1))){
            throw new IllegalArgumentException("Data de nascimento não pode ser anterior a 1900!");
        }
    }
}
