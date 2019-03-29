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
 public class AttributeProtoTuple extends BoaProtoTuple {
	private final static List < BoaType> members = new ArrayList<BoaType>();
	private final static Map <String, Integer> names = new HashMap<String, Integer>();

	static {
		int counter = 0;

		names.put("tag", counter++);
		members.add(new BoaString());

		names.put("value", counter++);
		members.add(new BoaString());


		}

	/**
	 * Construct a AttributeProtoTuple
	 */	

	 public AttributeProtoTuple() {
		super(members, names);
	 }

	/** @{inheritDoc} */
	@Override
	public String toJavaType() {
		return "boa.types.GFeature.Attribute";
	}

 }
 