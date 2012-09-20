import ru.devdep.processing.dobj.objects.*;
import ru.devdep.processing.dobj.events.*;
import ru.devdep.processing.dobj.animation.*;
import ru.devdep.processing.dobj.test.*;

TestWindow window;
SquareObject sq1, sq2;

public void setup() {
  window = new TestWindow( this, 320, 320, OPENGL );
  background( 255 );
  sq1 = new SquareObject( this );
  sq1.setPos( 10, 10 );
  sq1.setScale( 1 );
  sq1.setDim( 10, 10 );
  window.appendChild( sq1 );
  
  sq2 = new SquareObject( this );
  sq2.setPos( 0, 0 );
  sq2.setScale( 2 );
  sq2.setDim( 5, 5 );
  sq1.appendChild( sq2 );
}

public void draw() {
  fill( 0, 255, 0 );
  noStroke();
  background( 0 );
  
  window.draw();

  try {
    assetClick();
    background( 0, 255, 0 );
  } catch ( AssetException e ) {
    background( 255, 0, 0 );
  }
}

public void asset( Boolean expr, String message ) throws AssetException {
  if ( !expr ) {
    throw new AssetException( this, message );
  }
}

public void assetClick() throws AssetException {
  sq1.setClicked( false );
  sq2.setClicked( false );
  window.fireClickAt( 16, 16 ); // 15 + 1
  asset( sq2.isClicked(), "child clicked" );
  asset( sq1.isClicked(), "parent clicked" );
}
