import java.util.Scanner;
import java.util.LinkedList;

public class Arvore {
  private No raiz; 
  
  Arvore (){
    raiz = null;
  }

public void setRaiz(No n){
  this.raiz = n;
}

public int numeroNos(){
  return numeroNos(raiz);
}

public int numeroNos (No n){
  if (n == null) return 0;
  return 1 + numeroNos(n.getEsq()) + numeroNos(n.getDir());
}

public int altura(){
  return altura(raiz);
}

public int altura (No n){
if(n == null ) return -1;
  return 1 + Math.max(altura(n.getEsq()), altura(n.getDir()));
}

public boolean procura(int valor){
  return procura(raiz, valor);
}
  
private boolean procura(No n, int valor){
  if (n == null) return false;
  if (n.getValue() == valor) return true;
  
  return procura(n.getEsq(),valor) || procura(n.getDir(), valor);
}

  public boolean insert(int valor){
    if (procura2(valor))return false;
    raiz = insert(raiz, valor);
    return true;
  }

  private No insert(No n, int valor){
    if(n == null) return new No(valor, null, null);
    if(n.getValue() < valor) n.setEsq(insert(n.getDir(), valor));
    if(n.getValue() > valor) n.setDir(insert(n.getEsq(), valor));
    return n;
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

  private boolean remove(char valor){
    if(! procura(valor)) return false;
    raiz = remove(raiz, valor);
   return true;
  }

  private No remove (No n, char valor){
    if(valor < n.getValue())
     n.setEsq(remove(n.getEsq(), valor));
    else if(valor > n.getValue())
      n.setDir(remove(n.getDir(), valor));
    else if(n.getEsq() == null && n.getDir() == null) n = null;
    else{
      No max = n.getEsq();
      while (max.getDir() != null) max = max.getDir();

      n.setValue(max.getValue());
      n.setDir(remove(n.getDir(), max.getValue()));
    }
    return n;
  }
  
  public void printPreOrder(){
    System.out.print("PreOrder: " );
    printPreOrder (raiz);
    System.out.println();
  }

  private void printPreOrder(No n){
    if(n == null) return;
    System.out.print(" " + n.getValue());
    printPreOrder(n.getEsq());
    printPreOrder(n.getDir());
  }

  public void printInOrder(){
    System.out.print("InOrder: ");
    printInOrder(raiz);
    System.out.println();
  }

  private void printInOrder(No n){
    if(n == null) return;
    printInOrder(n.getEsq());
    System.out.print (" " + n.getValue());
    printInOrder(n.getDir());
  
  }

  public void printPosOrder(){
    System.out.print("PosOrder: ");
    printPosOrder(raiz);
    System.out.println();
  }

  private void printPosOrder(No n){
    if(n == null) return;
    printInOrder(n.getEsq());
    printInOrder(n.getDir());
    System.out.println (" " + n.getValue());
    }

  public void printBFS(){
    int altura = 0;
    System.out.print("BFS: ");
    printBFS(raiz, altura);
    System.out.println();
  }

   private void printBFS(No n, int altura){
    LinkedList <No> lista = new LinkedList<>();  
     
     System.out.print(n.getValue());
     if(n.getEsq() != null)
     lista.add(n.getEsq());      
     if(n.getDir() != null)
     lista.add(n.getDir());

    while(! lista.isEmpty()){
      No no = lista.removeFirst();
      System.out.print(" " + no.getValue());
     if(n.getEsq() != null)
       lista.add(no.getEsq());
      if(n.getDir() != null)
       lista.add(no.getDir());
      }
    }

    public static Arvore leitura(Scanner in){
      Arvore a = new Arvore();
      a.setRaiz(escritaArvore(in));
      return a;
    }

    private static No escritaArvore(Scanner in){
      String x = in.next();
      if (x.equals("null")) return null;
      int valor = Integer.parseInt(x);
      No esquerdo = escritaArvore(in);
      No direito = escritaArvore(in);
      return new No(valor, esquerdo, direito);
    }
}
