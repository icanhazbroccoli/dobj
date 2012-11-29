package ru.devdep.processing.dobj.objects;

import java.awt.Container;
import java.awt.event.MouseEvent;

import TUIO.TuioCursor;
import TUIO.TuioObject;
import processing.core.PApplet;
import ru.devdep.processing.dobj.animation.Lambda;

public class TouchWindow extends TuioWindow {

	protected Boolean is_drag_started;
	private PApplet applet;
	
	public TouchWindow( PApplet p, int width, int height, String mode ) {
		this(p, width, height, mode, 3333);
	}

	public TouchWindow( PApplet p, int width, int height, String mode, int tuio_port ) {
		super(p, width, height, mode, tuio_port);
		setupTouchHandlers();
		this.is_drag_started = false;
		this.applet = p;
	}
	
	protected void setDragStarted( Boolean is_drag_started ) {
		this.is_drag_started = is_drag_started;
	}
	
	protected Boolean isDragStarted() {
		return is_drag_started;
	}

	protected int getTuioX( TuioCursor cursor ) {
		return Math.round(
			this.getWidth() / this.scale * cursor.getX()
		);
	}
	
	protected int getTuioY( TuioCursor cursor ) {
		return Math.round(
				this.getHeight() / this.scale * cursor.getY()
		);
	}
	
	
	protected void setupTouchHandlers() {
		//final Container container = new Container();
		this.addAddTuioCursorHandler( new Lambda<TuioCursor>() {
			public void run( final TuioCursor cursor ) {
				if ( cursor.getCursorID() > 0 ) {
					return;
				}
				setDragStarted( true );
				captureEvent(
					new MouseEvent(
						new Container(),
						MouseEvent.MOUSE_PRESSED,
						System.currentTimeMillis() / 1000,
						0,
						getTuioX( cursor ),
						getTuioY( cursor ),
						1,
						false
					)
				);
			}
		});
		
		this.addUpdateTuioCursorHandler( new Lambda<TuioCursor>() {
			public void run( final TuioCursor cursor ) {
				if ( cursor.getCursorID() > 0 ) {
					return;
				}
				int event_id = isDragStarted() ? MouseEvent.MOUSE_MOVED : MouseEvent.MOUSE_DRAGGED;
				System.out.println( event_id );
				captureEvent(
					new MouseEvent(
						new Container(),
						event_id,
						System.currentTimeMillis() / 1000,
						0,
						getTuioX( cursor ),
						getTuioY( cursor ),
						1,
						false
					)
				);
			}
		});
		
		this.addRemoveTuioCursorHandler( new Lambda<TuioCursor>() {
			public void run( final TuioCursor cursor ) {
				if ( cursor.getCursorID() > 0 ) {
					return;
				}
				setDragStarted( false );
				captureEvent(
					new MouseEvent(
						new Container(),
						MouseEvent.MOUSE_RELEASED,
						System.currentTimeMillis() / 1000,
						0,
						getTuioX( cursor ),
						getTuioY( cursor ),
						1,
						false
					)
				);
			}
		});
	}
}
