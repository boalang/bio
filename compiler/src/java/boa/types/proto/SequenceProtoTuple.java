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
 public class SequenceProtoTuple extends BoaProtoTuple {
	private final static List < BoaType> members = new ArrayList<BoaType>();
	private final static Map <String, Integer> names = new HashMap<String, Integer>();

	static {
		int counter = 0;

		names.put("accession", counter++);
		members.add(new BoaString());

		names.put("header", counter++);
		members.add(new BoaString());

		names.put("featureRoot", counter++);
		members.add(new FeatureRootProtoTuple());


		}

	/**
	 * Construct a SequenceProtoTuple
	 */	

	 public SequenceProtoTuple() {
		super(members, names);
	 }

	/** @{inheritDoc} */
	@Override
	public String toJavaType() {
		return "boa.types.GFeature.Sequence";
	}

 }
 