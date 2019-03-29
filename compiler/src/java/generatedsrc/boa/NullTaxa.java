//TODO: remove generatedsrc from the following line when to submit on hadoop
package generatedsrc.boa;


public class NullTaxa extends boa.runtime.BoaRunner {
	/** {@inheritDoc} */
	@Override
	public org.apache.hadoop.mapreduce.Job job(final org.apache.hadoop.fs.Path[] ins, final org.apache.hadoop.fs.Path out, final boolean robust) throws java.io.IOException {
		final org.apache.hadoop.mapreduce.Job job = super.job(ins, out, robust);

		job.setJobName("NullTaxa: " + out);

		job.setJarByClass(NullTaxaBoaMapper.class);

		job.setMapperClass(NullTaxaBoaMapper.class);
		job.setCombinerClass(NullTaxaBoaCombiner.class);
		job.setReducerClass(NullTaxaBoaReducer.class);

		return job;
	}

	static {
		getOptions().addOption(org.apache.commons.cli.OptionBuilder.withLongOpt("splitsize")
										.withDescription("split size in BYTES")
										.hasArg()
										.withArgName("BYTES")
										.create("s"));
	}

	public static void main(String[] args) throws Exception {
	org.apache.hadoop.util.ToolRunner.run(new NullTaxa(), args);
		//System.exit();
	}

	@Override
	public int run(String[] args) throws Exception {
		final org.apache.commons.cli.CommandLine line = parseArgs(args, getUsage());
		args = line.getArgs();
		if (args.length != 2) {
			System.err.println("Not enough arguments. Must give input directory and output directory.");
			printHelp(getUsage());
		}

		final boolean robust = line.hasOption("robust");
		final int id;
		if (line.hasOption("job"))
			id = Integer.parseInt(line.getOptionValue("job"));
		else
			id = 0;

		final org.apache.hadoop.fs.Path[] ins = new org.apache.hadoop.fs.Path[1];
		ins[0] = new org.apache.hadoop.fs.Path(args[0] + "/annotations.seq");

		final org.apache.hadoop.mapreduce.Job jb = job(ins, new org.apache.hadoop.fs.Path(args[1]), robust);

		org.apache.hadoop.conf.Configuration configuration = jb.getConfiguration();

		configuration.set("boa.input.dir", args[0]);
		if (line.hasOption("ast"))
			configuration.set("boa.ast.dir", line.getOptionValue("ast"));
		if (line.hasOption("comments"))
			configuration.set("boa.comments.dir", line.getOptionValue("comments"));

		if (line.hasOption("splitsize"))
			configuration.setInt("mapred.max.split.size", Integer.parseInt(line.getOptionValue("splitsize")));
		else
			configuration.setInt("mapred.max.split.size", 67108864);

		if (line.hasOption("profile")) {
			configuration.setBoolean("mapred.task.profile", true);
			configuration.set("mapred.task.profile.maps", "1");
			configuration.set("mapred.task.profile.reduces", "0");
			//configuration.set("mapred.task.profile.params", "-agentlib:hprof=cpu=samples,heap=sites,force=n,thread=y,verbose=n,file=%s");
			configuration.set("mapred.task.profile.params", "-agentlib:hprof=cpu=times,heap=sites,force=n,verbose=n,file=%s");
		}

		jb.setInputFormatClass(org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat.class);

		jb.setNumReduceTasks(1);

		if (id > 0)
			configuration.setInt("boa.hadoop.jobid", id);
		jb.waitForCompletion(false);
		if (id > 0)
			boa.io.BoaOutputCommitter.setJobID(jb.getJobID().toString(), id);
		System.err.println("Job ID: " + jb.getJobID().toString());

		if (line.hasOption("block")) {
			double lastSetup = -1;
			double lastMap = -1;
			double lastReduce = -1;
			while (!jb.isComplete()) {
				final double newSetup = jb.setupProgress();
				final double newMap = jb.mapProgress();
				final double newReduce = jb.reduceProgress();
				if (newSetup != lastSetup) {
					lastSetup = newSetup;
					System.err.println("SETUP : " + (newSetup * 100) + "%");
				}
				if (newMap != lastMap) {
					lastMap = newMap;
					System.err.println("MAP   : " + (newMap * 100) + "%");
				}
				if (newReduce != lastReduce) {
					lastReduce = newReduce;
					System.err.println("REDUCE: " + (newReduce * 100) + "%");
				}
				try {
					Thread.sleep (500);
				} catch (final Exception e) {}
			}
			System.err.println("JOB FINISHED: " + (jb.isSuccessful() ? "Success" : "Failed"));
			return jb.isSuccessful() ? 0 : 1;
		}
		return 0;
	}

	public String getUsage() {
		return "<inputDir> <outputDir>";
	}

	static interface BoaJob {
		void map(final boa.types.Nr2.Sequence _input, final org.apache.hadoop.mapreduce.Mapper<org.apache.hadoop.io.Text, org.apache.hadoop.io.BytesWritable, boa.io.EmitKey, boa.io.EmitValue>.Context context) throws Exception;
	}

	static class NullTaxaBoaMapper extends boa.runtime.BoaMapper {
		private static class Job0 implements BoaJob {
			long ____local_aggregator_countAll;
			long ____local_aggregator_countNull;
			boa.types.Nr2.Sequence ___s;

			public void map(final boa.types.Nr2.Sequence _input, final org.apache.hadoop.mapreduce.Mapper<org.apache.hadoop.io.Text, org.apache.hadoop.io.BytesWritable, boa.io.EmitKey, boa.io.EmitValue>.Context context) throws Exception {
				____local_aggregator_countAll = 0l;

				____local_aggregator_countNull = 0l;

				___s = _input;

				for (long ___i = 0; ___i < ___s.getAnnotationList().size(); ___i++)
				{
					if ((___s.getAnnotationList().get((int)(___i)) != null))
					{
						{
							____local_aggregator_countAll = ____local_aggregator_countAll + (1l);
							if (___s.getAnnotationList().get((int)(___i)).getTaxName().equals(""))
							{
								____local_aggregator_countNull = ____local_aggregator_countNull + (1l);
							}
						}
					}
				}


				if (____local_aggregator_countNull != 0l)
				{
					context.write(new boa.io.EmitKey("countNull", 0), new boa.io.EmitValue(____local_aggregator_countNull));
				}

				if (____local_aggregator_countAll != 0l)
				{
					context.write(new boa.io.EmitKey("countAll", 0), new boa.io.EmitValue(____local_aggregator_countAll));
				}

			}
		}
		private static BoaJob _job_0 = new Job0();

		/** {@inheritDoc} */
		@Override
		protected void map(final org.apache.hadoop.io.Text key, final org.apache.hadoop.io.BytesWritable value, final org.apache.hadoop.mapreduce.Mapper<org.apache.hadoop.io.Text, org.apache.hadoop.io.BytesWritable, boa.io.EmitKey, boa.io.EmitValue>.Context context) throws java.io.IOException {
			try {
				boa.types.Nr2.Sequence _input = boa.types.Nr2.Sequence.parseFrom(com.google.protobuf.CodedInputStream.newInstance(value.getBytes(), 0, value.getLength()));
				runJob("Job0", _job_0, _input, context);
			} catch (final Throwable e) {
				boa.io.BoaOutputCommitter.lastSeenEx = e;
				throw new java.io.IOException("map failure for key '" + key.toString() + "'", e);
			}
		}

		private void runJob(final String name, final BoaJob job, final boa.types.Nr2.Sequence input, final org.apache.hadoop.mapreduce.Mapper<org.apache.hadoop.io.Text, org.apache.hadoop.io.BytesWritable, boa.io.EmitKey, boa.io.EmitValue>.Context context) throws Throwable {
			try {
				job.map(input, context);
			} catch (final Throwable e) {
				LOG.error(name + ": " + e.getClass().getName() + " caught", e);
				throw e;
			}
			context.progress();
		}

		/** {@inheritDoc} */
		@Override
		protected void setup(final org.apache.hadoop.mapreduce.Mapper.Context context) throws java.io.IOException, java.lang.InterruptedException {
			boa.functions.BoaAstIntrinsics.setup(context);
			super.setup(context);
		}

		/** {@inheritDoc} */
		@Override
		protected void cleanup(final org.apache.hadoop.mapreduce.Mapper.Context context) throws java.io.IOException, java.lang.InterruptedException {
			boa.functions.BoaAstIntrinsics.cleanup(context);
			super.cleanup(context);
		}
	}

	static class NullTaxaBoaCombiner extends boa.runtime.BoaCombiner {
		public NullTaxaBoaCombiner() {
			super();

			this.aggregators.put("0::countAll", new boa.aggregators.IntSumAggregator());
			this.aggregators.put("0::countNull", new boa.aggregators.IntSumAggregator());
		}
	}

	static class NullTaxaBoaReducer extends boa.runtime.BoaReducer {
		public NullTaxaBoaReducer() {
			super();

			this.aggregators.put("0::countAll", new boa.aggregators.IntSumAggregator());
			this.aggregators.put("0::countNull", new boa.aggregators.IntSumAggregator());
		}
	}

	@Override
	public org.apache.hadoop.mapreduce.Mapper getMapper() {
		return new NullTaxaBoaMapper();
	}

	@Override
	public boa.runtime.BoaCombiner getCombiner() {
		return new NullTaxaBoaCombiner();
	}

	@Override
	public boa.runtime.BoaReducer getReducer() {
		return new NullTaxaBoaReducer();
	}
}