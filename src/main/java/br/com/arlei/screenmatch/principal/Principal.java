package br.com.arlei.screenmatch.principal;

import br.com.arlei.screenmatch.model.*;
import br.com.arlei.screenmatch.repository.SerieRepository;
import br.com.arlei.screenmatch.service.ConsumoApi;
import br.com.arlei.screenmatch.service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;


public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";
    private List<DadosSerie> dadosSeries = new ArrayList<>();
    private SerieRepository repositorio;
    private List<Serie> series = new ArrayList<>();

    public Principal(SerieRepository repositorio) {

        this.repositorio = repositorio;
    }

    public void exibeMenu() {
        var opcao =-1;

        while(opcao != 0) {

            var menu = """
                    1 - Buscar séries
                    2 - Buscar episódios
                    3 - Listar Séries Buscadas
                    4 - Buscar Séries por Titulo
                    5 - Buscar séries por ator
                    6 - Top 5 Séries
                    7 - Buscar Séries por Genero
                    8 - Buscar Séries por Avaliação e Total de Temporadas
                                    
                    0 - Sair                                 
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    listarSeriesBuscadas();
                    break;
                case 4:
                    BuscarSeriePorTitulo();
                    break;
                case 5:
                    BuscarSeriePorAtor();
                    break;
                case 6:
                    BuscarTopSeries();
                    break;
                case 7:
                    BuscarPorGenero();
                    break;
                case 8:
                    BuscarPorAvaliacaoTotalTempradas();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void BuscarPorAvaliacaoTotalTempradas() {

        System.out.println("Digite a avaliação mínima");
        var avaliacao = leitura.nextDouble();
        System.out.println("Digite o total de temporadas");
        var totalTemporadas = leitura.nextInt();

        try {
            List<Serie> series =
                    repositorio.findByAvaliacaoGreaterThanAndTotalTemporadasLessThanEqual(avaliacao
                            , totalTemporadas);
            series.forEach(System.out::println);

        }
        catch (Exception e) {
            System.out.println("Nenhuma série encontrada com os parâmetros fornecidos");
        }

    }

    private void BuscarPorGenero() {

        System.out.println("Deseja buscar séries de que categoria/gênero? ");
        var nomeGenero = leitura.nextLine();
        try {
            Categoria categoria = Categoria.fromPortugues(nomeGenero);
            List<Serie> seriesPorCategoria = repositorio.findByGenero(categoria);
            System.out.println("Séries da categoria " + nomeGenero);
            seriesPorCategoria.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Nenhuma categoria encontrada para a string fornecida: " + nomeGenero);
        }
    }

    private void BuscarTopSeries() {

        List<Serie> serieTop = repositorio.findTop5ByOrderByAvaliacaoDesc();
        serieTop.forEach(s ->
                System.out.println(s.getTitulo() + " avaliação: " + s.getAvaliacao()));

//        System.out.println("Digite a quantidade de séries que deseja buscar");
//        var quantidade = leitura.nextInt();
//        var series = repositorio.findAll();
//
//        List<Serie> topSeries = series.stream()
//                .sorted(Comparator.comparing(Serie::getAvaliacao).reversed())
//                .limit(quantidade)
//                .collect(Collectors.toList());
//
//        topSeries.forEach(System.out::println);
    }

    private void BuscarSeriePorAtor() {

        System.out.println("Digite o nome do ator para busca");
        var nomeAtor = leitura.nextLine();
        System.out.println("Digite a paritr de qual nota de avaliação você quer buscar");
        var nota = leitura.nextDouble();

        List<Serie> series
                = repositorio.findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThan(nomeAtor, nota);

        series.forEach(System.out::println);





    }

    private void BuscarSeriePorTitulo() {

        System.out.println("Digite o nome da série para busca");

        var nomeSerie = leitura.nextLine();
        var serie = repositorio.findByTituloContainingIgnoreCase(nomeSerie);

        serie.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Série não encontrada"));

//        if(serie.isPresent()) {
//            System.out.println(serie.get());
//        } else {
//            System.out.println("Série não encontrada");
//        }

    }

    private void buscarSerieWeb() {

        DadosSerie dados = getDadosSerie();
        dadosSeries.add(dados);
        Serie serie = new Serie(dados);
        repositorio.save(serie);
        System.out.println(dados);
    }

    private DadosSerie getDadosSerie() {

        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        return dados;
    }

    private void buscarEpisodioPorSerie(){

        listarSeriesBuscadas();
        System.out.println("Escolha uma série pelo nome");
        var nomeSerie = leitura.nextLine();

        //DadosSerie dadosSerie = getDadosSerie();

        // poderia aqui buscar pelo novo metodo da opção 4
        // var serie = repositorio.findByTituloContainingIgnoreCase(nomeSerie);

        Optional<Serie> serie = series.stream()
                .filter(s -> s.getTitulo().toLowerCase().contains(nomeSerie.toLowerCase()))
                .findFirst();

        if(serie.isPresent()) {

            var serieEncontrada = serie.get();
            List<DadosTemporada> temporadas = new ArrayList<>();

            for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
                var json = consumo.obterDados(ENDERECO + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY);
                DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
                temporadas.add(dadosTemporada);
            }
            temporadas.forEach(System.out::println);

            List<Episodio> episodios = temporadas.stream()
                    .flatMap(d -> d.episodios().stream()
                            .map(e -> new Episodio(d.numero(), e)))
                    .collect(Collectors.toList());

            serieEncontrada.setEpisodios(episodios);
            repositorio.save(serieEncontrada);

        } else {
            System.out.println("Série não encontrada");

        }

    }

    private void listarSeriesBuscadas() {

        //List<Serie> series = new ArrayList<>();
        //series = dadosSeries.stream().map(d-> new Serie(d)).collect(Collectors.toList());
        series = repositorio.findAll();
        series.stream().sorted(Comparator.comparing(Serie::getGenero)).forEach(System.out::println);


    }
}