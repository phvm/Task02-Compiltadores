import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Stack;

public class RPN {
    private Stack<Double> pilha;

    public RPN(){
            pilha = new Stack<Double>();
            }
    public static void main(String[] args) throws Exception{
        RPN stacker = new RPN();
        Tokenizer tokenizer = new Tokenizer();
        String expressao = "";
        Double resultado;
        //alterar o path para o do arquivo que possuir a expressao a ser analisada. 
        FileReader fr = new FileReader("C:\\Users\\user\\Desktop\\Testes\\Compiladores\\src\\Calc1.stk");
        BufferedReader lerArq = new BufferedReader(fr);
        String linha = lerArq.readLine();
        while (linha != null) {
            expressao += linha + " ";
            linha = lerArq.readLine(); //
          }
        tokenizer.createTokens(expressao);
        resultado = stacker.avaliar(tokenizer.tokens);
        System.out.println(resultado);
   }
	public Double avaliar(ArrayList<Token> tokens) throws Exception{
			for(int i = 0; i <tokens.size(); i++) {
				if(tokens.get(i).type == TokenType.NUM) {
					pilha.push(Double.parseDouble(tokens.get(i).lexeme));
				}else {
					pilha.push(avaliarOperador(tokens.get(i).lexeme));
				}
			}
			return pilha.pop();
        }

     private Double avaliarOperador(String operacao){
            Double resultado = null;
            Double op1 = this.pilha.pop();
            Double op2 = this.pilha.pop();

            switch (operacao)
            {
                case "+":
                    resultado = op1 + op2;
                    break;
                case "-":
                    resultado = op1 - op2;
                    break;
                case "*":
                    resultado = op1 * op2;
                    break;
                case "/":
                    resultado = op1 / op2;
                    break;
            }
            return resultado;
        }
}