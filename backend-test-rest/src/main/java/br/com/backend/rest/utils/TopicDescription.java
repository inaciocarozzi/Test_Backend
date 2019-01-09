package br.com.backend.rest.utils;

public class TopicDescription {
	public static final int DEFAULT_PARTITION_NUMBER = 2;
	public static final int DEFAULT_REPLICATION_FACTOR = 1;

	private String topic;

	private int partitions;

	public TopicDescription() {
		this.partitions = DEFAULT_PARTITION_NUMBER;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getPartitions() {
		return partitions;
	}

	public void setPartitions(int partitions) {
		this.partitions = partitions;
	}

	@Override
	public String toString() {
		return "TopicDescription{" + "topic='" + topic + '\'' + ", partitions=" + partitions + '}';
	}
}
