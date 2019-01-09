package br.com.backend.rest.utils;

import java.util.concurrent.atomic.AtomicBoolean;

public final class ThreadInfiniteJob {
	private ThreadInfiniteJobRun jobrun;
	private AtomicBoolean alive = new AtomicBoolean(true);
	private Thread th;

	public ThreadInfiniteJob(final long time, final ThreadInfiniteJobRun jobrun) {
		this.th = new Thread(new Runnable() {
			@Override
			public void run() {
				while (alive.get()) {
					try {
						if (jobrun != null)
							alive.set(jobrun.onJob());
					} catch (Throwable t) {
					}
					try {
						Thread.sleep(time);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

	public void start() {
		this.th.start();
	}

	public void join() throws InterruptedException {
		this.th.join();
	}

	public void finish() {
		alive.set(false);
		if (this.th != null) {
			this.th.interrupt();
		}
	}
}
