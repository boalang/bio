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
 public class FeatureProtoTuple extends BoaProtoTuple {
	private final static List < BoaType> members = new ArrayList<BoaType>();
	private final static Map <String, Integer> names = new HashMap<String, Integer>();

	static {
		int counter = 0;

		names.put("accession", counter++);
		members.add(new BoaString());

		names.put("seqid", counter++);
		members.add(new BoaString());

		names.put("source", counter++);
		members.add(new BoaString());

		names.put("ftype", counter++);
		members.add(new BoaString());

		names.put("start", counter++);
		members.add(new BoaInt());

		names.put("end", counter++);
		members.add(new BoaInt());

		names.put("score", counter++);
		members.add(new BoaString());

		names.put("strand", counter++);
		members.add(new BoaString());

		names.put("phase", counter++);
		members.add(new BoaString());

		names.put("attribute", counter++);
		members.add(new BoaProtoList( new AttributeProtoTuple()));

		names.put("parent", counter++);
		members.add(new BoaString());


		}

	/**
	 * Construct a FeatureProtoTuple
	 */	

	 public FeatureProtoTuple() {
		super(members, names);
	 }

	/** @{inheritDoc} */
	@Override
	public String toJavaType() {
		return "boa.types.GFeature.Feature";
	}

 }
 