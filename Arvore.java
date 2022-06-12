

import java.util.LinkedList;

public class Arvore {
  private No raiz; 
  
    Arvore (){
      raiz = null;
    }

    public void setRaiz(No n){
      this.raiz = n;
    }

    public int altura(){
      return altura(raiz);
    }

    public int altura (No n){
    if(n == null ) return -1;
      return 1 + Math.max(altura(n.getEsq()), altura(n.getDir()));
    }


    public void insertBFS(No NoAtual,LinkedList<Integer> insert ){ 
      if(this.raiz == null ){
        this.raiz = new No(insert.getFirst(),null,null);
        NoAtual = this.raiz;                    
        insert.removeFirst();// removendo da lista depois de adiciona-lo
        }
      
      if (NoAtual == null)// condição de parada
        return;
      
      insertBFS(NoAtual.getEsq(),insert);
      insertBFS(NoAtual.getDir(),insert);   
            
      //verificando se algum dos filhos é null
      //caso seja adiciona na arvore e remove da lista

      if(insert.size() > 0 && NoAtual.getEsq() == null){
        NoAtual.setEsq(new No(insert.getFirst(),null,null));
        insert.removeFirst();
      }    
        if(insert.size() > 0 && NoAtual.getDir() == null){
        NoAtual.setDir(new No(insert.getFirst(),null,null));
        insert.removeFirst();
        }                      
    }

    public void printPostOrder(){
     System.out.print("PostOrder: ");
     printPostOrder(raiz);
     System.out.println();
  }

    public void printPostOrder(No n){
     if(n == null) return;
      printPostOrder(n.getEsq());
      printPostOrder(n.getDir());
      System.out.print(" " + n.getValue());
  }

    public void printBFS(){
      int altura = 0;
      System.out.print("BFS: ");
      printBFS(raiz, altura);
      System.out.println();
  }

   private void printBFS(No n, int altura){
    LinkedList <No> lista = new LinkedList<>();  

     // imprimindo a raiz e seus filhos caso não sejam null
     System.out.print(n.getValue());
     if(n.getEsq() != null)
     lista.add(n.getEsq());      
     if(n.getDir() != null)
     lista.add(n.getDir());

    while(! lista.isEmpty()){
      No no = lista.removeFirst(); //pegando o primeiro valor da lista para imprimi-lo e o removendo
      System.out.print(" " + no.getValue());
      
      //adicionando o no a esquerda e a direita na lista caso não sejam null
     if(n.getEsq() != null)
       lista.add(no.getEsq());  
      if(n.getDir() != null)
       lista.add(no.getDir());
      }
    }
}
