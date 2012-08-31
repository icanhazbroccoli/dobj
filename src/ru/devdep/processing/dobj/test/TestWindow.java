package ru.devdep.processing.dobj.test;

import processing.core.PApplet;
import ru.devdep.processing.dobj.objects.Window;

import java.awt.Canvas;
import java.awt.event.MouseEvent;

public class TestWindow extends Window {

	public TestWindow(PApplet p, int width, int height, String mode) {
		super(p, width, height, mode);
	}

	public void fireClickAt( int pos_x, int pos_y ) {
		captureEvent( new MouseEvent(
			new Canvas(),
			MouseEvent.MOUSE_CLICKED,
			System.currentTimeMillis() / 1000,
			0,
			pos_x,
			pos_y,
			1,
			false
		) );
	}
}
