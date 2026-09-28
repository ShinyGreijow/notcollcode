import java.util.Scanner;

public class ControleEstoque {

    // Quantidade de produtos que o programa cadastra.
    static final int TOTAL_PRODUTOS = 5;

    // Um produto com quantidade MENOR que 5 vai estar no estoque baixo.
    static final int LIMITE_ESTOQUE_BAIXO = 5;

    public static void main(String[] args) {

        // Scanner permite receber dados digitados pelo usuário, é o input do python.
        Scanner scanner = new Scanner(System.in);

        // Os dois arrays têm o tamanhos iguais.
        // A posição 0 de produtos corresponde à posição 0 de quantidades.
        String[] produtos = new String[TOTAL_PRODUTOS];
        int[] quantidades = new int[TOTAL_PRODUTOS];

        // O main organiza a ordem das tarefas do programa.
        cadastrarProdutos(scanner, produtos, quantidades);

        // calcularTotal devolve um número inteiro, que está guardado em total.
        int total = calcularTotal(quantidades);

        exibirRelatorio(produtos, quantidades, total);

        // Fecha o Scanner depois que ele terminar a leitura.
        scanner.close();
    }

    // Este método cadastra os nomes e as quantidades.
    // Ele recebe o Scanner e os dois arrays criados no main, vulgo  a parte acima.
    static void cadastrarProdutos(
            Scanner scanner, String[] produtos, int[] quantidades) {

        // O for repete o cadastro uma vez para cada posição do array, pois é um laço de repetição.
        // i começa em 0, aumenta de 1 em 1 e para ao chegar no tamanho do array.
        for (int i = 0; i < produtos.length; i++) {

            // O do...while executa a pergunta pelo menos uma vez.
            // Se o nome ficar vazio, ele será executado novamente.
            do {
                System.out.print("Nome do produto " + (i + 1) + ": ");

                // nextLine lê o texto digitado.
                // trim remove espaços do início e do fim.
                produtos[i] = scanner.nextLine().trim();

                // isEmpty verifica se não sobrou nenhum caracter. 
                // Dessa froma, um nome formado apenas por espaços também vai ser recusado.
                if (produtos[i].isEmpty()) {
                    System.out.println("Digite um nome.");
                }

            } while (produtos[i].isEmpty());

            // Depois de obter um nome válido, outro método ira ler uma quantidade válida para o mesmo produto.
            quantidades[i] = lerQuantidade(scanner);
        }
    }

    // Este método ira ler e devolver uma quantidade inteira não negativa.
    static int lerQuantidade(Scanner scanner) {

        // while (true) mantém a pergunta em repetição.
        // O método só termina quando encontrar alguma quantidade que seja válida e executa o comando return.
        while (true) {
            System.out.print("Quantidade: ");

            try {
                // nextLine lê a entrada como texto.
                // parseInt tenta transformar esse texto em um número inteiro.
                int quantidade = Integer.parseInt(scanner.nextLine().trim());

                // Zero é permitido: significa que não há unidades no estoque.
                if (quantidade >= 0) {

                    // return devolve o valor e encerra este método.
                    return quantidade;
                }

                // Se chegou aqui, o número digitado era negativo.
                System.out.println("A quantidade não pode ser negativa.");

            } catch (NumberFormatException erro) {

                // O catch é executado quando parseInt não consegue
                // converter a entrada para inteiro, como em "abc" ou "2,5".
                // Depois da mensagem, o while faz a pergunta novamente.
                
                System.out.println("Digite um número inteiro.");
            }
        }
    }

    // Este método soma as quantidades e devolve o total.
    static int calcularTotal(int[] quantidades) {
        int total = 0;

        // Este tipo de for percorre os valores do array.
        // A cada repetição, quantidade recebe o próximo valor.
        for (int quantidade : quantidades) {
            total += quantidade; // Equivale a: total = total + quantidade;
        }

        return total;
    }

    // Este método apresenta o resultado; por isso, seu tipo é void:
    // ele mostra informações, mas não devolve um valor.
    static void exibirRelatorio(
            String[] produtos, int[] quantidades, int total) {

        System.out.println("\n=== RELATÓRIO DE ESTOQUE ===");
        System.out.println("Total de itens: " + total);
        System.out.println("Produtos com estoque baixo:");

        // Começa como false porque ainda não encontramos nenhum produto
        // com estoque baixo.
        boolean encontrou = false;

        // Usamos um for com índice i porque precisamos consultar
        // o nome e a quantidade na MESMA posição dos dois arrays.
        for (int i = 0; i < produtos.length; i++) {

            // O alerta vale apenas para quantidades menores que 5.
            // Uma quantidade igual a 5 não entra no relatório.
            if (quantidades[i] < LIMITE_ESTOQUE_BAIXO) {
                System.out.println(
                    produtos[i] + ": " + quantidades[i] + " unidade(s)"
                );

                encontrou = true;
            }
        }

        // Se o for terminou e encontrou continua false,
        // nenhum produto tinha menos de 5 unidades.
        if (!encontrou) {
            System.out.println("Nenhum produto com estoque baixo.");
        }
    }
}





//Repetição que percorre todos os elementos.
//Nome vazio impedido.
//Quantidade negativa rejeitada.
//Entrada não inteira tratada.
//Total calculado a partir das quantidades.
//Produtos com menos de cinco unidades identificados
// Cada comentario do codigo foi feito com ajuda de IA para futuros estudos e entendimentos do código com mais facilidades, uma revisão