package br.edu.ifpb.main;

import br.edu.ifpb.domain.Pessoa;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class App {

    public static void main (String[] args){

        List<String> names = Arrays.asList("John", "Jack", "Hamilton", "George");

        //old method
        System.out.println("Lista filtrada pelo meio antigo:");
        for (String name : names) {
            if (name.startsWith("J")) {
                System.out.println(name);
            }
        }

        //new method with streams and filters
        List<String> result = names.stream()
                .filter(name -> name.startsWith("J"))
                .collect(Collectors.toList());
        System.out.println("\nLista filtrada com streams e filters:");
        System.out.println(result);

        //imprimindo valores da lista com expressão lambda
        System.out.println("\nPrintando Valores da Lista Filtrada com Expressão Lambda:");
        result.forEach(element -> System.out.println(element));

        //imprimindo valores da lista com a dedução de expressão
        // lambda de println deduzida pelo java
        System.out.println("\nPrintando Valores da Lista Filtrada com Expressão Lambda de println deduzida pelo Java:");
        result.forEach(System.out::println);

        //alterando a saída com collectors
        String result1 = names.stream()
                .filter(name -> name.startsWith("J"))
                .collect(Collectors.joining(", "));
        System.out.println("\nSaída com adição de ',':");
        System.out.println(result1);

        //streams e filters com optional
        Optional<String> result2 = names.stream()
                .filter(name -> name.startsWith("W"))
                .findAny();
        System.out.println("\nSaída com optional:");
        if (!result2.isPresent()) System.out.println("Not found");
        else System.out.println(result2);

//        //lançando uma exceção direto da busca
//        Optional<String> result3 = Optional.of(names.stream()
//                .filter(name -> name.startsWith("W"))
//                .findAny()
//                .orElseThrow(IllegalStateException::new));

        List<Pessoa> pessoas = Arrays.asList(new Pessoa("John", 26),
                new Pessoa("Jack", 40),
                new Pessoa("Hamilton", 14),
                new Pessoa("George", 63));

        //brincando com map: filtrando por idade e uppercase nos nomes
        System.out.println("\nPrintando as pessoas com idade maior que 20 com nomes em caixa alta:");
        pessoas.stream().filter(p -> p.getIdade()  > 20)
                .map(p -> p.getNome().toUpperCase())
                .forEach(System.out::println);

        //pegando as idades maiores que 20 com map
        System.out.println("\nPrintando as idades maiores que 20:");
        pessoas.stream()
                .filter(p -> p.getIdade() > 20)
                .mapToInt(Pessoa::getIdade)
                .forEach(System.out::println);

        //fazendo operações com resutado da stream: somando todas as idades maiores que 20
        System.out.println("\nPrintando a soma das idades maiores que 20:");
        OptionalInt result4 = pessoas.stream()
                .filter(p -> p.getIdade() > 20)
                .mapToInt(Pessoa::getIdade)
                .reduce((a, b) -> a + b);
        if (result4.isPresent()) System.out.println(result4.getAsInt());

        //fazendo operações com resutado da stream: somando todas as idades maiores que 20 com função dedicada
        System.out.println("\nPrintando a soma das idades maiores que 20:");
        int result5 = pessoas.stream()
                .filter(p -> p.getIdade() > 20)
                .mapToInt(Pessoa::getIdade)
                .sum();
        System.out.println(result5);

    }

}
