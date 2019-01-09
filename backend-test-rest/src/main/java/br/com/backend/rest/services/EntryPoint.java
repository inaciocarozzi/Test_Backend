package br.com.backend.rest.services;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.backend.rest.data.AppReference;
import br.com.backend.rest.data.JsonResolver;
import br.com.backend.rest.data.TransactionData;
import br.com.backend.rest.utils.ThreadInfiniteJob;
import br.com.backend.rest.utils.ThreadInfiniteJobRun;

@Path("/entry-point")
public class EntryPoint {
	private final static int APPID_EVICTION_TIME = 1 * 60 * 1000;
	private final static Map<String, AppReference> database = new ConcurrentHashMap<String, AppReference>();
	private final static ReentrantLock lk = new ReentrantLock();

	static {
		ThreadInfiniteJob job = new ThreadInfiniteJob(APPID_EVICTION_TIME, new ThreadInfiniteJobRun() {
			@Override
			public boolean onJob() {
				try {
					lk.lock();
					database.clear();
				} finally {
					lk.unlock();
				}
				return true;
			}
		});
		job.start();
	}

	@GET
	@Path("statistics")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAppid() {
		AppReference data = database.get("data");
		return Response.ok(JsonResolver.toJson(data), MediaType.APPLICATION_JSON).build();
	}

	@POST
	@Path("/transactions")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postTec(String rawdata) {
		TransactionData data = (TransactionData) JsonResolver.fromJson(rawdata, TransactionData.class);
		try {
			if (data == null) {
				return Response.status(Status.NO_CONTENT).build();
			}
			try {
				lk.lock();

				AppReference record = database.get("data");
				if (record == null) {
					record = new AppReference();
					record.setCount(record.getCount() + 1);
					record.setAvg((record.getAvg() + data.getAmount()) / 2);
					record.setSum(record.getSum() + data.getAmount());
					if (data.getAmount() > record.getMax()) {
						record.setMax(data.getAmount());
					}
					if (data.getAmount() < record.getMin()) {
						record.setMin(data.getAmount());
					}
					database.put("data", record);	
				}
			} finally {
				lk.unlock();
			}
			return Response.ok().build();
		} catch (Throwable t) {
			return Response.serverError().entity("Error [" + t + "]").build();
		}
	}

}