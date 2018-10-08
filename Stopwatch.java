package edu.iastate.cs228.proj2;

/**
No additions or changes are required in this class.
*/
public class Stopwatch {
	private long elapsedTime = 0;
	private long mostRecentStartTime = -1;
	private boolean started = false;
	private boolean running = false;
	
	
	//Resets elapsed time to 0 and starts timer.
	public void start() {
		if (running) {
			return;
		}
		elapsedTime = 0;
		mostRecentStartTime = System.nanoTime();
		running = true;
		started = true;
	}
	
	//Stops timer, storing elapsed time.
	public void stop() {
		if (!running) {
			return;
		}
		elapsedTime += System.nanoTime() - mostRecentStartTime;
		running = false;
	}
	
	//Restarts timer.
	public void resume() {
		if (!started) {
			throw new IllegalStateException();
		}
		if (running) {
			return;
		}
		mostRecentStartTime = System.nanoTime();
		running = true;
	}
	
	
	//Returns the elapsed time of a stopper timer.
	public long getElapsedTime() {
		if (!started) {
			throw new IllegalStateException();
		}
		if (running) {
			return System.nanoTime() - mostRecentStartTime + elapsedTime;
		}
		return elapsedTime;
	}
}
