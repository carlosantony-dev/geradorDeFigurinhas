import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class app {

    public static void main(String[] args) throws Exception {

        // Caso queira, pode utilizar o extrato das API's da Nasa com sua respectiva url
        // String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        // API feita através do Spring, hospedado no Heroku e com o banco de dados MongoDB recebendo os valores do body
        String url = "https://alura-linguagens-api-br.herokuapp.com/linguagens";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // exibir e manipular os dados 
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "exit/" + conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }
    }
}
