package br.com.kangu;

import br.com.kangu.commons.Endereco;
import com.github.javafaker.Faker;
import io.restassured.http.Header;

import java.util.Locale;

public class GenerateUtils {
    public static Header getTokenReader() {
        return new Header("token", "f46f7836cd54d72b747ebedecd45f55955160b9b39f634fdac6b4d68e891b67d");
        //return new Header("token", "8eb8964223e22d826cd6cc92365c4e10");

    }

    public static Faker getFaker() {
        return new Faker(new Locale("pt-BR"));
    }

    public static Endereco getEndereco() {
        return new Endereco(
                getFaker().address().streetAddress(),
                String.valueOf(getFaker().random().nextInt(0, 999)),
                "CASA",
                "Vila Prudente",
                getFaker().address().zipCode(),
                getFaker().address().city(),
                getFaker().address().state());
    }
}
