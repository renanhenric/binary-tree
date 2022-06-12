

public class No {
  private int value;
  private No esq;
  private No dir;

  No (int v, No e, No d){
    value = v;
    esq = e;
    dir =d;
  }

  public void setValue(int v){
    this.value = v;
  }

  public void setEsq(No e){
    this.esq = e;
  }

  public void setDir(No d){
    this.dir = d;
  }

  public int getValue(){
    return this.value;
  }
  
  public No getEsq(){
    return this.esq;
  }

  public No getDir(){
    return this.dir;
  }
  
}
