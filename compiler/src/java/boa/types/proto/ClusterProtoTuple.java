package boa.types.proto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import boa.types.*;

/**
 * A {@link BoaProtoTuple}.
 * 
 * @author nmtiwari
 */
 public class ClusterProtoTuple extends BoaProtoTuple {
	private final static List < BoaType> members = new ArrayList<BoaType>();
	private final static Map <String, Integer> names = new HashMap<String, Integer>();

	static {
		int counter = 0;

		names.put("similarity", counter++);
		members.add(new BoaInt());

		names.put("cid", counter++);
		members.add(new BoaString());

		names.put("representative", counter++);
		members.add(new BoaBool());

		names.put("length", counter++);
		members.add(new BoaInt());

		names.put("seq_start", counter++);
		members.add(new BoaInt());

		names.put("seq_stop", counter++);
		members.add(new BoaInt());

		names.put("rep_start", counter++);
		members.add(new BoaInt());

		names.put("rep_stop", counter++);
		members.add(new BoaInt());

		names.put("match", counter++);
		members.add(new BoaInt());


		}

	/**
	 * Construct a ClusterProtoTuple
	 */	

	 public ClusterProtoTuple() {
		super(members, names);
	 }

	/** @{inheritDoc} */
	@Override
	public String toJavaType() {
		return "boa.types.Nr2.Cluster";
	}

 }
 