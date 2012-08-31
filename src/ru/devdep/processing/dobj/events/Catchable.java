package ru.devdep.processing.dobj.events;

public interface Catchable {
	public void stopPropagation();
	public Boolean isStopped();
}
