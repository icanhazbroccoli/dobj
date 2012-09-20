class SquareObject extends DObject {
  
  protected Boolean is_clicked; 
  
  public SquareObject( PApplet p ) {
    super( p );
    is_clicked = false;
  }
  
  public void setClicked( Boolean is_clicked ) {
    this.is_clicked = is_clicked;
  }
  
  public Boolean isClicked() {
    return is_clicked;
  }
  
  protected void onMouseClick( CatchableMouseEvent e ) {
    setClicked( true );
  }
}
