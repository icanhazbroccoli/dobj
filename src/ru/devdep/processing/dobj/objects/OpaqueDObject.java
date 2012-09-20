package ru.devdep.processing.dobj.objects;

import processing.core.PApplet;
import ru.devdep.processing.dobj.animation.AnimationChain;
import ru.devdep.processing.dobj.animation.Lambda;

public class OpaqueDObject extends DObject {
	
	protected float opacity = 1.0f;
	AnimationChain fading_ac;
	
	public OpaqueDObject( PApplet p ) {
		super( p );
	}
	
	public void draw() {
		if ( getOpacity() > 0.0f ) {
			super.draw();
		}
	}
	
	public void setOpacity( float opacity ) {
		if ( opacity < 0.0f ) {
			this.opacity = 0.0f;
		} else if ( opacity > 1.0f ) {
			this.opacity = 1.0f;
		} else {
			this.opacity = opacity;
		}
	}
	
	public float getOpacity() {
		return opacity;
	}
	
	public AnimationChain fadeIn( int duration ) {
		return fadeIn( duration, new Runnable() { public void run() {} } );
	}
	
	public AnimationChain fadeIn( int duration, Runnable handler ) {
		return fadeIn( duration, AnimationChain.EASE_LINEAR, handler );
	}
	
	public AnimationChain fadeIn( int duration, int easing ) {
		return fadeIn( duration, easing, new Runnable() { public void run() {} } );
	}
	
	public AnimationChain fadeIn( int duration, int easing, Runnable handler ) {
		return fadeTo( 1.0f, duration, easing, handler );
	}
	
	public AnimationChain fadeOut( int duration ) {
		return fadeOut( duration, new Runnable() { public void run() {} } );
	}
	
	public AnimationChain fadeOut( int duration, Runnable handler ) {
		return fadeOut( duration, AnimationChain.EASE_LINEAR, handler );
	}
	
	public AnimationChain fadeOut( int duration, int easing ) {
		return fadeOut( duration, easing, new Runnable() { public void run() {} } );
	}
	
	public AnimationChain fadeOut( int duration, int easing, Runnable handler ) {
		return fadeTo( 0.0f, duration, easing, handler );
	}
	
	public AnimationChain fadeTo( float val, int duration, int easing, Runnable handler ) {
		if ( fading_ac != null )
			fading_ac.stop();
		fading_ac = animate( getOpacity(), val, duration, easing, new Lambda<Float>() { public void run( Float v ) {
			setOpacity( v );
		} } ).ensure( handler );
		fading_ac.run();
		return fading_ac;
	}
	
	protected void withModifiers( final Runnable scope_runner ) {
		super.withModifiers( new Runnable() {
			public void run() {
				withOpacity( getOpacity(), scope_runner );
			}
		});
		
	}
	
	public void withOpacity( float opacity, Runnable scope_runner ) {
		if ( opacity < 1.0f ) {
	    	applet.tint( 255, Math.round( 255.0f * opacity ) );
		}
	    scope_runner.run();
	    if ( opacity < 1.0f )
	    	applet.noTint();
	}
}
