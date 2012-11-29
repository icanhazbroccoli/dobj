package ru.devdep.processing.dobj.objects;

import java.util.ArrayList;

import TUIO.TuioClient;
import TUIO.TuioCursor;
import TUIO.TuioListener;
import TUIO.TuioObject;
import TUIO.TuioTime;
import processing.core.PApplet;
import ru.devdep.processing.dobj.animation.Lambda;

public class TuioWindow extends Window implements TuioListener {

	private TuioClient client;
	private final ArrayList<Lambda<TuioCursor>> add_tuio_cursor_handlers;
	private final ArrayList<Lambda<TuioCursor>> remove_tuio_cursor_handlers;
	private final ArrayList<Lambda<TuioCursor>> update_tuio_cursor_handlers;
	private final ArrayList<Lambda<TuioObject>> add_tuio_object_handlers;
	private final ArrayList<Lambda<TuioObject>> remove_tuio_object_handlers;
	private final ArrayList<Lambda<TuioObject>> update_tuio_object_handlers;
	private final ArrayList<Lambda<TuioTime>> refresh_handlers;
	
	
	public TuioWindow( PApplet p, int width, int height, String mode ) {
		this( p, width, height, mode, 3333 );
	}
	
	public TuioWindow( PApplet p, int width, int height, String mode, int tuio_port ) {
		super( p, width, height, mode );
		client = new TuioClient( tuio_port );
		client.addTuioListener( this );
		client.connect();
		
		this.add_tuio_cursor_handlers = new ArrayList<Lambda<TuioCursor>>();
		this.remove_tuio_cursor_handlers = new ArrayList<Lambda<TuioCursor>>();
		this.update_tuio_cursor_handlers = new ArrayList<Lambda<TuioCursor>>();
		this.add_tuio_object_handlers = new ArrayList<Lambda<TuioObject>>();
		this.remove_tuio_object_handlers = new ArrayList<Lambda<TuioObject>>();
		this.update_tuio_object_handlers = new ArrayList<Lambda<TuioObject>>();
		this.refresh_handlers = new ArrayList<Lambda<TuioTime>>();
	}

	public void refresh( TuioTime time ) {
		for ( Lambda<TuioTime> handler : refresh_handlers ) {
			handler.run( time );
		}
	}

	public void addTuioCursor( TuioCursor cursor ) {
		for ( Lambda<TuioCursor> handler : add_tuio_cursor_handlers ) {
			handler.run( cursor );
		}
	}

	public void removeTuioCursor( TuioCursor cursor ) {
		for ( Lambda<TuioCursor> handler : remove_tuio_cursor_handlers ) {
			handler.run( cursor );
		}
	}

	public void updateTuioCursor( TuioCursor cursor ) {
		for ( Lambda<TuioCursor> handler : update_tuio_cursor_handlers ) {
			handler.run( cursor );
		}
	}

	public void addTuioObject( TuioObject object ) {
		for ( Lambda<TuioObject> handler : add_tuio_object_handlers ) {
			handler.run( object );
		}
	}

	public void removeTuioObject( TuioObject object ) {
		for ( Lambda<TuioObject> handler : remove_tuio_object_handlers ) {
			handler.run( object );
		}
	}

	public void updateTuioObject( TuioObject object ) {
		for ( Lambda<TuioObject> handler : update_tuio_object_handlers ) {
			handler.run( object );
		}
	}
	
	public void addAddTuioCursorHandler( Lambda<TuioCursor> handler ) {
		this.add_tuio_cursor_handlers.add( handler );
	}
	
	public void addRemoveTuioCursorHandler( Lambda<TuioCursor> handler ) {
		this.remove_tuio_cursor_handlers.add( handler );
	}
	
	public void addUpdateTuioCursorHandler( Lambda<TuioCursor> handler ) {
		this.update_tuio_cursor_handlers.add( handler );
	}
	
	public void addAddTuioObjectHandler( Lambda<TuioObject> handler ) {
		this.add_tuio_object_handlers.add( handler );
	}
	
	public void addRemoveTuioObjectHandler( Lambda<TuioObject> handler ) {
		this.remove_tuio_object_handlers.add( handler );
	}
	
	public void addUpdateTuioObjectHandler( Lambda<TuioObject> handler ) {
		this.update_tuio_object_handlers.add( handler );
	}
	
	public void addRefreshHandler( Lambda<TuioTime> handler ) {
		this.refresh_handlers.add( handler );
	}
	
	public void removeAddTuioCursorHandler( Lambda<TuioCursor> handler ) {
		if ( this.add_tuio_cursor_handlers.contains( handler ) ) {
			this.add_tuio_cursor_handlers.remove( handler );
		}
	}
	
	public void removeRemoveTuioCursorHandler( Lambda<TuioCursor> handler ) {
		if ( this.remove_tuio_cursor_handlers.contains( handler ) ) {
			this.remove_tuio_cursor_handlers.remove( handler );
		}
	}
	
	public void removeUpdateTuioCursorHandler( Lambda<TuioCursor> handler ) {
		if ( this.update_tuio_cursor_handlers.contains( handler ) ) {
			this.update_tuio_cursor_handlers.remove( handler );
		}
	}
	
	public void removeAddTuioObjectHandler( Lambda<TuioObject> handler ) {
		if ( this.add_tuio_object_handlers.contains( handler ) ) {
			this.add_tuio_object_handlers.remove( handler );
		}
	}
	
	public void removeRemoveTuioObjectHandler( Lambda<TuioObject> handler ) {
		if ( this.remove_tuio_object_handlers.contains( handler ) ) {
			this.remove_tuio_object_handlers.remove( handler );
		}
	}
	
	public void removeUpdateTuioObjectHandler( Lambda<TuioObject> handler ) {
		if ( this.update_tuio_object_handlers.contains( handler ) ) {
			this.update_tuio_object_handlers.remove( handler );
		}
	}
	
	public void removeRefreshHandler( Lambda<TuioTime> handler ) {
		if ( this.refresh_handlers.contains( handler ) ) {
			this.refresh_handlers.remove( handler );
		}
	}
	
	protected void finalize() {
		if ( client.isConnected() ) {
			client.disconnect();
		}
	}
}
